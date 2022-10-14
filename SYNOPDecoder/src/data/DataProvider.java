package data;

import sections.sectionone.SectionOne;
import sections.sectionzero.SectionZero;
import sections.time.Time;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProvider {

    public List<String> splitTimeAndSections(String line) {
        line = line.replaceAll("==", "")
                .replaceAll(",", " ")
                .replace(" 333 ", "*");
        String timeLine = line.substring(6, 23);
        String sectionZeroLine = line.substring(23, 40);
        String sectionOneLine = line.substring(40, line.indexOf("*"));

        List<String> lines = new ArrayList<>();
        lines.add(timeLine);
        lines.add(sectionZeroLine);
        lines.add(sectionOneLine);

        return lines;
    }

    public Time prepareTimeObject(String timeLine) {
        String[] splittedTimeLine = timeLine.split(" ");
        String year = splittedTimeLine[0];
        String month = splittedTimeLine[1];
        String day = splittedTimeLine[2];
        String hour = splittedTimeLine[3];
        String minutes = splittedTimeLine[4];
        return new Time(year, month, day, hour, minutes);
    }

    public SectionZero prepareSectionZeroObject(String sectionLine) {

        String[] splittedLine = sectionLine.split(" ");

        String stationTypeIdentifier = splittedLine[0];
        String windIndicator = splittedLine[1].substring(4);
        String blockNumber = splittedLine[2].substring(0, 2);
        String stationNumberIdentifier = splittedLine[2].substring(2);

        switch (windIndicator) {
            case "0" -> windIndicator = "prędkość wiatru oszacowano w m/s";
            case "1" -> windIndicator = "prędkość wiatru zmierzono anemometrem w m/s";
            case "3 " -> windIndicator = "prędkość wiatru oszacowano w węzłach";
            case "4" -> windIndicator = "prędkość wiatru zmierzono anemometrem w węzłach";
            default -> {
            }
        }

        return new  SectionZero(stationTypeIdentifier, windIndicator, blockNumber, stationNumberIdentifier);
    }

    public SectionOne prepareSectionOneObject(String sectionLine) {
        String[] parts = sectionLine.split(" ");
        SectionOne sectionOne = new SectionOne();
        int ADDITIONAL_FACTOR = 0;

        getFirstPart(sectionOne, parts[0]);
        getSecondPart(sectionOne, parts[1]);

        if (sectionOne.getWindSpeed().equals("99")) {
            getAdditionalWindGroup(sectionOne, parts[2]);
            ADDITIONAL_FACTOR = 1;
        }


        getFirstGroup(sectionOne, parts[2 + ADDITIONAL_FACTOR]);
        getSecondGroup(sectionOne, parts[3 + ADDITIONAL_FACTOR]);
        getThirdGroup(sectionOne, parts[4 + ADDITIONAL_FACTOR]);
        getFourthGroup(sectionOne, parts[5 + ADDITIONAL_FACTOR]);
        getFifthGroup(sectionOne, parts[6 + ADDITIONAL_FACTOR]);

        return sectionOne;
    }

    private void getFirstPart(SectionOne sectionOne, String line) {

        String rainGroup = line.substring(0,1);
        String stationType = line.substring(1, 2);
        String cloudsHeight = line.substring(2, 3);
        String horizontalVisibility = line.substring(3);

        rainGroup = findCodeInFile(rainGroup, "rain_group_key.dat", 1);
        stationType = findCodeInFile(stationType, "station_type_key.dat", 1);
        cloudsHeight = findCodeInFile(cloudsHeight, "clouds_height_key.dat", 1);
        horizontalVisibility = findCodeInFile(horizontalVisibility, "horizontal_visibility_key.dat", 2);

        sectionOne.setRainGroup(rainGroup);
        sectionOne.setStationType(stationType);
        sectionOne.setCloudsHeight(cloudsHeight);
        sectionOne.setHorizontalVisibility(horizontalVisibility);
    }

    private void getSecondPart(SectionOne sectionOne, String line) {

        String amountOfCloudCover = line.substring(0, 1);
        String windDirection = line.substring(1, 3);
        String windSpeed = line.substring(3);

        amountOfCloudCover = findCodeInFile(amountOfCloudCover, "amount_of_cloud_cover_key.dat", 1);
        windDirection = findCodeInFile(windDirection, "wind_direction_key.dat", 2);

        sectionOne.setAmountOfCloudCover(amountOfCloudCover);
        sectionOne.setWindDirection(windDirection);
        sectionOne.setWindSpeed(windSpeed);
    }

    private void getAdditionalWindGroup(SectionOne sectionOne, String line) {
        String windSpeed = line.substring(2);
        if (windSpeed.equals("099"))
            windSpeed = "99";
        sectionOne.setWindSpeed(windSpeed);
    }

    private void getFirstGroup(SectionOne sectionOne, String line) {
        String temperatureIndicator = line.substring(1, 2);
        String temperatureValue = line.substring(2);

        if (temperatureIndicator.equals("0"))
            temperatureIndicator = "dodatnia";
        else
            temperatureIndicator = "ujemna";

        temperatureValue = temperatureValue.substring(0, 2) + "." + temperatureValue.substring(2);
        if (temperatureValue.charAt(0) == '0')
            temperatureValue = temperatureValue.substring(1);


        sectionOne.setAirTemperatureIndicator(temperatureIndicator);
        sectionOne.setAirTemperatureValue(temperatureValue);
    }

    private void getSecondGroup(SectionOne sectionOne, String line) {
        String airHumidity = "";
        String dewTemperatureIndicator = line.substring(1, 2);
        String dewTemperatureValue = line.substring(2);

        if (dewTemperatureIndicator.equals("0"))
            dewTemperatureIndicator = "dodatnia";
        else if (dewTemperatureIndicator.equals("1"))
            dewTemperatureIndicator = "ujemna";
        else {
            airHumidity = dewTemperatureValue;
            dewTemperatureIndicator = "Brak odczytu";
            dewTemperatureValue = "Brak odczytu";
            sectionOne.setDewTemperatureAvailability(false);
        }

        if (sectionOne.isDewTemperatureAvailable()) {
            dewTemperatureValue = dewTemperatureValue.substring(0, 2) + "." + dewTemperatureValue.substring(2);
            if (dewTemperatureValue.charAt(0) == '0')
                dewTemperatureValue = dewTemperatureValue.substring(1);
        } else {
            sectionOne.setAirHumidity(airHumidity);
        }

        sectionOne.setDewTemperatureIndicator(dewTemperatureIndicator);
        sectionOne.setDewTemperatureValue(dewTemperatureValue);
    }

    private void getThirdGroup(SectionOne sectionOne, String line) {
        String pressureLevelStation = line.substring(1);

        if (pressureLevelStation.charAt(0) == '0')
            pressureLevelStation = "1" + pressureLevelStation.substring(0, 3) + "." + pressureLevelStation.substring(3);
        else
            pressureLevelStation = pressureLevelStation.substring(0, 3) + "." + pressureLevelStation.substring(3);

        sectionOne.setPressureLevelStation(pressureLevelStation);
    }

    private void getFourthGroup(SectionOne sectionOne, String line) {
        String pressureLevelSea = line.substring(1);
        String geopotentialHeight = "";

        if (pressureLevelSea.charAt(0) != '0' && pressureLevelSea.charAt(0) != '1') {
            geopotentialHeight = pressureLevelSea.substring(1);
        }
        pressureLevelSea = switch (pressureLevelSea.charAt(0)) {
            case '0' -> "1" + pressureLevelSea.substring(0, 3) + "." + pressureLevelSea.substring(3);
            case '1' -> "1000";
            case '2' -> "925";
            case '5' -> "500";
            case '7' -> "700";
            case '8' -> "850";
            default -> pressureLevelSea.substring(0, 3) + "." + pressureLevelSea.substring(3);
        };

        sectionOne.setPressureLevelSea(pressureLevelSea);
        sectionOne.setGeopotentialHeight(geopotentialHeight);
    }

    private void getFifthGroup(SectionOne sectionOne, String line) {
        String pressureTendencyCharacteristic = line.substring(1, 2);
        String pressureTendencyValue = line.substring(2);

        if (pressureTendencyValue.charAt(0) == '0')
            pressureTendencyValue = pressureTendencyValue.charAt(1) + "." + pressureTendencyValue.substring(2);
        else
            pressureTendencyValue = pressureTendencyValue.substring(0, 2) + "." + pressureTendencyValue.substring(2);

        pressureTendencyCharacteristic = findCodeInFile(pressureTendencyCharacteristic, "pressure_tendency_characteristic_key.dat", 1);
        sectionOne.setPressureTendencyCharacteristic(pressureTendencyCharacteristic);
        sectionOne.setPressureTendencyValue(pressureTendencyValue);

    }

    private String findCodeInFile(String codeToFind, String fileName, int codeLength) {

        String filePath = "src/data/datafiles/" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.substring(0, codeLength).equals(codeToFind)) {
                    codeToFind = line.substring(3 + codeLength);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeToFind;
    }
}

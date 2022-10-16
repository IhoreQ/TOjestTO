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
        String sectionOneLine = "";

        if (line.contains("*"))
            sectionOneLine = line.substring(40, line.indexOf("*"));
        else
            sectionOneLine = line.substring(40);

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
            ADDITIONAL_FACTOR++;
        }


        getFirstGroup(sectionOne, parts[2 + ADDITIONAL_FACTOR]);
        getSecondGroup(sectionOne, parts[3 + ADDITIONAL_FACTOR]);
        getThirdGroup(sectionOne, parts[4 + ADDITIONAL_FACTOR]);
        getFourthGroup(sectionOne, parts[5 + ADDITIONAL_FACTOR]);
        getFifthGroup(sectionOne, parts[6 + ADDITIONAL_FACTOR]);

        //TODO naprawić indexowanie
        if (sectionOne.isRainGroupActive())
            getSixthGroup(sectionOne, parts[7 + ADDITIONAL_FACTOR]);
        else
            ADDITIONAL_FACTOR--;

        if (sectionOne.isPresentWeatherActive())
            getSeventhGroup(sectionOne, parts[8 + ADDITIONAL_FACTOR]);
        else
            ADDITIONAL_FACTOR--;

        if (sectionOne.isCloudsGroupActive()) {
            getEighthGroup(sectionOne, parts[9 + ADDITIONAL_FACTOR]);
        }


        return sectionOne;
    }

    private void getFirstPart(SectionOne sectionOne, String line) {

        String rainGroup = line.substring(0,1);
        String stationType = line.substring(1, 2);
        String cloudsHeight = line.substring(2, 3);
        String horizontalVisibility = line.substring(3);


        if (Integer.parseInt(rainGroup) < 2)
            sectionOne.setRainGroupActive(true);

        switch (stationType) {
            case "1", "4" -> {
                sectionOne.setAutomaticStation(false);
                sectionOne.setPresentWeatherActive(true);
            }
            case "2", "3" -> {
                sectionOne.setAutomaticStation(false);
                sectionOne.setPresentWeatherActive(false);
            }
            case "5", "6" -> {
                sectionOne.setAutomaticStation(true);
                sectionOne.setPresentWeatherActive(false);
            }
            case "7" -> {
                sectionOne.setAutomaticStation(true);
                sectionOne.setPresentWeatherActive(true);
            }
        }

        if (cloudsHeight.equals("/") || cloudsHeight.equals("9")) {
            sectionOne.setCloudsGroupActive(false);
        }

        rainGroup = findCodeInFile(rainGroup, "rain_group_key.dat", rainGroup.length());
        stationType = findCodeInFile(stationType, "station_type_key.dat", stationType.length());
        cloudsHeight = findCodeInFile(cloudsHeight, "clouds_height_key.dat", cloudsHeight.length());
        horizontalVisibility = findCodeInFile(horizontalVisibility, "horizontal_visibility_key.dat", horizontalVisibility.length());

        sectionOne.setRainGroup(rainGroup);
        sectionOne.setStationType(stationType);
        sectionOne.setCloudsHeight(cloudsHeight);
        sectionOne.setHorizontalVisibility(horizontalVisibility);
    }

    private void getSecondPart(SectionOne sectionOne, String line) {

        String amountOfCloudCover = line.substring(0, 1);
        String windDirection = line.substring(1, 3);
        String windSpeed = line.substring(3);

        windSpeed = windSpeed.replaceFirst("^0+(?!$)", "");

        amountOfCloudCover = findCodeInFile(amountOfCloudCover, "amount_of_cloud_cover_key.dat", amountOfCloudCover.length());
        windDirection = findCodeInFile(windDirection, "wind_direction_key.dat", windDirection.length());

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
        String isobaricSurface = "";

        if (pressureLevelSea.charAt(0) != '0' && pressureLevelSea.charAt(0) != '1') {
            geopotentialHeight = pressureLevelSea.substring(1);
        }
        switch (pressureLevelSea.charAt(0)) {
            case '0' -> pressureLevelSea = "1" + pressureLevelSea.substring(0, 3) + "." + pressureLevelSea.substring(3);
            case '1' -> isobaricSurface = "1000";
            case '2' -> isobaricSurface = "925";
            case '5' -> isobaricSurface = "500";
            case '7' -> isobaricSurface = "700";
            case '8' -> isobaricSurface = "850";
            default -> pressureLevelSea = pressureLevelSea.substring(0, 3) + "." + pressureLevelSea.substring(3);
        };

        sectionOne.setPressureLevelSea(pressureLevelSea);
        sectionOne.setIsobaricSurface(isobaricSurface);
        sectionOne.setGeopotentialHeight(geopotentialHeight);
    }

    private void getFifthGroup(SectionOne sectionOne, String line) {
        String pressureTendencyCharacteristic = line.substring(1, 2);
        String pressureTendencyValue = line.substring(2);

        if (pressureTendencyValue.charAt(0) == '0')
            pressureTendencyValue = pressureTendencyValue.charAt(1) + "." + pressureTendencyValue.substring(2);
        else
            pressureTendencyValue = pressureTendencyValue.substring(0, 2) + "." + pressureTendencyValue.substring(2);

        pressureTendencyCharacteristic = findCodeInFile(pressureTendencyCharacteristic, "pressure_tendency_characteristic_key.dat", pressureTendencyCharacteristic.length());
        sectionOne.setPressureTendencyCharacteristic(pressureTendencyCharacteristic);
        sectionOne.setPressureTendencyValue(pressureTendencyValue);

    }

    private void getSixthGroup(SectionOne sectionOne, String line) {
        String rainfall = line.substring(1, 4);
        String rainfallDuration = line.substring(4);

        rainfall = rainfall.replaceFirst("^0+(?!$)", "");

        if (Integer.parseInt(rainfall) > 988)
            rainfall = findCodeInFile(rainfall, "rainfall_key.dat", rainfall.length());
        else
            rainfall += " mm";

        rainfallDuration = findCodeInFile(rainfallDuration, "rainfall_duration_key.dat", rainfallDuration.length());

        sectionOne.setRainfall(rainfall);
        sectionOne.setRainfallDuration(rainfallDuration);
    }

    private void getSeventhGroup(SectionOne sectionOne, String line) {

        String presentWeather = line.substring(1, 3);
        String pastWeatherFirstPart = line.substring(3, 4);
        String pastWeatherSecondPart = line.substring(4);

        if (sectionOne.isAutomaticStation()) {
            presentWeather = findCodeInFile(presentWeather, "wmo_code_table_4680.dat", presentWeather.length());
            pastWeatherFirstPart = findCodeInFile(pastWeatherFirstPart, "wmo_code_table_4531.dat", pastWeatherFirstPart.length());
            pastWeatherSecondPart = findCodeInFile(pastWeatherSecondPart, "wmo_code_table_4531.dat", pastWeatherSecondPart.length());
        } else {
            presentWeather = findCodeInFile(presentWeather, "wmo_code_table_4677.dat", presentWeather.length());
            pastWeatherFirstPart = findCodeInFile(pastWeatherFirstPart, "wmo_code_table_4561.dat", pastWeatherFirstPart.length());
            pastWeatherSecondPart = findCodeInFile(pastWeatherSecondPart, "wmo_code_table_4561.dat", pastWeatherSecondPart.length());
        }

        sectionOne.setPresentWeather(presentWeather);
        sectionOne.setPastWeatherFirstPart(pastWeatherFirstPart);
        sectionOne.setPastWeatherSecondPart(pastWeatherSecondPart);
    }

    private void getEighthGroup(SectionOne sectionOne, String line) {
        String cloudCover = line.substring(1, 2);
        String stratocumulusClouds = line.substring(2, 3);
        String altocumulusClouds = line.substring(3, 4);
        String cirrusClouds = line.substring(4);

        stratocumulusClouds = findCodeInFile(stratocumulusClouds, "stratocumulus_clouds_key.dat", stratocumulusClouds.length());
        altocumulusClouds = findCodeInFile(altocumulusClouds, "altocumulus_clouds_key.dat", altocumulusClouds.length());
        cirrusClouds = findCodeInFile(cirrusClouds, "cirrus_clouds_key.dat", cirrusClouds.length());

        sectionOne.setCloudCover(cloudCover);
        sectionOne.setStratocumulusClouds(stratocumulusClouds);
        sectionOne.setAltocumulusClouds(altocumulusClouds);
        sectionOne.setCirrusClouds(cirrusClouds);

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

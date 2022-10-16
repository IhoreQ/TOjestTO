package sections.sectionone;

public class SectionOne {

    private String rainGroup;
    private String stationType;
    private String cloudsHeight;
    private String horizontalVisibility;
    private String amountOfCloudCover;
    private String windDirection;
    private String windSpeed;
    private String airTemperatureIndicator;
    private String airTemperatureValue;
    private String dewTemperatureIndicator;
    private String dewTemperatureValue;
    private boolean dewTemperatureAvailability = true;
    private String airHumidity;
    private String pressureLevelStation;
    private String pressureLevelSea;
    private String isobaricSurface;
    private String geopotentialHeight;
    private String pressureTendencyCharacteristic;
    private String pressureTendencyValue;
    private String rainfall;
    private String rainfallDuration;
    private String presentWeather;
    private String pastWeatherFirstPart;
    private String pastWeatherSecondPart;
    private String cloudCover;
    private String stratocumulusClouds;
    private String altocumulusClouds;
    private String cirrusClouds;

    private boolean automaticStation = false;
    private boolean rainGroupActive = false;
    private boolean cloudsGroupActive = false;
    private boolean presentWeatherActive = false;

    public void setRainGroup(String rainGroup) {
        this.rainGroup = rainGroup;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public void setCloudsHeight(String cloudsHeight) {
        this.cloudsHeight = cloudsHeight;
    }

    public void setHorizontalVisibility(String horizontalVisibility) {
        this.horizontalVisibility = horizontalVisibility;
    }

    public void setAmountOfCloudCover(String amountOfCloudCover) {
        this.amountOfCloudCover = amountOfCloudCover;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setAirTemperatureIndicator(String airTemperatureIndicator) {
        this.airTemperatureIndicator = airTemperatureIndicator;
    }

    public void setAirTemperatureValue(String airTemperatureValue) {
        this.airTemperatureValue = airTemperatureValue;
    }

    public void setDewTemperatureIndicator(String dewTemperatureIndicator) {
        this.dewTemperatureIndicator = dewTemperatureIndicator;
    }

    public void setDewTemperatureValue(String dewTemperatureValue) {
        this.dewTemperatureValue = dewTemperatureValue;
    }

    public void setPressureLevelStation(String pressureLevelStation) {
        this.pressureLevelStation = pressureLevelStation;
    }

    public void setPressureLevelSea(String pressureLevelSea) {
        this.pressureLevelSea = pressureLevelSea;
    }

    public void setIsobaricSurface(String isobaricSurface) {
        this.isobaricSurface = isobaricSurface;
    }

    public void setGeopotentialHeight(String geopotentialHeight) {
        this.geopotentialHeight = geopotentialHeight;
    }

    public void setPressureTendencyCharacteristic(String pressureTendencyCharacteristic) {
        this.pressureTendencyCharacteristic = pressureTendencyCharacteristic;
    }

    public void setPressureTendencyValue(String pressureTendencyValue) {
        this.pressureTendencyValue = pressureTendencyValue;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public void setRainfallDuration(String rainfallDuration) {
        this.rainfallDuration = rainfallDuration;
    }

    public void setPresentWeather(String presentWeather) {
        this.presentWeather = presentWeather;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setStratocumulusClouds(String stratocumulusClouds) {
        this.stratocumulusClouds = stratocumulusClouds;
    }

    public void setAltocumulusClouds(String altocumulusClouds) {
        this.altocumulusClouds = altocumulusClouds;
    }

    public void setCirrusClouds(String cirrusClouds) {
        this.cirrusClouds = cirrusClouds;
    }

    public String getRainGroup() {
        return rainGroup;
    }

    public String getStationType() {
        return stationType;
    }

    public String getCloudsHeight() {
        return cloudsHeight;
    }

    public String getHorizontalVisibility() {
        return horizontalVisibility;
    }

    public String getAmountOfCloudCover() {
        return amountOfCloudCover;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getAirTemperatureIndicator() {
        return airTemperatureIndicator;
    }

    public String getAirTemperatureValue() {
        return airTemperatureValue;
    }

    public String getDewTemperatureIndicator() {
        return dewTemperatureIndicator;
    }

    public String getDewTempaeratureValue() {
        return dewTemperatureValue;
    }

    public String getPressureLevelStation() {
        return pressureLevelStation;
    }

    public String getPressureLevelSea() {
        return pressureLevelSea;
    }

    public String getIsobaricSurface() {
        return isobaricSurface;
    }

    public String getGeopotentialHeight() {
        return geopotentialHeight;
    }

    public String getPressureTendencyCharacteristic() {
        return pressureTendencyCharacteristic;
    }

    public String getPressureTendencyValue() {
        return pressureTendencyValue;
    }

    public String getRainfall() {
        return rainfall;
    }

    public String getRainfallDuration() {
        return rainfallDuration;
    }

    public String getPresentWeather() {
        return presentWeather;
    }

    public String getPastWeatherFirstPart() {
        return pastWeatherFirstPart;
    }

    public void setPastWeatherFirstPart(String pastWeatherFirstPart) {
        this.pastWeatherFirstPart = pastWeatherFirstPart;
    }

    public String getPastWeatherSecondPart() {
        return pastWeatherSecondPart;
    }

    public void setPastWeatherSecondPart(String pastWeatherSecondPart) {
        this.pastWeatherSecondPart = pastWeatherSecondPart;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getStratocumulusClouds() {
        return stratocumulusClouds;
    }

    public String getAltocumulusClouds() {
        return altocumulusClouds;
    }

    public String getCirrusClouds() {
        return cirrusClouds;
    }

    public String getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(String airHumidity) {
        this.airHumidity = airHumidity;
    }

    public boolean isDewTemperatureAvailable() {
        return dewTemperatureAvailability;
    }

    public void setDewTemperatureAvailability(boolean dewTemperatureAvailability) {
        this.dewTemperatureAvailability = dewTemperatureAvailability;
    }

    public boolean isAutomaticStation() {
        return automaticStation;
    }

    public void setAutomaticStation(boolean automaticStation) {
        this.automaticStation = automaticStation;
    }

    public boolean isRainGroupActive() {
        return rainGroupActive;
    }

    public void setRainGroupActive(boolean rainGroupActive) {
        this.rainGroupActive = rainGroupActive;
    }

    public boolean isCloudsGroupActive() {
        return cloudsGroupActive;
    }

    public void setCloudsGroupActive(boolean cloudsGroupActive) {
        this.cloudsGroupActive = cloudsGroupActive;
    }

    public boolean isPresentWeatherActive() {
        return presentWeatherActive;
    }

    public void setPresentWeatherActive(boolean presentWeatherActive) {
        this.presentWeatherActive = presentWeatherActive;
    }

    public String toString() {

        String finalResult = "";

        finalResult += "Wskaźnik grupy opadowej: " + rainGroup +
                "\nTyp stacji: " + stationType +
                "\nWysokość względna podstawy najniższych chmur: " + cloudsHeight +
                "\nWidzialność w kierunku poziomym: " + horizontalVisibility +
                "\nWielkość zachmurzenia ogólnego: " + amountOfCloudCover +
                "\nŚredni (z 10 minut) kierunek wiatru rzeczywistego: " + windDirection +
                "\nPrędkość wiatru rzeczywistego wyrażona w węzłąch: " + windSpeed +
                "\nWskaźnik temperatury powietrza: " + airTemperatureIndicator +
                "\nTemperatura powietrza: " + airTemperatureValue + "°C" +
                "\nWskaźnik temperatury punktu rosy: " + dewTemperatureIndicator;

        if (!dewTemperatureAvailability) {
            finalResult += "\nTemperatura punktu rosy: " + dewTemperatureValue + "\nWilgotność powietrza: " + airHumidity + "%";
        } else {
            finalResult += "\nTemperatura punktu rosy: " + dewTemperatureValue + "°C";
        }

        finalResult += "\nCiśnienie atmosferyczne na poziomie stacji: " + pressureLevelStation + " hPa";

        if (isobaricSurface.equals("")) {
            finalResult += "\nCiśnienie atmosferyczne na poziomie morza: " + pressureLevelSea + " hPa";
        } else {
            finalResult += "\nCiśnienie atmosferyczne na poziomie morza: nie można podać ciśnienia" +
                    "\nStandardowa powierzchnia izobaryczna: " + isobaricSurface +
                    "\nWysokość geopotencjalna: " + geopotentialHeight;
        }
        finalResult += "\nCharakterystyka tendencji ciśnienia atmosferycznego: " + pressureTendencyCharacteristic +
                "\nBezwzględna wartość tendencji ciśnienia atmosferycznego: " + pressureTendencyValue + " hPa";

        if (isRainGroupActive()) {
            finalResult += "\nSuma opadu: " + rainfall +
                    "\nCzas trwania okresu opadu kończącego się w terminie obserwacji: " + rainfallDuration;
        }

        if (isPresentWeatherActive()) {
            finalResult += "\nPogoda bieżąca: " + presentWeather +
                    "\nPogoda ubiegła: " + pastWeatherFirstPart + ", " + pastWeatherSecondPart;
        }

        if (isCloudsGroupActive()) {
            finalResult += "\nWielkość zachmurzenia (oktanty): " + cloudCover +
                    "\nChmury Stratocumulus, Stratus, Cumulus i Cumulonimbus: " + stratocumulusClouds +
                    "\nChmury Altocumulus, Altostratus i Nimbostratus: " + altocumulusClouds +
                    "\nChmury Cirrus, Cirrocumulus i Cirrostratus" + cirrusClouds;
        }



        return finalResult;
    }
}

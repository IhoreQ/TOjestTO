package sections.sectionzero;

public class SectionZero {
    private final String stationTypeIdentifier;
    private final String stationNumberIdentifier;
    private final String windIndicator;
    private final String blockNumber;


    public SectionZero(String stationTypeIdentifier, String windIndicator, String blockNumber, String stationNumberIdentifier) {
        this.stationTypeIdentifier = stationTypeIdentifier;
        this.stationNumberIdentifier = stationNumberIdentifier;
        this.blockNumber = blockNumber;
        this.windIndicator = windIndicator;
    }

    @Override
    public String toString() {
        return "Identyfikator rodzaju stacji: " + stationTypeIdentifier + "\nIndywidualny numer stacji: " + stationNumberIdentifier + "\nWskaźnik wiatru: " +
                windIndicator + "\nNumer blokowy: " + blockNumber;
    }
}

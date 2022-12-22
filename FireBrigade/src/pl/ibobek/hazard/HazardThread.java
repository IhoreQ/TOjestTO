package pl.ibobek.hazard;

import pl.ibobek.observer.MainStation;

public class HazardThread implements Runnable {

    private Hazard hazard;
    private MainStation mainStation;

    public HazardThread(MainStation mainStation, Hazard hazard) {
        this.mainStation = mainStation;
        this.hazard = hazard;
    }

    @Override
    public void run() {
        // Główna stacja odbiera sygnał o zagrożeniu i pobiera listę dostępnych stacji
        mainStation.receiveRequest(hazard);
        // Stacja zawiadamia podległe stacje o zagrożeniu
        mainStation.notifySubscribers();
        // Stacja pobiera informacje o dostępnych jednostkach
        mainStation.getAvailableStations();
        // Stacja wysyła jednostkę która znajduje się najbliżej
        mainStation.sendTeam();
    }
}

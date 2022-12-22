package pl.ibobek.observer;

import pl.ibobek.hazard.Hazard;
import pl.ibobek.iterator.FireStationsCollection;
import pl.ibobek.iterator.Iterator;

import java.util.ArrayList;

public class MainStation {

    private ArrayList<Subscriber> subscribers = new ArrayList<>();
    private ArrayList<FireStation> availableStations = new ArrayList<>();
    private final FireStationsCollection fireStations;
    private Hazard hazard;

    public MainStation() {
        this.fireStations = new FireStationsCollection();
        for (FireStation fireStation : fireStations.getFireStations()) {
            subscribe(fireStation);
        }
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(hazard.getEvent());
        }
    }

    public void getAvailableStations() {

        Iterator it = fireStations.createIterator();
        availableStations.clear();

        FireStation tempStation = it.getNext();

        while (tempStation != null) {
            if (tempStation.isAvailable())
                availableStations.add(tempStation);

            tempStation = it.getNext();
        }
    }

    public void receiveRequest(Hazard hazard) {
        this.hazard = hazard;
        System.out.println(hazard);
    }

    private FireStation findNearestFireStation() throws Exception {

        if (availableStations.size() == 0)
            throw new Exception("Żadna stacja nie ma dostępnych wozów!");

        double x = hazard.getCoordinates()[0];
        double y = hazard.getCoordinates()[1];
        FireStation nearestFireStation = null;
        double tempDistance = 666;

        for (FireStation fireStation : availableStations) {
            double newDistance = calculateDistance(fireStation.getX(), fireStation.getY(), hazard.getCoordinates()[0], hazard.getCoordinates()[1]);
            if (newDistance < tempDistance) {
                tempDistance = newDistance;
                nearestFireStation = fireStation;
            }
        }

        return nearestFireStation;
    }

    private double calculateDistance(double eventX, double eventY, double stationX, double stationY) {
        return Math.sqrt(Math.pow(stationX - eventX, 2) - Math.pow(stationY - eventY, 2));
    }

    public void sendTeam() {

        FireStation fireStation;
        try {
            fireStation = findNearestFireStation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
            return;
        }
        hazard.setFireStation(fireStation);
        hazard.action();
    }
}

package pl.ibobek.observer;

import pl.ibobek.strategy.Event;

public class FireStation implements Subscriber {

    private double x;
    private double y;
    private final String stationNumber;
    private int carsNumber;

    private Event event;

    public FireStation(double x, double y, String stationNumber) {
        this.x = x;
        this.y = y;
        this.stationNumber = stationNumber;
        this.carsNumber = 5;;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public boolean isAvailable() { return carsNumber >= event.getRequiredCars(); }

    @Override
    public void update(Event event) {
        this.event = event;
    }

    public void sendCars() {
        carsNumber -= event.getRequiredCars();
    }

    public void returnCars() {
        carsNumber += event.getRequiredCars();
    }
}

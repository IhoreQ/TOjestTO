package pl.ibobek.observer;

import pl.ibobek.observer.state.Car;
import pl.ibobek.observer.state.FreeState;
import pl.ibobek.strategy.Event;

import java.util.ArrayList;

public class FireStation implements Subscriber {

    private double x;
    private double y;
    private final String stationNumber;
    private int carsNumber;
    private ArrayList<Car> cars;

    private Event event;

    public FireStation(double x, double y, String stationNumber) {
        this.x = x;
        this.y = y;
        this.stationNumber = stationNumber;
        this.carsNumber = 5;
        this.cars = new ArrayList<>();

        for (int i = 1; i <= carsNumber; ++i) {
            this.cars.add(new Car(i));
        }
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

    @Override
    public void update(Event event) {
        this.event = event;
    }

    public  ArrayList<Car> sendCars(int carsLeft) {
        int addedCars = 0;
        ArrayList<Car> availableCars = new ArrayList<>();

        for (Car car : cars) {
            if (car.getState() instanceof FreeState) {
                availableCars.add(car);
                addedCars++;
            }
            if (addedCars == carsLeft)
                break;
        }
        return availableCars;
    }

    public int getAvailableCarsNumber() {
        int carsNumber = 0;
        for (Car car : cars)
            if (car.getState() instanceof FreeState) {
                carsNumber++;
            }

        return carsNumber;
    }

    public boolean hasAnyCars() {
        for (Car car : cars) {
            if (car.getState() instanceof FreeState)
                return true;
        }
        return false;
    }
}

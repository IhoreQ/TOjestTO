package pl.ibobek.hazard;

import pl.ibobek.observer.FireStation;
import pl.ibobek.observer.state.Car;
import pl.ibobek.strategy.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Hazard {

    private final Double[] coordinates;
    private Event event;
    private ArrayList<FireStation> fireStations;

    public Hazard(Event event, Double[] coordinates) {
        this.event = event;
        this.coordinates = coordinates;
    }

    public Event getEvent() {
        return event;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setFireStations(ArrayList<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    public void action() {

        int carsLeft = event.getRequiredCars();
        ArrayList<Car> cars = new ArrayList<>();

        for (FireStation fireStation : fireStations) {
            ArrayList<Car> carsToSend = fireStation.sendCars(carsLeft);
            cars.addAll(carsToSend);
            carsLeft -= carsToSend.size();
            System.out.println(fireStation.getStationNumber() + " wysyła " + carsToSend.size() + " jednostki");
        }
        // Odejmij aktywne jednostki stacji
        cars.forEach(Car::handle);
        // Czas na dojazd
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 3) * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Sprawdź czy fałszywy
        if (event.isFalse()) {
            System.out.println("Alarm był fałszywy.");
            // Zawróć do jednostki
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 3) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fireStations.forEach(fireStation -> {System.out.println("Ekipa ze stacji " + fireStation.getStationNumber() + " wróciła z akcji.");});
            cars.forEach(Car::handle);
        }
        else {
            for (FireStation fireStation : fireStations)
                System.out.println("Ekipa " + fireStation.getStationNumber() + " podejmuje działanie.");

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5, 25) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 3) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fireStations.forEach(fireStation -> {System.out.println("Ekipa ze stacji " + fireStation.getStationNumber() + " wróciła z akcji.");});

            cars.forEach(Car::handle);
        }
    }

    @Override
    public String toString() {
        return event + "\nWspółrzędne zdarzenia:\nx = " + coordinates[0] + "\ny = " + coordinates[1];
    }
}

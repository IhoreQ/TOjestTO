package pl.ibobek.hazard;

import pl.ibobek.observer.FireStation;
import pl.ibobek.strategy.Event;

import java.util.concurrent.ThreadLocalRandom;

public class Hazard {

    private final Double[] coordinates;
    private Event event;
    private FireStation fireStation;

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

    public void setFireStation(FireStation fireStation) {
        this.fireStation = fireStation;
    }

    public void action() {

        System.out.println(fireStation.getStationNumber() + " wysyła " + event.getRequiredCars() + " jednostki");
        // Odejmij aktywne jednostki stacji
        fireStation.sendCars();
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
            System.out.println("Ekipa ze stacji " + fireStation.getStationNumber() + " wróciła z akcji.");
            fireStation.returnCars();
        }
        else {
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
            System.out.println("Ekipa ze stacji " + fireStation.getStationNumber() + " wróciła z akcji.");
            fireStation.returnCars();
        }
    }

    @Override
    public String toString() {
        return event + "\nWspółrzędne zdarzenia:\nx = " + coordinates[0] + "\ny = " + coordinates[1];
    }
}

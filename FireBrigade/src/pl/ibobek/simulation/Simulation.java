package pl.ibobek.simulation;

import pl.ibobek.hazard.Hazard;
import pl.ibobek.hazard.HazardGenerator;
import pl.ibobek.hazard.HazardThread;
import pl.ibobek.observer.MainStation;
import pl.ibobek.strategy.Event;

import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    private final HazardGenerator hazardGenerator;
    private final MainStation mainStation;

    public Simulation() {
        hazardGenerator = new HazardGenerator();
        mainStation = new MainStation();
    }

    public void start() {
        while (true) {
            Event event = hazardGenerator.generateEvent();
            Double[] coordinates = hazardGenerator.generateCoordinates();

            Hazard hazard = new Hazard(event, coordinates);
            new Thread(new HazardThread(mainStation, hazard)).start();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(10, 20) * 1000L);
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }
    }
}

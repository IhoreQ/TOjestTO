package pl.ibobek.hazard;

import pl.ibobek.strategy.Event;
import pl.ibobek.strategy.Fire;
import pl.ibobek.strategy.OtherHazard;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HazardGenerator {

    private final double startXBoundary;
    private final double endXBoundary;
    private final double startYBoundary;
    private final double endYBoundary;
    private Random random;

    public HazardGenerator() {
        this.startXBoundary = 49.95855025648944;
        this.endXBoundary = 50.154564013341734;
        this.startYBoundary = 19.688292482742394;
        this.endYBoundary = 20.02470275868903;
        this.random = new Random();
    }

    public Event generateEvent() {

        Event event;
        double randomDouble = random.nextDouble();
        if (randomDouble > 0.7)
            event = new OtherHazard();
        else
            event = new Fire();

        return event;
    }

    public Double[] generateCoordinates() {
        Double[] coords = new Double[2];

        double x = ThreadLocalRandom.current().nextDouble(startXBoundary, endXBoundary);
        double y = ThreadLocalRandom.current().nextDouble(startYBoundary, endYBoundary);
        coords[0] = x;
        coords[1] = y;

        return coords;
    }
}
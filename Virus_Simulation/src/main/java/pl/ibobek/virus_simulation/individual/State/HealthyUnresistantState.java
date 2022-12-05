package pl.ibobek.virus_simulation.individual.State;

import javafx.scene.paint.Color;
import pl.ibobek.virus_simulation.application.SimulationController;
import pl.ibobek.virus_simulation.individual.Individual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class HealthyUnresistantState extends State {
    private final Color color = Color.BLUE;

    public HealthyUnresistantState(Individual individual) {
        super(individual);
    }
    public HealthyUnresistantState(Individual individual, State state) {
        super(individual);
        this.secondsToBecomeResistant = state.secondsToBecomeResistant;
    }

    @Override
    public void handle() {

        Set<Individual> population = individual.getInfectionTimes().keySet();
        HashMap<Individual, Integer> populationMap = individual.getInfectionTimes();

        for (Individual other : population) {
            int timeToBeInfected = individual.getInfectionTimes().get(other);
            if ((other.getState().getColor() == Color.RED || other.getState().getColor() == Color.VIOLET)
                    && individual.getPosition().getDistance(other.getPosition()) < 2 * SimulationController.scale) {
                if (timeToBeInfected <= 0) {
                    if (color == Color.RED || new Random().nextBoolean()) {
                        if (new Random().nextBoolean())
                            individual.setState(new SickWithSymptoms(individual));
                        else
                            individual.setState(new SickNoSymptoms(individual));
                    }
                } else
                    populationMap.put(other, timeToBeInfected - 1);
            } else {
                populationMap.put(other, 3 * (int)SimulationController.fps);
            }
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getSecondsToBecomeResistant() {
        return secondsToBecomeResistant;
    }
}

package pl.ibobek.virus_simulation.individual.State;

import javafx.scene.paint.Color;
import pl.ibobek.virus_simulation.application.SimulationController;
import pl.ibobek.virus_simulation.individual.Individual;

import java.util.Random;

public abstract class State {

    protected Individual individual;
    protected int secondsToBecomeResistant = (new Random().nextInt(30 - 20 + 1) + 20) * (int) SimulationController.fps;

    public State(Individual individual) {
        this.individual = individual;
    }

    public abstract void handle();
    public abstract Color getColor();

    public abstract int getSecondsToBecomeResistant();
}

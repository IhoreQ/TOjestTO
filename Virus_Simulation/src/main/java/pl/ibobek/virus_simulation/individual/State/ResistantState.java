package pl.ibobek.virus_simulation.individual.State;

import javafx.scene.paint.Color;
import pl.ibobek.virus_simulation.individual.Individual;

public class ResistantState extends State {
    private final Color color = Color.GREEN;

    public ResistantState(Individual individual) {
        super(individual);
    }

    public ResistantState(Individual individual, State state) {
        super(individual);
        this.secondsToBecomeResistant = state.secondsToBecomeResistant;
    }

    @Override
    public void handle() {

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

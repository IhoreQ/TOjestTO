package pl.ibobek.virus_simulation.individual.State;

import javafx.scene.paint.Color;
import pl.ibobek.virus_simulation.individual.Individual;

public class SickWithSymptoms extends State {
    private final Color color = Color.RED;

    public SickWithSymptoms(Individual individual) {
        super(individual);
    }
    public SickWithSymptoms(Individual individual, State state) {
        super(individual);
        this.secondsToBecomeResistant = state.secondsToBecomeResistant;
    }

    @Override
    public void handle() {
        if (secondsToBecomeResistant <= 0)
            individual.setState(new ResistantState(individual));
        else
            secondsToBecomeResistant--;
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

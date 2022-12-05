package pl.ibobek.virus_simulation.individual.State;

import javafx.scene.paint.Color;
import pl.ibobek.virus_simulation.individual.Individual;

public class SickNoSymptoms extends State {
    private final Color color = Color.VIOLET;

    public SickNoSymptoms(Individual individual) {
        super(individual);
    }

    public SickNoSymptoms(Individual individual, State state) {
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

    public Color getColor() {
        return color;
    }

    @Override
    public int getSecondsToBecomeResistant() {
        return secondsToBecomeResistant;
    }
}

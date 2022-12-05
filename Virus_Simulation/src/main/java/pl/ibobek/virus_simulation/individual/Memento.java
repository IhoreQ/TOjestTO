package pl.ibobek.virus_simulation.individual;

import java.util.ArrayList;

public class Memento {
    private final ArrayList<Individual> population;

    public Memento(ArrayList<Individual> population) {
        this.population = population;
    }

    public ArrayList<Individual> getPopulation() {
        return population;
    }
}

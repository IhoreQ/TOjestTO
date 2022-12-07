package pl.ibobek.virus_simulation.simulation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pl.ibobek.virus_simulation.application.SimulationController;
import pl.ibobek.virus_simulation.individual.Individual;
import pl.ibobek.virus_simulation.individual.Memento;
import pl.ibobek.virus_simulation.individual.MoveController;
import pl.ibobek.virus_simulation.individual.State.*;
import pl.ibobek.virus_simulation.individual.Vector2D.Vector2D;

import java.util.*;

public class Simulation {

    private ArrayList<Individual> population = new ArrayList<>();
    private Pane area;
    public static int TIME_OF_INFECTION = 3 * (int)SimulationController.fps;

    public Simulation(Pane area, int populationSize, boolean initialCase) {

        this.area = area;
        for (int i = 0; i < populationSize; ++i) {
            population.add(new Individual(area, initialCase));
        }
        for (Individual individual : population) {
            HashMap<Individual, Integer> infectionTimes = new HashMap<>();
            for (Individual other : population) {
                infectionTimes.put(other, TIME_OF_INFECTION);
            }
            individual.setInfectionTimes(infectionTimes);
        }

        update(area);
    }

    public Memento save() {
        ArrayList<Individual> newPopulation = new ArrayList<>();

        Vector2D vector2D;
        State state;
        MoveController moveController;
        ArrayList<ArrayList<Integer>> distancesList = new ArrayList<>();
        Collection<Integer> distancesTemp;

        for (Individual i : population) {
            vector2D = i.getPosition();
            state = i.getState();
            moveController = i.getMoveController();

            distancesTemp = i.getInfectionTimes().values();
            distancesList.add(new ArrayList<>(distancesTemp));

            Individual individual = new Individual(new Vector2D(vector2D), new MoveController(moveController), i.getCircle(), i.getArea(), new HashMap<>());

            if (state.getColor() == Color.BLUE) {
                individual.setState(new HealthyUnresistantState(individual, state));
            }
            else if (state.getColor() == Color.RED) {
                individual.setState(new SickWithSymptoms(individual, state));
            }
            else if (state.getColor() == Color.VIOLET) {
                individual.setState(new SickNoSymptoms(individual, state));
            }
            else if (state.getColor() == Color.GREEN){
                individual.setState(new ResistantState(individual, state));
            }

            newPopulation.add(individual);
        }

        int i = 0;
        int j = 0;

        for (Individual individual : newPopulation) {
            individual.getInfectionTimes().clear();
            for (Individual ind : newPopulation) {
                if (individual != ind)
                    individual.getInfectionTimes().put(ind, distancesList.get(i).get(j++));
            }
            i++;
            j = 0;
        }

        return new Memento(newPopulation);
    }

    public void setPopulation(Memento memento) {
        this.population = memento.getPopulation();
        for (Individual individual : this.population) {
            individual.updatePosition();
            individual.updateCircleColor();
            this.area.getChildren().add(individual.getCircle());
        }
    }

    public ArrayList<Individual> getPopulation() {
        return population;
    }

    public void move() {
        for (Individual individual : population) {
            individual.checkDistance();
            individual.move();
        }
    }

    public void update(Pane area) {
        int random = new Random().nextInt(25) + 1;
        if (random == 5) {
            Individual newIndividual = new Individual(area, false);
            double x = Math.random() * (area.getWidth() - 2 * Individual.circleWidth);
            double y = Math.random() * (area.getHeight() - 2 * Individual.circleWidth);
            if (new Random().nextBoolean()) {
                if (new Random().nextBoolean())
                    newIndividual.setPosition(Individual.circleWidth * 2, y);
                else
                    newIndividual.setPosition(area.getWidth() - Individual.circleWidth * 2, y);
            } else {
                if (new Random().nextBoolean())
                    newIndividual.setPosition(x, Individual.circleWidth * 2);
                else
                    newIndividual.setPosition(x, area.getHeight() - Individual.circleWidth * 2);
            }

            HashMap<Individual, Integer> infectionTimes = new HashMap<>();
            for (Individual individual : population) {
                infectionTimes.put(individual, TIME_OF_INFECTION);
                individual.getInfectionTimes().put(newIndividual, TIME_OF_INFECTION);
            }
            newIndividual.setInfectionTimes(infectionTimes);
            population.add(newIndividual);
        }

        for (Individual individual : population) {
            individual.updatePosition();
        }
    }
}

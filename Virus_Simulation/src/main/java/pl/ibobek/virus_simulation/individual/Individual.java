package pl.ibobek.virus_simulation.individual;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pl.ibobek.virus_simulation.application.SimulationApplication;
import pl.ibobek.virus_simulation.individual.State.HealthyUnresistantState;
import pl.ibobek.virus_simulation.individual.State.SickNoSymptoms;
import pl.ibobek.virus_simulation.individual.State.SickWithSymptoms;
import pl.ibobek.virus_simulation.individual.State.State;
import pl.ibobek.virus_simulation.individual.Vector2D.Vector2D;

import java.util.HashMap;
import java.util.Random;

public class Individual {
    private Vector2D position;
    private State state;
    private MoveController moveController;
    private Circle circle;
    public static final int circleWidth = 4;
    private Pane area;
    private HashMap<Individual, Integer> infectionTimes;

    public Individual(Vector2D position, MoveController moveController, Circle circle, Pane area, HashMap<Individual, Integer> infectionTimes) {
        this.position = position;
        this.moveController = moveController;
        this.circle = circle;
        this.area = area;
        this.infectionTimes = infectionTimes;
    }

    public Individual(Pane area) {

        Random random = new Random();
        int randomNumber = random.nextInt(10) + 1;
        if (randomNumber == 5) {
            if (random.nextBoolean())
                this.state = new SickWithSymptoms(this);
            else
                this.state = new SickNoSymptoms(this);
        } else
            this.state = new HealthyUnresistantState(this);

        this.moveController = new MoveController();
        this.position = new Vector2D(area, circleWidth);
        this.circle = new Circle(circleWidth, state.getColor());
        area.getChildren().add(circle);
        this.area = area;
    }

    public void setInfectionTimes(HashMap<Individual, Integer> infectionTimes) {
        this.infectionTimes = infectionTimes;
    }

    public void setState(State state) {
        this.state = state;
        updateCircleColor();
    }

    public State getState() {
        return state;
    }

    public Vector2D getPosition() {
        return position;
    }

    public HashMap<Individual, Integer> getInfectionTimes() {
        return infectionTimes;
    }


    public void move() {
        collisionCheck();
        rollMovement();
        position.change(moveController);
    }

    private void rollMovement() {
        int random = new Random().nextInt(25) + 1;
        if (random == 5) {
            moveController = new MoveController();
        }
    }

    public void updatePosition() {
        circle.setTranslateX(position.getX());
        circle.setTranslateY(position.getY());
    }

    private void collisionCheck() {
        double x = position.getX();
        double y = position.getY();
        double radius = circle.getRadius();
        Random random = new Random();

        if (x < radius || x > area.getWidth() - radius) {
            if (random.nextBoolean())
                area.getChildren().remove(circle);
            else
                moveController.changeDx();
        }
        else if (y < radius || y > area.getHeight() - radius) {
            if (random.nextBoolean())
                area.getChildren().remove(circle);
            else
                moveController.changeDy();
        }
    }

    public void checkDistance() {
        getState().handle();
    }

    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    public Circle getCircle() {
        return circle;
    }

    public MoveController getMoveController() {
        return moveController;
    }

    public Pane getArea() {
        return area;
    }

    public void updateCircleColor() {
        circle.setFill(state.getColor());
    }
}

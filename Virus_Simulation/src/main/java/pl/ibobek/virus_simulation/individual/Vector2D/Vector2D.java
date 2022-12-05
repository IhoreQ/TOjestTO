package pl.ibobek.virus_simulation.individual.Vector2D;

import javafx.scene.layout.Pane;
import pl.ibobek.virus_simulation.application.SimulationController;
import pl.ibobek.virus_simulation.individual.MoveController;

public class Vector2D implements IVector {
    protected double x;
    protected double y;

    public Vector2D(Pane area, int circleWidth) {
        x = Math.random() * (area.getWidth() - 2 * circleWidth);
        y = Math.random() * (area.getHeight() - 2 * circleWidth);
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}

    public void change(MoveController moveController) {
        this.x += moveController.getDx() / 25 * SimulationController.scale;
        this.y += moveController.getDy() / 25 * SimulationController.scale;
    }

    public double getDistance(Vector2D param) {
        return Math.sqrt(Math.pow(x - param.getX(), 2) + Math.pow(y - param.getY(), 2));
    }

    @Override
    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public double cdot(IVector param) {
        double[] components = param.getComponents();

        return x * components[0] + y * components[1];
    }

    @Override
    public double[] getComponents() {
        double[] components = new double[2];
        components[0] = x;
        components[1] = y;
        return components;
    }

    @Override
    public String toString() {
        return "Wspolrzedne wektora w ukladzie kartezjanskim: [" + x + ", " + y + "]";
    }

}

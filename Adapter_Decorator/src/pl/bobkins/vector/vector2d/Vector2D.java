package pl.bobkins.vector.vector2d;

import pl.bobkins.vector.IVector;

public class Vector2D implements IVector {
    protected double x;
    protected double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
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

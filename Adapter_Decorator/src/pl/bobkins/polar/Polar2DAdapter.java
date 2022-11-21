package pl.bobkins.polar;

import pl.bobkins.vector.IVector;
import pl.bobkins.vector.vector2d.Vector2D;

public class Polar2DAdapter implements IPolar2D, IVector {
    private final Vector2D srcVector;

    public Polar2DAdapter(Vector2D srcVector) {
        this.srcVector = srcVector;
    }

    @Override
    public double getAngle() {
        double[] components = srcVector.getComponents();
        double x = components[0];
        double y = components[1];

        if (x == 0)
            return Math.toDegrees(Math.PI / 2);

        double tg = y / x;
        double angleInRadians = Math.abs(Math.atan(tg));

        if (x > 0 && y >= 0)
            return Math.toDegrees(angleInRadians);
        else if (x < 0 && y >= 0)
            return Math.toDegrees(angleInRadians + Math.PI / 2);
        else if (x < 0 && y < 0)
            return Math.toDegrees(angleInRadians + Math.PI);
        else
            return Math.toDegrees(angleInRadians + 3 * Math.PI / 2);
    }

    @Override
    public double abs() {
        return srcVector.abs();
    }

    @Override
    public double cdot(IVector param) {
        return srcVector.cdot(param);
    }

    @Override
    public double[] getComponents() {
        return srcVector.getComponents();
    }

    @Override
    public String toString() {
        return "Wektor Polar2DAdapter\n" + srcVector.toString() + "\n" + "Wspolrzedne wektora w ukladzie biegunowym: [" + abs() + ", " + getAngle() + "]";
    }
}

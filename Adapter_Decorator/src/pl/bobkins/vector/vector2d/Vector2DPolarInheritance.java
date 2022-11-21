package pl.bobkins.vector.vector2d;

public class Vector2DPolarInheritance extends Vector2D {

    public Vector2DPolarInheritance(double x, double y) {
        super(x, y);
    }

    public double getAngle() {

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
    public String toString() {
        return "Wektor 2DPolarInheritance:\n" + super.toString() + "\n" + "Wspolrzedne wektora w ukladzie biegunowym: [" + abs() + ", " + getAngle() + "]";
    }
}

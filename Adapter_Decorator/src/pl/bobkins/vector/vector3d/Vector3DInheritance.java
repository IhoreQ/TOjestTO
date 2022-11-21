package pl.bobkins.vector.vector3d;

import pl.bobkins.vector.IVector;
import pl.bobkins.vector.vector2d.Vector2D;

public class Vector3DInheritance extends Vector2D {

    private final double z;

    public Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double abs() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double cdot(IVector param) {
        double[] components = param.getComponents();

        if (components.length == 2)
            return x * components[0] + y * components[1];
        else
            return x * components[0] + y * components[1] + z * components[2];

    }

    public double[] getComponents() {
        double[] components = new double[3];
        components[0] = x;
        components[1] = y;
        components[2] = z;
        return components;
    }

    public Vector3DInheritance cross(IVector param) {
        double paramZ = 0;
        double[] paramComponents = param.getComponents();

        if (paramComponents.length == 3)
            paramZ = paramComponents[2];

        double paramX = paramComponents[0];
        double paramY = paramComponents[1];

        double newX = y * paramZ - z * paramY;
        double newY = z * paramX - x * paramZ;
        double newZ = x * paramY - y * paramX;

        return new Vector3DInheritance(newX, newY, newZ);
    }

    public IVector getSrcV() {
        return new Vector3DInheritance(x, y, z);
    }

    @Override
    public String toString() {
        return "Wektor 3DInheritance:\nWspolrzedne wektora w ukladzie kartezjanskim: [" + x + ", " + y + ", " + z + "]";
    }
}

package pl.bobkins.vector.vector3d;

import pl.bobkins.vector.IVector;
import pl.bobkins.vector.vector2d.Vector2D;

public class Vector3DDecorator implements IVector {
    private final IVector srcVector;
    private final double z;

    public Vector3DDecorator(IVector vector, double z) {
        this.srcVector = vector;
        this.z = z;
    }

    @Override
    public double abs() {
        double[] components = srcVector.getComponents();
        double x = components[0];
        double y = components[1];
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double cdot(IVector param) {
        double[] srcComponents = srcVector.getComponents();
        double[] paramComponents = param.getComponents();

        if (srcComponents.length == 2 || paramComponents.length == 2)
            return srcComponents[0] * paramComponents[0] + srcComponents[1] * paramComponents[1];
        else
            return srcComponents[0] * paramComponents[0] + srcComponents[1] * paramComponents[1] + srcComponents[2] * paramComponents[2];
    }

    @Override
    public double[] getComponents() {
        double[] srcComponents = srcVector.getComponents();
        double[] components = new double[3];
        components[0] = srcComponents[0];
        components[1] = srcComponents[1];
        components[2] = z;
        return components;
    }

    public Vector3DDecorator cross(IVector param) {
        double z = 0;
        double paramZ = 0;
        double[] srcComponents = srcVector.getComponents();
        double[] paramComponents = param.getComponents();

        double x = srcComponents[0];
        double y = srcComponents[1];

        if (srcComponents.length == 3)
            z = srcComponents[2];

        double paramX = paramComponents[0];
        double paramY = paramComponents[1];

        if (paramComponents.length == 3)
            paramZ = paramComponents[2];

        double newX = y * paramZ - z * paramY;
        double newY = z * paramX - x * paramZ;
        double newZ = x * paramY - y * paramX;

        Vector2D vector2D = new Vector2D(newX, newY);

        return new Vector3DDecorator(vector2D, newZ);
    }

    public IVector getSrcVector() {
        return srcVector;
    }

    @Override
    public String toString() {
        return "Wektor 3DDecorator:\nWspolrzedne wektora w ukladzie kartezjanskim: [" + srcVector.getComponents()[0] + ", " + srcVector.getComponents()[1] + ", " + z + "]";
    }
}

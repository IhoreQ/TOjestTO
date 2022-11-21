package pl.bobkins.vector;

public interface IVector {
    double abs();
    double cdot(IVector vector);
    double[] getComponents();
}

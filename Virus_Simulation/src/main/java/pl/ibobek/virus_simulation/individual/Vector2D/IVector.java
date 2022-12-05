package pl.ibobek.virus_simulation.individual.Vector2D;

public interface IVector {
    double abs();
    double cdot(IVector vector);
    double[] getComponents();
}

package pl.ibobek.virus_simulation.individual;

public class MoveController {
    private final double velocity = 2.5;
    private double dx;
    private double dy;

    public MoveController() {
        double direction = Math.random() * 2 * Math.PI;
        this.dx = Math.sin(direction) * Math.random() * velocity;
        this.dy = Math.cos(direction) * Math.random() * velocity;
    }

    public MoveController(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public MoveController(MoveController moveController) {
        this.dx = moveController.getDx();
        this.dy = moveController.getDy();
    }

    public double getVelocity() {
        return velocity;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void changeDx() {
        dx *= -1;
    }

    public void changeDy() {
        dy *= -1;
    }


}

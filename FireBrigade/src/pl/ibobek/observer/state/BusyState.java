package pl.ibobek.observer.state;

public class BusyState implements State {

    private final Car car;

    public BusyState(Car car) {
        this.car = car;
    }

    @Override
    public void handle() {
        car.changeState(new FreeState(car));
    }
}

package pl.ibobek.observer.state;

public class FreeState implements State {

    private final Car car;

    public FreeState(Car car) {
        this.car = car;
    }

    @Override
    public void handle() {
        car.changeState(new BusyState(car));
    }


}

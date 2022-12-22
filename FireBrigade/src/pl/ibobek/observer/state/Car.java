package pl.ibobek.observer.state;

public class Car {
    State state;
    int number;

    public Car(int number) {
        this.number = number;
        state = new FreeState(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void handle() {
        state.handle();
    }

    public int getNumber() {
        return number;
    }
}

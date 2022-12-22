package pl.ibobek.strategy;

import java.util.Random;

public class Fire implements Event {


    @Override
    public int getRequiredCars() {
        return 3;
    }

    @Override
    public boolean isFalse() {
        return new Random().nextDouble() < 0.05;
    }

    @Override
    public String toString() {
        return "Pożar!";
    }
}

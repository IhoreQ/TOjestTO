package pl.ibobek.strategy;

import java.util.Random;

public class OtherHazard implements Event {
    @Override
    public int getRequiredCars() {
        return 2;
    }

    @Override
    public boolean isFalse() {
        return new Random().nextDouble() < 0.05;
    }

    @Override
    public String toString() {
        return "Inne miejscowe zdarzenie!";
    }
}

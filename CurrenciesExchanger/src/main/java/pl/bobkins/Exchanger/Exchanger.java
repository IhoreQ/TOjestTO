package pl.bobkins.Exchanger;

import pl.bobkins.Currency.Currency;

public class Exchanger {

    private static Exchanger exchanger = null;

    private Exchanger() {}

    public static Exchanger getInstance() {
        if (exchanger == null)
            exchanger = new Exchanger();
        return exchanger;
    }

    public double exchange(Currency source, Currency target, double amount) {
        return amount * source.getRate() * source.getFactor() / target.getRate();
    }

}

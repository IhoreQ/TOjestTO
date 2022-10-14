package pl.bobkins.Currency.CurrencyData;

import pl.bobkins.Currency.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyData {

    private List<Currency> currencyList = new ArrayList<>();

    @Override
    public String toString() {

        String allObjects = "Name,\tCode,\tFactor,\tRate\n";

        for (Currency element : currencyList) {
            allObjects += element.getName() + ",\t" + element.getCode() + ",\t" + element.getFactor() + ",\t" + element.getRate() + "\n";
        }
        return allObjects;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

}

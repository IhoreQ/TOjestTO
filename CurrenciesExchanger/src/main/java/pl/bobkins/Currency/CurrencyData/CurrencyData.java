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

    //Zwraca referencję do listy obiektów Currency w klasie
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    //Zwraca pełen obiekt Currency z listy po podaniu obiektu Currency, z ustawionym kodem waluty
    public Currency getCurrencyByCode(Currency currency) {

        for (Currency element : currencyList) {
            if (element.getCode().equals(currency.getCode()))
                return element;
        }
        return null;
    }

}

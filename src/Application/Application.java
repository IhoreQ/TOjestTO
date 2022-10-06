package Application;

import Currency.Currency;
import Currency.CurrencyData.CurrencyData;
import Exchanger.Exchanger;
import XMLProvider.XMLProvider;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private Exchanger exchanger;
    private XMLProvider xmlProvider;
    private CurrencyData currencyData;
    private Scanner scanner;

    public Application() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        xmlProvider = new XMLProvider();
        exchanger = new Exchanger();
    }

}

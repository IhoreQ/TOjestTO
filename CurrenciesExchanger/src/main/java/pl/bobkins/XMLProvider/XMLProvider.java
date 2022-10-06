package pl.bobkins.XMLProvider;

import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import pl.bobkins.Currency.Currency;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pl.bobkins.Currency.CurrencyData.CurrencyData;

public class XMLProvider {

    public void provideData(CurrencyData currencyData) {

        String address = "https://www.nbp.pl/kursy/xml/lasta.xml";
        Document document;
        try {
            document = new SAXReader().read(new URL(address));
        } catch (DocumentException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Element classElement = document.getRootElement();
        List<Node> nodes = classElement.selectNodes("pozycja");

        String name = "";
        String code = "";
        double factor, rate;

        for (Node node : nodes) {
            name = node.selectSingleNode("nazwa_waluty").getText();
            factor = Double.parseDouble(node.selectSingleNode("przelicznik").getText());
            code = node.selectSingleNode("kod_waluty").getText();
            rate = Double.parseDouble(node.selectSingleNode("kurs_sredni").getText().replace(',', '.'));
            currencyData.getCurrencyList().add(new Currency(name, code, factor, rate));
        }
        addZloty(currencyData);
    }

    public void addZloty(CurrencyData currencyData) {
        currencyData.getCurrencyList().add(new Currency("Złoty nowy", "PLN", 1, 1.0));
    }
}
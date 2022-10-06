package pl.bobkins.Application;

import pl.bobkins.Currency.Currency;
import pl.bobkins.Currency.CurrencyData.CurrencyData;
import pl.bobkins.Exchanger.Exchanger;
import pl.bobkins.XMLProvider.XMLProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Application {
    private JPanel mainPanel;
    private JComboBox sourceComboBox;
    private JComboBox targetComboBox;
    private JTextField amountTextField;
    private JButton exchangeButton;
    private JTextField resultTextField;
    private JLabel sourceLabel;
    private JLabel targetLabel;
    private JLabel amountLabel;


    private Exchanger exchanger;
    private CurrencyData currencyData;
    private XMLProvider xmlProvider;
    private static Application application;

    private static void init() {
        application.exchanger = Exchanger.getInstance();
        application.currencyData = new CurrencyData();
        application.xmlProvider = new XMLProvider();
    }

    private static void getCurrencyData() {
        application.xmlProvider.provideData(application.currencyData);
        List<Currency> currencyList = application.currencyData.getCurrencyList();
        for (Currency currency : currencyList) {
            application.sourceComboBox.addItem(currency);
            application.targetComboBox.addItem(currency);
        }
    }

    public Application() {

        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountTextField.getText());
                    Currency sourceCurrency = (Currency) sourceComboBox.getSelectedItem();
                    Currency targetCurrency = (Currency) targetComboBox.getSelectedItem();

                    double result = application.exchanger.exchange(sourceCurrency, targetCurrency, amount);
                    String formattedResult = String.format("%.3f", result);
                    resultTextField.setText(formattedResult);
                    amountTextField.setText("");

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Zły format liczby!");
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Kantor wymiany walut");
        application = new Application();
        mainFrame.setContentPane(application.mainPanel);
        init();
        getCurrencyData();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}

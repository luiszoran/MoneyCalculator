package moneycalculator.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.Model.Currency;
import moneycalculator.Model.CurrencySet;

public class ConsoleCurrencyDialog implements CurrencyDialog {

    private Currency currency;

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public void execute() {
            String line=null;
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introducir divisa:");
            try {
                line = readCurrency(reader);
            } catch (IOException ex) {
                Logger.getLogger(ConsoleCurrencyDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            Currency[] currencies = CurrencySet.getInstance().search(line);
            if (check(currencies)) {
                break;
            }
        }
    }

    private String readCurrency(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private boolean check(Currency[] currencies) {
        if (currencies.length == 0) {
            System.out.println("Moneda no encontrada");
            return false;
        }

        if (currencies.length == 1) {
            currency = currencies[0];
            return true;
        }

        if (currencies.length > 1) {
            System.out.println("Hay múltiples coincidencias para esa divisa: ");
            for (Currency curr : currencies) {
                System.out.println(curr);
            }
            return false;
        }
        return false;
    }
}

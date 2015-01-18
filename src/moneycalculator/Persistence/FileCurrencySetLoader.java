package moneycalculator.Persistence;

import java.util.Arrays;
import moneycalculator.Model.Currency;
import moneycalculator.Model.CurrencySet;

public class FileCurrencySetLoader {
    
    private static FileCurrencySetLoader instance;

    private FileCurrencySetLoader() {
    }

    public static FileCurrencySetLoader getInstance() {
        if (instance == null) {
            instance = new FileCurrencySetLoader();
        }
        return instance;
    }

    public void load(Currency[] currencies) {
        CurrencySet set = CurrencySet.getInstance();
        set.addAll(Arrays.asList(currencies));
    }  
}

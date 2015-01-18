package moneycalculator.Persistence;

import java.util.Date;
import moneycalculator.Model.Currency;
import moneycalculator.Model.ExchangeRate;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Date date, Currency to, Currency from);
    
    public ExchangeRate load(Currency to, Currency from);
    
}

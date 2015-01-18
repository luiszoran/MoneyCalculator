package moneycalculator.Model;

public class MoneyExchanger {
    public static Money exchange(Money money, ExchangeRate exchangeRate){
        
        return new Money(exchangeRate.getTo(),money.getAmount().multiply(exchangeRate.getRate()));
        
    }
}
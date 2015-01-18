package moneycalculator.Model;

public class Money {

    private final Number amount;
    private final Currency currency;

    public Money(Currency currency, Number amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Number getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
    
    @Override
    public String toString() {
        return amount.getNumerator()/amount.getDenominator() + " " + currency.getCode();
    }
}

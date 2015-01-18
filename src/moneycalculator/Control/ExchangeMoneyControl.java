package moneycalculator.Control;

import java.io.FileNotFoundException;
import moneycalculator.Model.Currency;
import moneycalculator.Model.ExchangeRate;
import moneycalculator.Model.Money;
import moneycalculator.Persistence.ExchangeRateLoader;
import moneycalculator.UI.CurrencyDialog;
import moneycalculator.UI.MoneyDialog;
import moneycalculator.UI.MoneyViewer;

public class ExchangeMoneyControl {

    ExchangeRateLoader exchangeRateLoader;
    MoneyDialog moneyDialog;
    CurrencyDialog currencyDialog;
    MoneyViewer moneyViewer;

    public ExchangeMoneyControl(ExchangeRateLoader exchangeRateLoader, MoneyDialog moneyDialog, CurrencyDialog currencyDialog, MoneyViewer moneyViewer) {
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
    }


    public ExchangeRate execute() throws FileNotFoundException {
        Money money = readMoney();
        Currency currency = readCurrency();
        ExchangeRate exchangeRate = exchangeRateLoader.load(money.getCurrency(), currency);
        return exchangeRate;
    //    moneyViewer.show(MoneyExchanger.exchange(money, exchangeRate));
    }

    private Money readMoney() {
        return moneyDialog.getMoney();
    }

    private Currency readCurrency() {
        return currencyDialog.getCurrency();
    }
}
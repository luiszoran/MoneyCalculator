package moneycalculator.Persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.Model.Currency;
import moneycalculator.Model.ExchangeRate;
import moneycalculator.Model.Number;

public class FileExchangeRateLoader implements ExchangeRateLoader {

    String fileRates;

    public FileExchangeRateLoader(String fileRates) {
        this.fileRates = fileRates;
    }

    @Override
    public ExchangeRate load(Date date, Currency to, Currency from) {
        try {
            return new ExchangeRate(date, from, to, calculateRate(readFileTo(to), readFileFrom(from)));
        } catch (IOException ex) {
            Logger.getLogger(FileExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(new Date(), from, to, calculateRate(readFileTo(to), readFileFrom(from)));
        } catch (IOException ex) {
            Logger.getLogger(FileExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Number calculateRate(String rateTo, String rateFrom) {
        return new Number(Double.parseDouble(rateTo) / Double.parseDouble(rateFrom));
    }

    private String readFileTo(Currency to) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileRates)));
        String line;
        while (true) {
            line = reader.readLine();
            if (line == null)
                break;
            if (line.split(" ")[1].equals(to.getCode()))
                return line.split(" ")[0];
        }
        return null;
    }

    private String readFileFrom(Currency from) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileRates)));
        String line;
        while (true) {
            line = reader.readLine();
            if (line == null)
                break;
            if (line.split(" ")[1].equals(from.getCode()))
                return line.split(" ")[0];
        }
        return null;
    }
}
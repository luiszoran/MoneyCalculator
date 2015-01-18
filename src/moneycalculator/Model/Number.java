package moneycalculator.Model;

public class Number {

    private long numerator, denominator;

    public Number(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    public Number(int numerator) {
        this(numerator, 1);
    }

    public Number(long numerator) {
        this(numerator, 1);
    }

    public Number(double number) {
        this(fromDouble(number));
    }

    public Number(Number number) {
        this(number.numerator, number.denominator);
    }

    public double getNumerator() {
        return (double) numerator;
    }

    public double getDenominator() {
        return (double) denominator;
    }

    private void reduce() {
        int[] primeNumbers = getPrimeNumbers();
        for (int prime : primeNumbers) {
            if (this.numerator < prime) {
                break;
            }
            if (this.denominator < prime) {
                break;
            }
            reduce(prime);
        }
    }

    private int[] getPrimeNumbers() {
        return new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    }

    private void reduce(int prime) {
        while (this.isDivisible(prime)) {
            numerator /= prime;
            denominator /= prime;
        }
    }

    private boolean isDivisible(int prime) {
        return ((numerator % prime == 0) && (denominator % prime == 0));
    }

    public static Number fromDouble(double number) {
        long denominator = 1;
        while ((long) number != number) {
            number *= 10;
            denominator *= 10;
        }
        return new Number((long) number, denominator);
    }

    public Number add(Number number) {
        if (denominator == number.denominator) {
            return new Number(numerator + number.numerator, denominator);
        } else {
            return new Number(number.denominator * numerator + denominator * number.numerator, denominator * number.denominator);
        }
    }

    public Number substract(Number number) {
        if (denominator == number.denominator) {
            return new Number(numerator - number.numerator, denominator);
        } else {
            return new Number(number.denominator * numerator - denominator * number.numerator, denominator * number.denominator);
        }
    }

    public Number multiply(Number number) {
        return new Number(this.numerator * number.numerator, this.denominator * number.denominator);
    }

    public Number divide(Number number) {
        return new Number(numerator * number.denominator, denominator * number.numerator);
    }

    @Override
    public String toString() {
        return ("Numerador: " + getNumerator() + "\nDenominador: " + getDenominator());
    }
}
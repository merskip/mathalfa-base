package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.elementary.RationalNumber;

public class NumberParser {

    private NumberParser() { }

    public static RationalNumber parse(String number) {
        String[] numbers = number.split("/", 2);
        int numerator = Integer.parseInt(numbers[0]);
        int denominator = Integer.parseInt(numbers[1]);
        return new RationalNumber(numerator, denominator);
    }
}

package com.shpp.p2p.cs.vomelianchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;
import java.util.Collections;

/**
 * File: Assignment5Part2.java
 * The program adds large positive integers and outputs the result to the console
 */
public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        char[] chars1 = n1.toCharArray();
        char[] chars2 = n2.toCharArray();
        ArrayList<Character> resultChars;

        if (chars1.length >= chars2.length) {
            resultChars = sumOfNumbers(chars1, chars2);
        } else {
            resultChars = sumOfNumbers(chars2, chars1);
        }

        return convertToString(resultChars);
    }

    /**
     * Adds numbers represented in character arrays
     * and returns the sum as a character array
     *
     * @param charsMore A longer character array
     * @param charsLess An array of characters that has a smaller length
     * @return The sum of numbers in the form of an array of characters
     */
    private ArrayList<Character> sumOfNumbers(char[] charsMore, char[] charsLess) {
        ArrayList<Character> resultChars = new ArrayList<>();
        boolean isUnitInTheDigit = false;
        int sum;

        // passes through a cycle where the digits of the numbers match
        for (int i = charsLess.length - 1, j = charsMore.length - 1; i >= 0; i--, j--) {
            sum = addingNumbers(charsMore[j], charsLess[i], isUnitInTheDigit);
            resultChars.add(calculationSymbol(sum));
            isUnitInTheDigit = sum >= 10;
        }

        // passes an array of numbers where they are only in a larger array
        for(int i = charsMore.length - charsLess.length - 1; i >= 0; i--) {
            sum = addingNumber(charsMore[i], isUnitInTheDigit);
            resultChars.add(calculationSymbol(sum));
            isUnitInTheDigit = sum >= 10;
        }

        // adds one to the array if it has moved since the last addition
        if(isUnitInTheDigit) {
            resultChars.add(Character.forDigit(1, 10));
        }

        Collections.reverse(resultChars);
        return resultChars;
    }

    /**
     * Converts two characters into an integer and calculates the sum
     *
     * @param digit1 The first character is a number
     * @param digit2 The second character is a number
     * @param isUnitInTheDigit Did the unit move from the previous addition
     * @return Sum of digits
     */
    private int addingNumbers(char digit1, char digit2, boolean isUnitInTheDigit) {
        int num1 = Character.digit(digit1, 10);
        int num2 = Character.digit(digit2, 10);
        int sum = num1 + num2;
        if (isUnitInTheDigit) sum++;

        return sum;
    }

    /**
     * Converts a symbol to a number and adds it to the sum
     * @param digit The character is a number
     * @param isUnitInTheDigit Did the unit move from the previous addition
     * @return Sum of digit
     */
    private int addingNumber(char digit, boolean isUnitInTheDigit) {
        int sum = Character.digit(digit, 10);
        if(isUnitInTheDigit) sum++;

        return sum;
    }

    /**
     * Calculates from a number exactly which digit needs
     * to be written down and converts it into a symbol
     *
     * @param sum Sum of digits
     * @return character-digit to be written in a digit
     */
    private Character calculationSymbol(int sum) {
        if (sum >= 10) {
            return Character.forDigit(sum % 10, 10);
        }

        return Character.forDigit(sum, 10);
    }

    /**
     * Converts an array of characters to a string
     *
     * @param resultChars Array of characters
     * @return A string composed of an array of characters
     */
    private String convertToString(ArrayList<Character> resultChars) {
        StringBuilder stringBuilder = new StringBuilder(resultChars.size());
        for (Character ch : resultChars) {
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}

package com.shpp.p2p.cs.vomelianchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File: Assignment5Part4.java
 * The program performs csv parsing and outputs information from the given column
 */
public class Assignment5Part4 extends TextProgram {
    
    public void run() {
        println(extractColumn("country.csv",0));
    }

    /**
     * Reads information from a file line by line
     * and then selects information from a given column
     *
     * @param filename Сsv file name
     * @param columnIndex Information column index
     * @return An array of information corresponding to the given column
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> column = new ArrayList<>();
        /* Open the file for reading. */
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (true) {
                String line = br.readLine();
                if (line == null) break;
                ArrayList<Integer> indexComa = calculationSeparators(line);
                column.add(getColumnExpression(line, indexComa, columnIndex));
            }
        } catch (IOException e) {
            return null;
        }
        return column;
    }

    /**
     * Calculates all separators from a string
     * and writes them into an array
     *
     * @param line A line of information
     * @return An array with indices of information separators
     */
    private ArrayList<Integer> calculationSeparators(String line) {
        ArrayList<Integer> indexesComa = new ArrayList<>();
        int index = -1;

        while (true) {
            // for cases where there are no quotes in the line
            if (!line.contains("\"")) {
                index = line.indexOf(",", index + 1);
                if (index == -1) break;
                indexesComa.add(index);

            } else {
                while (true) {
                    index = line.indexOf(",", index + 1);
                    if(index == -1) break;
                    if (commaOutsideQuotes(line, index)) {
                        indexesComa.add(index);
                    }
                }
            }
            if (index == -1) break;
        }
        indexesComa.add(line.length());

        return indexesComa;
    }

    /**
     * Checks whether a comma is not inside quotes,
     * that is, whether a comma is a separator
     * or encoded information
     *
     * @param line A line of information
     * @param index Comma index
     * @return There is a separator or information
     */
    private boolean commaOutsideQuotes(String line, int index) {
        // indices of beginning and ending quotes
        int indexStart;
        int indexEnd = -1;
        boolean isInQuotes = false;

        while (true) {
            indexStart = line.indexOf("\"", indexEnd + 1);
            indexEnd = line.indexOf("\"", indexStart + 1);
            if (indexStart == -1 || indexEnd == -1) break;

            // check for double quotes
            if (indexStart == indexEnd - 1){
                continue;
            }

            // checking whether there is a comma in quotation marks
            if ((index > indexStart && index < indexEnd)) {
                isInQuotes = true;
                break;
            }

        }
        return !isInQuotes;
    }

    /**
     * The expression that belongs to this column is obtained from the given line
     *
     * @param line A line of information
     * @param indexComa An array of delimiter indices
     * @param columnIndex Column index
     * @return Expression from a line that lies in a certain column
     */
    private String getColumnExpression(String line, ArrayList<Integer> indexComa, int columnIndex) {
        String expression = null;
        int numberExpression = 0;
        int startIndex = 0;
        for (Integer index : indexComa) {
            if (numberExpression == columnIndex) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = startIndex; i < index; i++) {
                    if (line.charAt(i) != '"') {
                        stringBuilder.append(line.charAt(i));
                    } else {
                        int countQuotes = amountQuotes(line, i);
                        i += (countQuotes - 1);
                        countQuotes /= 2;
                        stringBuilder.append("\"".repeat(Math.max(0, countQuotes)));
                    }
                }
                expression = stringBuilder.toString();
            }
            startIndex = index + 1;
            numberExpression++;
        }
        return expression;
    }

    /**
     * Checks if this is an encoded parenthesis in the information
     *
     * @param line A line information
     * @param i Index of quotation marks
     * @return Number of quotes
     */
    private int amountQuotes(String line, int i) {
        int countQuotes = 0;
        while (i < line.length()) {
            if (line.charAt(i) != '"') break;
            countQuotes++;
            i++;
        }
        return countQuotes;
    }
}

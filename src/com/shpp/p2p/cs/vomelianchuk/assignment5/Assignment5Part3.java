package com.shpp.p2p.cs.vomelianchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File: Assignment5Part3.java
 * Takes input from the user and outputs all words in the English language
 * that have those three letters in the given order
 */
public class Assignment5Part3 extends TextProgram {
    // The name of the English dictionary file
    private static final String ENGLISH_WORDS = "en-dictionary.txt";

    // Constant, the number of letters specified by the user
    private static final int COUNTER_THREE_LETTERS = 3;

    public void run() {
        // All words from the dictionary will be saved
        ArrayList<String> words = new ArrayList<>();

        try {
            words = readTheFile();
        } catch (IOException e) {
            System.err.println("""
                    IOException!!!
                    Fix the error!
                    Try again!""");
        }

        while (true) {
            char[] letters = enterUserData();
            searchWords(words, letters);
        }

    }

    /**
     * Reads all words from the file (English dictionary)
     *
     * @return An array of words from the dictionary
     * @throws IOException Throws an invalid filename exception
     */
    private ArrayList<String> readTheFile() throws IOException {
        ArrayList<String> englishWords = new ArrayList<>();
        /* Open the file for reading. */
        BufferedReader br = new BufferedReader(new FileReader(ENGLISH_WORDS));

        while (true) {
            String englishWord = br.readLine();
            if (englishWord == null) break;
            englishWords.add(englishWord);
        }
        br.close();

        return englishWords;
    }

    /**
     * Reads a string of three letters entered by the user
     * and converts them into an array of characters
     *
     * @return An array of three letters
     */
    private char[] enterUserData() {
        String letters = readLine("Enter three letters: ");
        letters = letters.toLowerCase();
        return letters.toCharArray();
    }

    /**
     * Searches for words that meet a given condition
     * given the letters entered by the user
     * and prints them to the console
     *
     * @param words An array of words from the dictionary
     * @param letters An array of three letters
     */
    private void searchWords(ArrayList<String> words, char[] letters) {
        for (String string : words) {
            if (checkWord(string.toCharArray(), letters)) {
                println(string);
            }
        }
    }

    /**
     * Checks the word whether it satisfies the data entered by the user
     * and the condition of the problem
     *
     * @param word The word being checked is presented as an array
     * @param letters An array of three letters entered by the user
     * @return The word meets the condition of the task or not
     */
    private boolean checkWord(char[] word, char[] letters) {
        // index of literature in the word from which to start checking for matches
        int indexLetter = 0;
        // counts the number of matches of letters with letters in a word
        int countCoincidence = 0;

        for (char letter : letters) {
            // the variable corresponds to whether the given letter is present in the word
            boolean isLetter = false;
            for (int j = indexLetter; j < word.length; j++) {
                if (letter == word[j]) {
                    // appropriates an index from a letter, at the next iteration it will start checking
                    indexLetter = j + 1;
                    isLetter = true;
                    countCoincidence++;
                }
                if (isLetter) break;
            }
            if (!isLetter) break;
        }

        return countCoincidence == COUNTER_THREE_LETTERS;
    }
}

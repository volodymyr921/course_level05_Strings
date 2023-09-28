package com.shpp.p2p.cs.vomelianchuk.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * File: Assignment5Part1.java
 * The program calculates the number of syllables in a word.
 * Input receives a word from the user,
 * output displays the number of syllables in the word
 */
public class Assignment5Part1 extends TextProgram {
    public void run() {
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        char[] letters = word.toLowerCase().toCharArray();
        char[] vowels = {'a', 'o', 'e', 'i', 'y', 'u'};

        // Vowel index, initial value 2, to go beyond the possible values of 'i - 1'
        int indexVowel = -2;

        return countNumberOfSyllables(letters, vowels, indexVowel);

    }

    /**
     * Gets an array of characters in a word,
     * then compares them with an array of vowels
     * and thus counts the number of syllables
     *
     * @param letters Array of letters of the given word
     * @param vowels An array of vowels
     * @param indexVowel Index of the array where the preceding vowel is located
     * @return The number of syllables in a word
     */
    private int countNumberOfSyllables(char[] letters, char[] vowels, int indexVowel) {
        int numberOfSyllables = 1;

        for (int i = 0; i < letters.length; i++) {
            for (char vowel : vowels) {
                if (letters[i] == vowel) {
                    if (notIsVowelBefore(indexVowel, i) && !isLastVowelE(letters.length, letters[i], i)) {
                        numberOfSyllables++;
                    }
                    indexVowel = i;
                }
            }
        }

        /* Check whether there is no word,
         there is not one syllable ending in e */
        if(numberOfSyllables > 1) {
            numberOfSyllables--;
        }

        return numberOfSyllables;
    }

    /**
     * Checks whether the last letter is 'e'
     *
     * @param length Word length
     * @param letter Letter in the word
     * @param i The index of the current vowel
     * @return Is the last letter 'e' or not
     */
    private boolean isLastVowelE(int length, char letter, int i) {
        return (i == length - 1 && letter == 'e');
    }

    /**
     * Checks whether a vowel is preceded by a vowel
     *
     * @param indexVowel Index of the last vowel
     * @param i The index of the current vowel
     * @return Is there a vowel before a vowel
     */
    private boolean notIsVowelBefore(int indexVowel, int i) {
        return indexVowel != i - 1;
    }
}

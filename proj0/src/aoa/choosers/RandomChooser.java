package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        if(wordLength < 1){
            throw new IllegalArgumentException("word length should bigger than 1!");
        }
        List<String> words =  FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if(words.isEmpty()){
            throw new IllegalStateException("no word match the length!");
        }
        int numWords = words.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomlyChosenWordNumber);

        char[] charArray = new char[wordLength];
        Arrays.fill(charArray, '-');
        pattern = new String(charArray, 0, wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        StringBuilder newPattern = new StringBuilder(pattern);
        int occurrences = 0;
        for(int i = 0; i < chosenWord.length(); i++){
            if(letter == chosenWord.charAt(i)){
                occurrences += 1;
                newPattern.setCharAt(i, letter);
            }
        }
        pattern = newPattern.toString();
        return occurrences;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }
}

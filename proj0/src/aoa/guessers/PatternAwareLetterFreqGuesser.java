package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public Map<Character, Integer> getFreqMapThatMatchesPattern(String pattern){
        List<String> newWords = new ArrayList<>();
        for(String word:words){
            if(LFGHelper.simpleMatchPattern(pattern, word)){
                newWords.add(word);
            }
        }
        return LFGHelper.getFreqMapThatMatchesPattern(newWords);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMapThatMatchesPattern = getFreqMapThatMatchesPattern(pattern);

        if(freqMapThatMatchesPattern.isEmpty())
            return '?';
        for(Character guessChar:guesses){
            freqMapThatMatchesPattern.remove(guessChar);
        }
        return LFGHelper.getMostFreqChar(freqMapThatMatchesPattern);
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("D:/Project/JavaProject/61b2023/skeleton-sp23/proj0/data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}
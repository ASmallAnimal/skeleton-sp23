package aoa.guessers;

import aoa.utils.FileUtils;
import org.antlr.v4.runtime.tree.Tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = LFGHelper.getFreqMapThatMatchesPattern(words);
        return freqMap;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freqMap = getFrequencyMap();

        if(freqMap.isEmpty())
            return '?';
        for(Character guessChar:guesses){
            freqMap.remove(guessChar);
        }
        //        int count = 0;
//        char res = 'a';
//        for(Map.Entry<Character,Integer> entry : freqMap.entrySet()){
//            if(count < entry.getValue()){
//                count = entry.getValue();
//                res = entry.getKey();
//            }
//        }
        return LFGHelper.getMostFreqChar(freqMap);
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("D:\\Project\\JavaProject\\61b2023\\skeleton-sp23\\proj0\\data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}

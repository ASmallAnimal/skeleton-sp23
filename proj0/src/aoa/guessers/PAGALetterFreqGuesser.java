package aoa.guessers;

import aoa.utils.FileUtils;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List<String> newWords = new ArrayList<>();

        for (String word:words){
            if(guesses.isEmpty()){
                newWords.add(word);
            }
            boolean flag = true;
            for(Character c:guesses){
                if(pattern.indexOf(c) != word.indexOf(c)){
                    flag = false;
                    break;
                }
            }
            if(flag && LFGHelper.matchPattern(pattern, word)){
                newWords.add(word);
            }
        }

        Map<Character, Integer> freqPAGAMap = LFGHelper.getFreqMapThatMatchesPattern(newWords);
        if(freqPAGAMap.isEmpty())
            return '?';
        for(Character guessChar:guesses){
            freqPAGAMap.remove(guessChar);
        }
        return LFGHelper.getMostFreqChar(freqPAGAMap);
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("D:/Project/JavaProject/61b2023/skeleton-sp23/proj0/data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}

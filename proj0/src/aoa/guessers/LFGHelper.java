package aoa.guessers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LFGHelper {
    public static List<Character> getCharInPattern(String pattern){
        List<Character> charInPattern = new ArrayList<>();
        for(char c:pattern.toCharArray()){
            if(c != '-')
                charInPattern.add(c);
        }
        return charInPattern;
    }
    public static boolean simpleMatchPattern(String pattern, String word){
        if(pattern.length() != word.length())
            return false;
        List<Character> charInPattern = getCharInPattern(pattern);
        for (int i = 0; i<pattern.length(); i++){
            if(pattern.charAt(i) == '-') {
                continue;
            }
            if(pattern.charAt(i) != word.charAt(i))
                return false;
        }
        return true;
    }
    public static boolean matchPattern(String pattern, String word){
        if(pattern.length() != word.length())
            return false;
        List<Character> charInPattern = getCharInPattern(pattern);
        for (int i = 0; i<pattern.length(); i++){
            if(pattern.charAt(i) == '-') {
                for (Character c : charInPattern) {
                    if (word.charAt(i) == c) {
                        return false;
                    }
                }
                continue;
            }
            if(pattern.charAt(i) != word.charAt(i))
                return false;
        }
        return true;
    }
    public static Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> words){
        Map<Character, Integer> freqMapThatMatchesPattern = new HashMap<>();
        for(String word:words){
            for(char c:word.toCharArray()){
                if(!freqMapThatMatchesPattern.containsKey(c)){
                    freqMapThatMatchesPattern.put(c,1);
                }
                else {
                    freqMapThatMatchesPattern.put(c,freqMapThatMatchesPattern.get(c)+1);
                }
            }
        }
        return freqMapThatMatchesPattern;
    }

    public static char getMostFreqChar(Map<Character, Integer> map){
        int count = 0;
        char res = 'z';
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(count < entry.getValue()){
                count = entry.getValue();
                res = entry.getKey();
                continue;
            }
            if(count == entry.getValue() && res >entry.getKey()){
                res = entry.getKey();
            }
        }
        return res;
    }

}

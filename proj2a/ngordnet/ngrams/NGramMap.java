package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private TreeMap<String, TimeSeries>  wordsMap;
    private TimeSeries countsMap;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(){

    }
    public NGramMap(String wordsFilename, String countsFilename) {
        wordsMap = new TreeMap<>();
        In inWords = new In(wordsFilename);
        TimeSeries ts = new TimeSeries();
        String[] line = inWords.readLine().split("[\t ]+");
        String word = line[0];
        ts.put(Integer.parseInt(line[1]), Double.parseDouble(line[2]));
        while(inWords.hasNextLine()){
            line = inWords.readLine().split("[\t ]+");
            if(!Objects.equals(word, line[0])){
                wordsMap.put(word, ts);
                ts = new TimeSeries();
                word = line[0];
            }
            ts.put(Integer.parseInt(line[1]), Double.parseDouble(line[2]));
        }
        wordsMap.put(word, ts);

        countsMap = new TimeSeries();
        In inCounts = new In(countsFilename);
        while(inCounts.hasNextLine()){
            line = inCounts.readLine().split(",");
            countsMap.put(Integer.parseInt(line[0]), Double.parseDouble(line[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordsMap.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        TimeSeries tsRes = new TimeSeries();
        tsRes.putAll(wordsMap.get(word));
        return tsRes;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return countsMap;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordsMap.get(word), startYear, endYear).dividedBy(countsMap);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return wordsMap.get(word).dividedBy(countsMap);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        return getTimeSeries(words, startYear, endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return getTimeSeries(words, MIN_YEAR, MAX_YEAR);
    }

    private TimeSeries getTimeSeries(Collection<String> words, int minYear, int maxYear) {
        TimeSeries tsRes = new TimeSeries();
        for(String word : words){
            tsRes = tsRes.plus(weightHistory(word, minYear, maxYear));
        }
        return tsRes;
    }
}

package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    // TODO: Add any necessary static/instance variables.

    private TreeMap<Integer, Double> ts;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for(Map.Entry<Integer, Double> entry: ts.entrySet()){
            if(entry.getKey() >= startYear && entry.getKey() <= endYear)
                this.put(entry.getKey(), entry.getValue());
        }
        // TODO: Fill in this constructor.
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        return new ArrayList<>(this.keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        return new ArrayList<>(this.values());
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries tsRes = new TimeSeries(ts, MIN_YEAR, MAX_YEAR);
        for(Map.Entry<Integer, Double> entry: this.entrySet()){
            if(tsRes.containsKey(entry.getKey())){
                tsRes.put(entry.getKey(), tsRes.get(entry.getKey()) + entry.getValue());
            }
            else {
                tsRes.put(entry.getKey(), entry.getValue());
            }
        }
        return tsRes;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries tsRes = new TimeSeries(this, MIN_YEAR, MAX_YEAR);
        for(Map.Entry<Integer, Double> entry: this.entrySet()){
            if(ts.containsKey(entry.getKey())){
                tsRes.put(entry.getKey(), entry.getValue() / ts.get(entry.getKey()));
            }
            else {
                throw new IllegalArgumentException("given TS missing a year that exists in this TimeSeries");
            }
        }
        return tsRes;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}

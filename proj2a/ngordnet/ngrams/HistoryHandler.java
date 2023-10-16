package ngordnet.ngrams;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;


public class HistoryHandler extends NgordnetQueryHandler {
    NGramMap ngram = new NGramMap();
    public HistoryHandler(NGramMap ngram){
        this.ngram = ngram;
    }
    @Override
    public String handle(NgordnetQuery q) {
        System.out.println("Got query that looks like:");
        System.out.println("Words: " + q.words());
        System.out.println("Start Year: " + q.startYear());
        System.out.println("End Year: " + q.endYear());

        System.out.println("But I'm totally ignoring that and just plotting a parabola\n" +
                "and a sine wave, because your job will be to figure out how to\n" +
                "actually use the query data.");
        TimeSeries ts = new TimeSeries();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for(String word : q.words()){
            ts = ngram.countHistory(word, q.startYear(), q.endYear());
            labels.add(word);
            lts.add(ts);
        }

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);

        return Plotter.encodeChartAsString(chart);
    }
}

package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;
import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;

import java.util.List;
import java.util.Map;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap ngram = new NGramMap();
    public HistoryTextHandler(NGramMap ngram){
        this.ngram = ngram;
    }
    @Override
    public String handle(NgordnetQuery q) {
        String response = "You entered the following info into the browser:\n";
        for(String word : q.words()){
            response += q.words() + ": {";
            for(Map.Entry<Integer, Double> entry :
                            ngram.countHistory(word, q.startYear(), q.endYear()).entrySet()){
                response += entry.getKey().toString() + "=" + entry.getValue().toString() + ", ";
            }
            response = response.substring(0, response.length() - 1) + "}\n";
        }
        return response;
    }
}


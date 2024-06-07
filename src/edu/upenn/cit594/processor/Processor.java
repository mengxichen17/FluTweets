package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.CSVFileReader;
import edu.upenn.cit594.datamanagement.JSONFileReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.TabSeparatedFileReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {

    protected Reader reader;
    protected List<Tweet> tweets;
    protected List<State> states;
//    protected String log_file_name;


    public Processor(String filename, String state_file_name) {
        if (filename.toLowerCase().endsWith(".json")) {
            this.reader = new JSONFileReader(filename);
        } else {
            // checked above, should be one or the other
            this.reader = new TabSeparatedFileReader(filename);
        }
        this.tweets = reader.getAllTweets();
        CSVFileReader csv = new CSVFileReader(state_file_name);
        this.states = csv.getStateLocations();
//        this.log_file_name = log_file_name;
    }

    /**
     * Identify flu tweets
     */
    public void filter() throws Exception {
        Map<String, Integer> count = new TreeMap<String, Integer>();
//        Logger l = Logger.getInstance();
//        l.setOutputDestination(log_file_name);

        /*
            \b assert position at a word boundary: (^\w|\w$|\W\w|\w\W)
            # matches the character # with index 3510 (2316 or 438) literally (case insensitive)
            {0,1} matches the previous token between zero and one times, as many times as possible, giving back as needed (greedy)
            flu matches the characters flu literally (case insensitive)
            \b assert position at a word boundary: (^\w|\w$|\W\w|\w\W)
            Global pattern flags
            g modifier: global. All matches (don't return after first match)
            i modifier: insensitive. Case insensitive match (ignores case of [a-zA-Z])
             */
        Pattern pattern = Pattern.compile("\\b#{0,1}flu\\b");
        Matcher matcher;

        for (Tweet tweet : tweets) {
            matcher = pattern.matcher(tweet.getContent().toLowerCase());
            boolean isflu = matcher.find();
            if (isflu) {
                // find the state
                State state = tweet.findState(this.states);
                String state_name = state.getName();
                // count: increment the state by 1
                count.put(state_name, count.getOrDefault(state_name, 0) + 1);
                // log the flu tweet
                String logContent = state.getName() + "\t" + tweet.getContent();
//                l.log(logContent);
                Logger.getInstance().log(logContent);
            }
        }
        Logger.getInstance().close();
        printSummary(count);
    }

    /**
     * Print out the program output - count
     * @param count the number of flu tweets in each state
     */
    private void printSummary(Map<String, Integer> count) {

        for (String state : count.keySet()) {
            System.out.println(state + ": " + count.get(state));
        }

    }

}

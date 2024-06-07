package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.Tweet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TabSeparatedFileReader implements Reader {

    protected String filename;

    public TabSeparatedFileReader(String name) {
        this.filename = name;
    }


    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = new ArrayList<Tweet>();

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(this.filename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] components = line.split("\\t");
                String locations = components[0];
                locations = locations.replace("[", "");
                locations = locations.replace("]", "");
                String[] location = locations.split(",");
                double lat = Double.parseDouble(location[0].trim());
                double lon = Double.parseDouble(location[1].trim());
                String content = components[3];
                tweets.add(new Tweet(lat, lon, content));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tweets;
    }

    @Override
    public String getFileName() {
        return this.filename;
    }
}

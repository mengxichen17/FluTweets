package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.Tweet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileReader implements Reader {
    protected String filename;

    public JSONFileReader(String name) {
        this.filename = name;
    }

    @Override
    public List<Tweet> getAllTweets() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        try {
            // parsing file
            Object obj = new JSONParser().parse(new FileReader(filename));

            // typecasting obj to JSONArray
            JSONArray ja = (JSONArray) obj;

            for (Object o : ja) {
                JSONObject t = (JSONObject) o;
                String content = (String) t.get("text");
                JSONArray location = (JSONArray) t.get("location");
                double lat = (double) location.get(0);
                double lon = (double) location.get(1);
                tweets.add(new Tweet(lat, lon, content));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tweets;
    }

    @Override
    public String getFileName() {
        return this.filename;
    }
}

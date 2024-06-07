package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.Tweet;

import java.util.List;

public interface Reader {
    public List<Tweet> getAllTweets();
    public String getFileName();
}

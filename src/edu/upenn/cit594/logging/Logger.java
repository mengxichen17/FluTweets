package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger implements AutoCloseable{

    protected String filename;
    private PrintWriter out ;
    private FileWriter file;

    private Logger() {}

    private static Logger instance = new Logger();

    public static Logger getInstance() {return instance;}

    /**
     * Log a flu tweet
     * @param content The content to append
     */
    public void log(String content) throws IOException {
        file = new FileWriter(this.filename, true);
        out = new PrintWriter(file, true);
        out.println(content);
        out.close();
    }

    public void setOutputDestination(String filename) {
        this.filename = filename;
    }

    @Override
    public void close() throws Exception {
        file.close();
    }
}

package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.State;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFileReader {
    protected String filename;

    public CSVFileReader(String name) {
        this.filename = name;
    }

    public List<State> getStateLocations() {
        List<State> locations = new ArrayList<State>();

        try {
            // Read CSV file line by line
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] state;
            while ((line = br.readLine()) != null) {
                state = line.split(",");
                String name = state[0];
                double lat = Double.parseDouble(state[1]);
                double lon = Double.parseDouble(state[2]);
                locations.add(new State(name, lat, lon));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return locations;
    }
}

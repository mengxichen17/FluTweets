package edu.upenn.cit594;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		/* Runtime Arguments
		There should be 3 arguments in the order:
			the name of the tweets input file
			the name of the states input file
			the name of the log file (for logging debug information; see “Logging” below)
		 */
		// Check 1: check the number of arguments
		if (args.length != 3) {
			System.out.println("Error: wrong number of arguments provided");
			return;
		}
		// Check 2: proper tweet file extensions
		if (!args[0].toLowerCase().endsWith(".json") && !args[0].toLowerCase().endsWith(".txt")) {
			System.out.println("Error: incorrect file type");
			return;
		}

		String filename = args[0];
		String state_file_name = args[1];
		String log_file_name = args[2];

		// Check 3: whether tweet file exists and can be read
		File file = new File(filename);
		if (!file.exists() || !file.canRead()) {
			System.out.println("Error: Tweets input file does not exist or cannot be opened for reading");
			return;
		}

		// Check 4: whether state file exists and can be read
		File state_file = new File(state_file_name);
		if (!state_file.exists() || !state_file.canRead()) {
			System.out.println("Error: State input file does not exist or cannot be opened for reading");
			return;
		}

		// Check 5: whether log file can be created or be opened and written
		File log_file = new File(log_file_name);
		try {
			log_file.createNewFile();// create a log_file if and only if this name does not exist.
		} catch (Exception e){
			System.out.println("Error: Log file cannot be created");
			return;
		}

		if (!log_file.canRead() || !log_file.canWrite()) {
			System.out.println("Error: Log file cannot be opened or read");
			return;
		}

		/*
		Create objects
		 */
		Logger.getInstance().setOutputDestination(log_file_name);

		Processor processor = new Processor(filename, state_file_name);

		CommandLineUserInterface ui = new CommandLineUserInterface(processor);

		ui.start();

	}

}

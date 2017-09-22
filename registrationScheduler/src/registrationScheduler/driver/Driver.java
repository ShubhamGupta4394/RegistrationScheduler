package registrationScheduler.driver;

import registrationScheduler.scheduling.Registrar;
import registrationScheduler.scheduling.RegistrarInterface;
import registrationScheduler.store.FileDisplayInterface;
import registrationScheduler.store.Results;
import registrationScheduler.util.InputParser;
import registrationScheduler.util.InputParserInterface;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Driver Class containing main method
 * 
 * @author shubham
 * 
 */
public class Driver {

	/**
	 * main method to start the program
	 * 
	 * @param args
	 *            = 4 arguments are passed from command line
	 */
	public static void main(String args[]) {
		if (args.length < 4 || args.length > 4) {
			System.err.println("4 Arguments required");
			System.exit(-1);
		}
		String outputFile = args[2];
		int debug_value = 0;
		if (isInteger(args[3])) {
			debug_value = Integer.parseInt(args[3]);
			if (debug_value < 0 || debug_value > 4) {
				System.err.println("Debug value should be between 0 to 4");
			}
		}
		/**
		 * Initialize all the 8 courses
		 */
		InputParser.dumpCourses();
		/**
		 * Logger value is set
		 */
		Logger.setDebugValue(debug_value);
		Logger.writeMessage("Debug value initialized", DebugLevel.CONSTRUCTOR);

		InputParserInterface input = new InputParser();
		/**
		 * Reading File data
		 */
		input.parseStudents(args[0], args[1]);

		RegistrarInterface reg = new Registrar();
		/**
		 * Analyze and Allocate courses
		 */
		reg.analyze();
		reg.doAllotment();
		/**
		 * If debug_value = 0 get the output
		 */
		if (0 == debug_value) {
			FileDisplayInterface result_file = new Results();
			result_file.output();
			result_file.writeScheduletoFile(outputFile);
		}

	}// end main(...)

	/**
	 * 
	 * isInteger is used to check argument passed is integer or not
	 * 
	 * @param string
	 *            = String argument passed to check if it is integer or not
	 * @return true if it is integer otherwise false
	 */
	private static boolean isInteger(String string) {

		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			System.err.println("Debug value should be integer");
			e.printStackTrace();
			System.exit(-1);
			return false;
		} finally {

		}
		return true;
	}
} // end public class Driver


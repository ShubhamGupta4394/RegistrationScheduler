package registrationScheduler.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import registrationScheduler.scheduling.Student;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Result class used to write Output to File by implementing
 * FileDisplayInterface
 * 
 * @author shubham
 * 
 */

public class Results implements FileDisplayInterface {
	// appropriate data structure as private data member
	private List<String> outputList = new ArrayList<String>();
	private BufferedWriter bufferedWriter;

	public Results() {

	}

	public void output() {
		Student.readStudents().sort(new Student.IStudentComparatorWithId());

		for (Student student : Student.readStudents()) {
			String output = student.name + "      " + student.allocated
					+ "      " + student.prefscore;
			outputList.add(output);

		}
		checkEfficiency();
	}

	/**
	 * checkEfficiency to calculate average score
	 */
	public void checkEfficiency() {
		int sum = 0;
		float average = 0;

		for (Student student : Student.readStudents()) {
			sum += student.prefscore;
		}

		average = sum / Student.readStudents().size();
		String average_score = "Average Preference score is: " + average;
		outputList.add(average_score);
		Logger.writeMessage("Average Preference score" + average_score,
				DebugLevel.OUTPUT);

	}

	public void writeScheduletoFile(String output) {
		// TODO Auto-generated method stub
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(output));
			for (String out : outputList) {
				bufferedWriter.write(out);
				bufferedWriter.write("\n");
			}
		} catch (IOException e) {
			System.err.println("Error in writing File");
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (bufferedWriter != null)
				try {
					bufferedWriter.close();
				} catch (Exception e) {
				}
		}
	}

	@Override
	public String toString() {
		return "Results []";
	}

}

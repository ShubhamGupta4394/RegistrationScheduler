package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import registrationScheduler.scheduling.Courses;
import registrationScheduler.scheduling.Records;
import registrationScheduler.scheduling.Student;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Input Parser class is used to parse student and 
 * initialize Course object with 8 course
 * 
 * @author shubham
 * 
 */
public class InputParser implements InputParserInterface {

	/**
	 * parseStudents method is used to parse String into 
	 * file object and retrieve the file contents
	 * 
	 * @param preferences
	 *            -> first file path passed as String
	 * @param regTime
	 *            -> second file path passed as String
	 */

	public InputParser() {

	}

	public void parseStudents(String preferences, String regTime) {

		try {
			BufferedReader bufferedReaderPref = FileUtils.readFile(preferences);
			BufferedReader bufferedReaderReg = FileUtils.readFile(regTime);

			try {
				String read;
				while ((read = bufferedReaderPref.readLine()) != null) {
					String regDetails = bufferedReaderReg.readLine();
					Student student = new Student(read, regDetails);
					Records.getInstance().addStudent(student);

				}
				Logger.writeMessage("Input are parsed", DebugLevel.INPUT);

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			} finally {
				if (bufferedReaderPref != null)
					try {
						bufferedReaderPref.close();
					} catch (Exception e) {
					}
				if (bufferedReaderReg != null)
					try {
						bufferedReaderReg.close();
					} catch (Exception e) {
					}
			}
		} catch (FileEmptyException fe) {
			fe.printStackTrace();
			System.exit(-1);
		} catch (FileNotFoundException e1) {
			System.err.println("File Not Found");
			e1.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * dumpCourses method is used to initialize
	 * Courses object with 8 courses
	 * 
	 */
	public static void dumpCourses() {
		String[] courses = { "A", "B", "C", "D", "E", "F", "G", "H" };
		for (int i = 0; i < courses.length; i++) {
			Courses course = new Courses(courses[i]);
			Records.getInstance().addCourses(course);
		}

	}

	public String toString() {
		return super.toString();
	}

}

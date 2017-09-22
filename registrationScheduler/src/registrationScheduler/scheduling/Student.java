package registrationScheduler.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Student class is used to store regTime, name, 
 * prefscore and also implements StudentInterface
 * 
 * @author shubham
 * 
 */
public class Student implements StudentInterface {

	public int regTime;
	public String name;
	public int prefscore;
	public List<Courses> preference;
	public List<Courses> allocated;
	public String pref[];

	public Student(String preferences, String regDetails) {
		String regDetail[] = regDetails.split(" ");
		name = regDetail[0];
		regTime = Integer.parseInt(regDetail[1]);
		preference = new ArrayList<Courses>();
		allocated = new ArrayList<Courses>();

		String[] prefs = preferences.split(" ");

		for (int i = 0; i < prefs.length - 1; i++) {

			Courses course = Courses.readCourses().get(prefs[i + 1]);

			/**
			 * Increase the course rating or preferences for students
			 */

			course.incrementPref(i);

			preference.add(course);
		}
	}

	public int getRegTime() {
		return regTime;
	}

	public void setRegTime(int regTime) {
		this.regTime = regTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrefscore() {
		return prefscore;
	}

	public void setPrefscore(int prefscore) {
		this.prefscore = prefscore;
	}

	public List<Courses> getPreference() {
		return preference;
	}

	public void setPreference(List<Courses> preference) {
		this.preference = preference;
	}

	public List<Courses> getAllocated() {
		return allocated;
	}

	public void setAllocated(List<Courses> allocated) {
		this.allocated = allocated;
	}

	public String[] getPref() {
		return pref;
	}

	public void setPref(String[] pref) {
		this.pref = pref;
	}

	public static List<Student> readStudents() {
		return Records.getInstance().getAllStudent();

	}

	public Courses getCourseAtPref(int choice) {
		// TODO Auto-generated method stub
		return getPreference().get(choice);
	}

	public void updatePreferenceScore(int score) {
		this.prefscore = score;
	}

	@Override
	public String toString() {
		return "Student []";
	}

	/**
	 * Class used for sorting based on RegTime
	 * 
	 * @author shubham
	 * 
	 */
	public static class IStudentComparatorWithRegtime implements
			Comparator<Student> {

		public int compare(Student arg0, Student arg1) {
			if (arg0.regTime < arg1.regTime)
				return -1;
			else if (arg0.regTime > arg0.regTime)
				return 1;
			else
				return 0;
		}

	}

	/**
	 * Class used for sorting based on Student name
	 * 
	 * @author shubham
	 * 
	 */
	public static class IStudentComparatorWithId implements Comparator<Student> {

		public int compare(Student arg0, Student arg1) {

			int Id0 = Integer.parseInt(arg0.name.split("_")[1]);
			int Id1 = Integer.parseInt(arg1.name.split("_")[1]);
			if (Id0 < Id1)
				return -1;
			else if (Id0 > Id1)
				return 1;
			else
				return 0;
		}

	}
}

package registrationScheduler.scheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Course class containing seats, coursename and number of students
 * 
 * @author shubham
 * 
 */
public class Courses implements CourseInterface {

	public String coursename;
	int seats;
	public List<Student> student;

	public int[] prefernces = { 0, 0, 0, 0 };

	String[] courses = { "A", "B", "C", "D", "E", "F", "G", "H" };

	/**
	 * Course Constructor
	 * 
	 * @param -> Coursename to be allocated
	 */
	public Courses(String coursename) {
		this.coursename = coursename;
		this.seats = 20;
		student = new ArrayList<Student>();
	}

	public static LinkedHashMap<String, Courses> readCourses() {
		return Records.getInstance().getAllCourses();
	}

	public Courses getCourses(String coursename) {
		return readCourses().get(coursename);
	}

	public void incrementPref(int choice) {
		prefernces[choice]++;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public int[] getPrefernces() {
		return prefernces;
	}

	public void setPrefernces(int[] prefernces) {
		this.prefernces = prefernces;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}

	public void registerStudent(Student st) {
		if (this.student == null)
			this.student = new ArrayList<Student>();

		this.student.add(st);
	}

	/**
	 * Check Availability of seats
	 */
	public boolean isSeatAvailable() {
		return this.student == null || this.seats > this.student.size();
	}

	/**
	 * Check for available course
	 * 
	 * @return -> Course object
	 */
	public static Courses getNextAvailableCourse() {
		int numSeatAvail = 0;
		int rating = 50;
		Courses courseToRet = null;

		for (Courses course : Courses.readCourses().values()) {
			int[] rates = course.prefernces;
			Arrays.sort(rates);
			int maxRate = rates[3];

			if (20 - course.student.size() > numSeatAvail && maxRate < rating) {
				numSeatAvail = 20 - course.student.size();
				courseToRet = course;
				rating = maxRate;
			}
		}

		return courseToRet;
	}

	@Override
	public String toString() {

		return coursename;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Courses) {
			return this.coursename
					.equalsIgnoreCase(((Courses) arg0).coursename);
		} else {
			return super.equals(arg0);
		}
	}
}

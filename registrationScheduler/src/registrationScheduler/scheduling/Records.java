package registrationScheduler.scheduling;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Records is a singleton class used to store student and courses
 * 
 * @author shubham
 * 
 */
public class Records {

	private static Records _sharedInstance;

	private List<Student> studentList;
	private LinkedHashMap<String, Courses> coursesList;

	public Records() {

	}

	public synchronized static Records getInstance() {
		if (_sharedInstance == null)
			_sharedInstance = new Records();

		return _sharedInstance;
	}

	public void addStudent(Student student) {
		if (studentList == null) {
			studentList = new ArrayList<Student>();
		}
		studentList.add(student);
	}

	public void addCourses(Courses courses) {
		if (coursesList == null) {
			coursesList = new LinkedHashMap<String, Courses>();
		}
		coursesList.put(courses.coursename, courses);
	}

	public List<Student> getAllStudent() {
		return studentList;
	}

	@Override
	public String toString() {
		return "Records []";
	}

	public LinkedHashMap<String, Courses> getAllCourses() {
		return coursesList;
	}

}

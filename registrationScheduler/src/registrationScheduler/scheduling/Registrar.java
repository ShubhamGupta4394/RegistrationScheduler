package registrationScheduler.scheduling;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Registrar class implements RegistrarInterface 
 * which is allocating course to students
 * 
 * @author shubham
 * 
 */
public class Registrar implements RegistrarInterface {

	public Registrar() {

	}

	public void analyze() {

		Student.readStudents()
				.sort(new Student.IStudentComparatorWithRegtime());
	}

	public void doAllotment() {
		int choice = 0;

		/**
		 * Allocate 4 courses to at most students
		 */
		while (choice < 4) {

			/**
			 * Iterate through all the students 
			 * and assign the best available course
			 */
			for (Student student : Student.readStudents()) {

				/**
				 * Check students first preference and 
				 * assign if sear is available
				 */
				Courses course = student.getCourseAtPref(choice);

				/**
				 * If there is seat available in course.
				 * Register student for the class
				 */
				if (course.isSeatAvailable()) {
					course.registerStudent(student);
					/**
					 * Update score
					 */
					student.updatePreferenceScore(student.getPrefscore()
							+ choice + 1);
					student.allocated.add(course);
				}

				else {
					course = Courses.getNextAvailableCourse();

					if (course == null || student.allocated.contains(course)) {
						student.prefscore = student.prefscore += 6;
						continue;
					}

					student.prefscore = student.prefscore += 5;
					student.allocated.add(course);
					course.registerStudent(student);
				}
			}

			choice += 1;
		}
		Logger.writeMessage("Courses Allocated", DebugLevel.ALLOCATION);
	}

	@Override
	public String toString() {
		return "Registrar []";
	}

}

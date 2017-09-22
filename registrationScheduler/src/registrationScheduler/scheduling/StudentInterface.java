package registrationScheduler.scheduling;

/**
 * StudentInterface implemented by Student Class
 * 
 * @author shubham
 * 
 */
public interface StudentInterface {
	public Courses getCourseAtPref(int choice);

	public void updatePreferenceScore(int score);
}

package registrationScheduler.util;

/**
 * Class is used to handle Empty File Exception
 * 
 * @author shubham
 * 
 */
public class FileEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getLocalizedMessage() {
		return "File is empty";
	}

	@Override
	public String getMessage() {
		return getLocalizedMessage();
	}

}

package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Class FileUtils is used to read File
 * 
 * @author shubham
 * 
 */
public class FileUtils {
	/**
	 * 
	 * @param path
	 *            -> File path passed as String
	 * @return -> BufferReader object is returned
	 * @throws FileEmptyException
	 *             -> File Exception when file is empty
	 * @throws FileNotFoundException
	 *             -> Exception when file path is null
	 */

	public FileUtils() {

	}

	public static BufferedReader readFile(String path)
			throws FileEmptyException, FileNotFoundException {
		File file = new File(path);
		BufferedReader buffer = readFile(file);
		if (file.length() == 0) {
			throw new FileEmptyException();
		}

		return buffer;
	}

	/**
	 * 
	 * @param file
	 *            -> file is passed as argument
	 * @return -> BufferReader object is returned
	 * @throws FileNotFoundException
	 *             -> Exception when file path is null
	 */
	public static BufferedReader readFile(File file)
			throws FileNotFoundException {
		FileInputStream fins = new FileInputStream(file);
		return new BufferedReader(new InputStreamReader(fins));
	}

	@Override
	public String toString() {
		return "FileUtils []";
	}
}

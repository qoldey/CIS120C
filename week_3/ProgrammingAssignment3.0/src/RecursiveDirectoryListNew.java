import java.io.File;

/**
 * This program lists the files in a directory specified by
 * the user. The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 * By default, uses current pwd until Scanner lines are uncommented
 */
public class RecursiveDirectoryListNew {

	public static void main(String[] args) {
		// Scanner scanner = new Scanner(System.in); // For user input.
		String directoryName; // Directory name entered by the user.
		File directory; // File object referring to the directory.

		// System.out.printf("\nEnter a directory name: \n");
		// directoryName = scanner.nextLine().trim();

		directoryName = System.getProperty("user.dir"); // get current pwd

		directory = new File(directoryName);
		System.out.println(directoryName);
		listDirectoryRecursively(directory, " ");

	} // end main()

	public static void listDirectoryRecursively(File file, String space) {

		String[] files;
		space += " ";
		files = file.list();

		System.out.println(space + file.getName());

		for (int i = 0; i < files.length; i++) {

			File check = new File(file, files[i]);

			if (check.isDirectory()) {
				listDirectoryRecursively(check, space);
			} else {
				System.out.println(space + files[i]);
			}
		}
	}

} // end class DirectoryList

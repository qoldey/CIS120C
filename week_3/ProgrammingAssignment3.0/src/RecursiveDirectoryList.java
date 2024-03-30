import java.io.File;

/**
 * This program lists the files in a directory specified by
 * the user. The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 * By default, uses current pwd until Scanner lines are uncommented
 */
public class RecursiveDirectoryList {

	public static void main(String[] args) {
		// Scanner scanner = new Scanner(System.in); // For user input.
		String directoryName; // Directory name entered by the user.
		File directory; // File object referring to the directory.

		// System.out.printf("\nEnter a directory name: \n");
		// directoryName = scanner.nextLine().trim();

		directoryName = System.getProperty("user.dir"); // get current pwd

		directory = new File(directoryName);
		printTree(directory);

	} // end main()

	public static void printTree(File directory) {

		String[] files; // Array of file names in the directory.

		if (directory.isDirectory() == false) {
			if (directory.exists() == false)
				System.out.println("There is no such directory!");
			else
				System.out.println("That file is not a directory.");
		} else {
			files = directory.list();
			File file;
			// System.out.println("Files in directory \"" + directory + "\":");
			// TODO: better format output tree
			for (int i = 0; i < files.length; i++) {
				file = new File(directory, files[i]);
				if (file.isDirectory()) {
					System.out.printf("   +" + files[i] + "\n");
					printTree(file);
				} else
					System.out.printf("   -" + files[i] + "");
			}
		}
	}// end method printTree
} // end class DirectoryList

import java.io.*;
import java.util.ArrayList;

public class CopyFile {

    public static void main(String[] args) {
        String fileName = "data.txt";
        String copyTo = "output.txt";
        copyFile(fileName, copyTo, true);

    } // end main()

    // Copies file "sourceName" to file "copyName" with and option to force.
    // Returns size of files in bytes or 0 if there is an error.
    public static int copyFile(String sourceName, String copyName, boolean force) {
        ArrayList<String> lines = new ArrayList<String>(); // An ArrayList for holding the data.

        int byteCount; // Number of bytes copied from the source file.

        /* Read the data from the input file. If an error occurs, end the program. */

        try (BufferedReader source = new BufferedReader(new FileReader(new File(sourceName)))) {
            while (source.ready()) { // Anything to read in the Stream? -- Data to read from target file?
                lines.add(source.readLine()); // Write content from file to array
            }
        } catch (IOException e) {
            // Can be caused if file does not exist or can't be read.
            System.out.println("Can't find file \"" + sourceName + "\".");
            System.out.println("Error: " + e);
            return 0; // Return from main(), since an error has occurred.
        }

        /*
         * If the output file already exists and the -f option was not
         * specified, print an error message and end the program.
         */

        File file = new File(copyName);
        if (file.exists() && force == false) {
            System.out.println(
                    "Output file exists.  Use the force option to replace it.");
            return 0;
        }

        /*
         * Copy one byte at a time from the input stream to the output
         * stream, ending after reaching the end of lines (which should
         * be the size of the file in bytes ). If any error occurs,
         * print an error message. Also print a message if
         * the file has been copied successfully.
         */

        byteCount = 0;

        // Write the data to the output file.

        try (PrintWriter copy = new PrintWriter(new FileWriter(new File(copyName)))) {
            // Output the numbers.
            for (int i = 0; i < lines.size(); i++) {
                copy.println(lines.get(i)); // Write content from array to new file.
                byteCount++;
            }
            if (copy.checkError())
                System.out.println("Error occurred while writing the file.");
            else
                System.out.printf("Done!\nSuccessfully copied " + byteCount + " bytes.\n"); // This isn't correct
        } catch (IOException e) {
            // Can only caused by the PrintWriter constructor
            // or by one of the constructors *it* calls
            System.out.println("Can't open file: " + copyName);
            System.out.println("Error: " + e);
        }

        return byteCount;
    }
} // end class CopyFile

import java.io.*;
import java.util.ArrayList;

public class CopyFile {

    // Alternate version of CopyFile that can optionally print the byte contents of
    // a file and output a binary.txt file
    public static void main(String[] args) {
        String fileName = "blue.bmp";
        String copyTo = "output.bmp";
        copyFile(fileName, copyTo, true, true);

    } // end main()

    // Copies file "sourceName" to file "copyName" with and option to force.
    // Returns size of files in bytes or 0 if there is an error.
    public static int copyFile(String sourceName, String copyName, boolean force, boolean print) {
        ArrayList<Integer> lines = new ArrayList<Integer>(); // An ArrayList for holding the data.
        int byteCount = 0; // Number of bytes copied from the source file.

        // Check if output exists and if force option passed. Prints error msg if not
        File file = new File(copyName);
        if (file.exists() && force == false) {
            System.out.println(
                    "Output file exists.  Use the force option to replace it.");
            return 0;
        }

        /*
         * Copy one byte at a time from the input stream to the output
         * stream, ending after reaching a -1 (which should
         * indicate the end of the file ). If any error occurs,
         * print an error message. Also print a message if
         * the file has been copied successfully.
         */

        try (
                /* Read the data from the input file. If an error occurs, end the program. */
                FileInputStream in = new FileInputStream(sourceName);
                FileOutputStream out = new FileOutputStream(copyName);) {
            int b;
            while ((b = in.read()) != -1) {// Data remaining in file?
                out.write(b);// Write content from file to array
                byteCount++; // Add byte
                lines.add(b); // Record byte
            }
            System.out.printf("Done!\nSuccessfully copied " + byteCount + " bytes.\n");
        } catch (IOException e) {
            // Can be caused if file does not exist or can't be read.
            System.out.println("Can't find file \"" + sourceName + "\".");
            System.out.println("Error: " + e);
            return 0; // Return from main(), since an error has occurred.
        }

        if (print) {
            try (PrintWriter copy = new PrintWriter(new FileWriter(new File("binary.txt")))) {
                for (int i = 0; i < lines.size(); i++) { // TODO: format binary output to match hex editor.
                    System.out.print(lines.get(i)); // Output content to CLI
                    copy.println(lines.get(i)); // Write content from array to new file.
                }
                if (copy.checkError())
                    System.out.println("Error occurred while writing to binary.txt.");
                else
                    System.out.println("Successfully wrote binary.txt!");
            } catch (IOException e) {
                // Can only caused by the PrintWriter constructor
                // or by one of the constructors *it* calls
                System.out.println("Can't open file: " + copyName);
                System.out.println("Error: " + e);
            }
        }
        // TODO: Allow user to change the value of some bytes
        return byteCount;
    }// end Method copyFile
} // end class CopyFile

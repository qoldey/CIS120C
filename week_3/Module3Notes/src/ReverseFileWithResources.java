import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Written by David Eck.
 * Modified by Steven L. Richardson to use a BufferedReader instead of
 * a Scanner.
 * 
 * Reads numbers from a file named data.dat and writes them to a file
 * named result.dat in reverse order. The input file should contain
 * exactly one real number per line.
 * This version of the ReverseFile program uses try-with-resource
 * statements to safely open and close the BufferedReader that is used to
 * read the data and the PrintWriter that is used to
 * write the data.
 */
public class ReverseFileWithResources {

    public static void main(String[] args) {

        ArrayList<String> lines; // An ArrayList for holding the data.

        lines = new ArrayList<String>();

        // Read the data from the input file.

        try (BufferedReader data = new BufferedReader(new FileReader(new File("data.txt")))) {
            while (data.ready()) { // Anything to read in the Stream?
                lines.add(data.readLine());
            }
        } catch (IOException e) {
            // Can be caused if file does not exist or can't be read.
            System.out.println("Can't open input file data.txt!");
            System.out.println("Error: " + e);
            return; // Return from main(), since an error has occurred.
        }

        // Write the data to the output file.

        try (PrintWriter result = new PrintWriter(new FileWriter(new File("result.txt")))) {
            // Output the numbers in reverse order.
            for (int i = lines.size() - 1; i >= 0; i--)
                result.println(lines.get(i));
            result.flush(); // Make sure data is actually sent to the file.
            if (result.checkError())
                System.out.println("Error occurred while writing the file.");
            else
                System.out.println("Done!");
        } catch (IOException e) {
            // Can only caused by the PrintWriter constructor
            // (or by one of the constructors *it* calls
            System.out.println("Can't open file result.dat!");
            System.out.println("Error: " + e);
        }

    } // end of main()

} // end class ReverseFileWithTextReader
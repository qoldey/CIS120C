import java.util.Scanner;

public class App {
    /*
     * Allows user to input 3 values and calculate the larger of the two roots of
     * the quadratic equation
     * a*x*x + b*x + c = 0, provided it has any roots, and then printing the
     * solution. until the user enters "no" in response to a prompt
     * If a == 0 or if the discriminant, b*b - 4*a*c, is negative, then an exception
     * of type IllegalArgumentException is printed, and the user is asked to enter
     * new numbers. If the user enters less than 3 numbers, a default set is
     * provided.
     */

    public static void main(String[] args) throws Exception {
        Double[] inputArray = new Double[3];
        double solution;

        // allow the user to specify values for a,b, and c.
        // if args exist, use args vs hardcoded.
        if (args.length == 3) { // if provided via command line
            inputArray = stringArrayToDoubleArray(args);
        } else { // if not provided via cli, get interactive input.
            inputArray = getInputArray();
        }

        String exitMessage = "Received Stop signal, exiting.";

        String input;
        // run code until user inputs exitCode
        while (true) {
            // call the subroutine in a try-catch block & compute solution to equation
            try {
                solution = root(inputArray[0], inputArray[1], inputArray[2]);
                // If no error occurs, it should print the root.
                System.out.println("Solution: " + solution);
            } catch (IllegalArgumentException e) {
                // if an error occurs, your program should catch that error and print
                // an error message
                if (inputArray[0] == 0) {
                    System.out.println("a cannot be zero.");
                } else {
                    System.out.println("Discriminant < zero.");
                }
            }

            // ask whether the user wants to enter another equation.
            System.out.println("Enter Another Equation?");
            input = inputString();
            // continue until the user answers no.
            if (input.equals("no")) {
                System.out.println(exitMessage);
                System.exit(0);
            } else if (input.equals("yes")) {
                inputArray = getInputArray();
            } else {
                inputArray = getInputArray();
            }
        }
    }

    public static Double[] getInputArray() {
        Double[] inputArray = new Double[3];

        System.out.print("Enter A ");
        inputArray[0] = Double.parseDouble(inputString());
        System.out.print("Enter B ");
        inputArray[1] = Double.parseDouble(inputString());
        System.out.print("Enter C ");
        inputArray[2] = Double.parseDouble(inputString());

        // default values
        // check array, if shorter than needed, provide default values
        // then terminate
        if (inputArray.length < 3) {
            inputArray[0] = 2.0;
            inputArray[1] = 3.0;
            inputArray[2] = 1.0;
            // throw new IllegalArgumentException("must specify a, b, & c.");
        }
        return inputArray;
    }

    static public double root(double a, double b, double c)
            throws IllegalArgumentException {
        if (a == 0) {
            throw new IllegalArgumentException("a can't be zero.");
        } else {
            double disc = b * b - 4 * a * c;
            if (disc < 0)
                throw new IllegalArgumentException("Discriminant < zero.");
            return (-b + Math.sqrt(disc)) / (2 * a);
        }
    }

    public static String inputString() { // user input from keyboard
        String output = "";
        Scanner sysin = new Scanner(System.in);
        System.out.print("$ ");// prompt
        if (sysin.hasNextLine()) {
            output = sysin.nextLine();
        } else {
            inputString();
        }
        return output;
    }

    public static Double[] stringArrayToDoubleArray(String[] inputStrings) { // used to convert args to Double[]
        Double[] output = new Double[3];
        for (int i = 0; i < inputStrings.length; i++) {
            output[i] = Double.parseDouble(inputStrings[i]);
        }
        return output;
    }
}

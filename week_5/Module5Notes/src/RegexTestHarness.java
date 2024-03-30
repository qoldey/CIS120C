import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    public static void main(String[] args) {

        Scanner kbd = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter your regex: ");
            Pattern pattern = Pattern.compile(kbd.nextLine());

            System.out.print("\nEnter input string to search: ");
            Matcher matcher = pattern.matcher(kbd.nextLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.printf("I found the text" +
                        " \"%s\" starting at " +
                        "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                found = true;
            }
            if (!found) {
                System.out.println("No match found.");
            }
        }
    }
}
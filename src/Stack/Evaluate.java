package Stack;

import java.util.Scanner;

public class Evaluate {
    // For this first simple case we will require that the expression:
    // 1) be fully parenthesized and valid syntax
    // 2) only have a single digit for an operand
    // For example:  (3*(7-5)) =>  (3*2) => (6) => 6

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(""); // Scanner will return one char at a time with no delimiter

        System.out.print("Enter expression: ");
        String input = scanner.next();  // Waits for input to be available, returns only the first char as a string
        while(true) {
            char c = input.charAt(0);

            // Process the character, just printing for now.
            System.out.print( c + " " );

            input = scanner.next();
        }
    }
}

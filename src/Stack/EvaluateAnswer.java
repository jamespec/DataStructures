package Stack;

import java.util.Scanner;

public class EvaluateAnswer {
    // For this first simple case we will require that the expression:
    // 1) be fully parenthesized and valid syntax
    // 2) only have a single digit for an operand
    // For example:  (3*(7-5)) =>  (3*2) => (6) => 6
    //
    // The first go of the algorithm is pretty easy.
    // We can ignore spaces and '('.
    //
    // The characters input are ASCII codes.  Java will convert these to integers.
    // For example: '0' is code 48, '1' is 49. '2' is 50, ...
    // By subtracting the character '0' from a character digit you can convert to an integer value:
    // '4' (code 52) - '0' (code 48) = 4

    public static void main( String[] args )
    {
        Stack s = new Stack(100 );
        int depth=0;

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(""); // Scanner will return one char at a time with no delimiter

        System.out.print("Enter expression: ");
        String input = scanner.next();  // Waits for input to be available, returns only the first char as a string
        while(true) {
            char c = input.charAt(0);

            if( c == '(' ) {
                depth++;
            }
            else if( Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || c == ')' ) {

                if( c == ')' ) {
                    int op2 = s.pop();
                    int operand = s.pop() + '0';  // Add back '0' to get the operand character.
                    int op1 = s.pop();
                    int result;

                    switch( operand ) {
                        case '+':
                            result = op1 + op2;
                            break;
                        case '-':
                            result = op1 - op2;
                            break;
                        case '*':
                            result = op1 * op2;
                            break;
                        case '/':
                            result = op1 / op2;
                            break;
                        default:
                            throw new RuntimeException();
                    }

                    s.push( result );
                    depth--;

                    if( depth == 0 ) {
                        System.out.println( s.pop() );
                    }
                }
                else
                    s.push(c-'0'); // Push the "value" on to the Stack.  By subtracting '0' we convert the char '1' to the integer 1 and so on.
            }
            // else anything else we throw away and ignore for now.

            input = scanner.next();
        }
    }
}

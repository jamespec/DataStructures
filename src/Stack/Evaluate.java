package Stack;

import java.util.Scanner;

public class Evaluate {
    // For this first simple case we will require that the expression:
    // 1) be fully parenthesized and valid syntax
    // 2) only have a single digit for an operand
    // 3) all interim results are a single digit
    // For example:  (3*(7-5)) =>  (3*2) => (6) => 6
    //
    // The first go of the algorithm is pretty easy.
    // We can ignore spaces and '('.

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
                    int op2 = s.pop() - '0';
                    char operand = s.pop();
                    int op1 = s.pop() - '0';
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

                    s.push((char) (result + '0'));
                    depth--;

                    if( depth == 0 ) {
                        System.out.println( result );
                        System.exit(0);
                    }
                }
                else
                    s.push(c);
            }
            // else anything else we throw away and ignore for now.

            input = scanner.next();
        }
    }
}

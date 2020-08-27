package Parsing;

public class Tokenizer {
    // This class is a simple example of tokenizing a stream of characters.
    // This is a highly complex topic in itself so this is be no means a complete example.

    // input is an optional character, the next in the input stream.
    // it may need be called with no input to get the last token.

    // States:
    // 0 - start, waiting for a character
    // 1 - building a number

    private String input;
    private int nextCharIndex;
    private StringBuilder partialToken;
    int state;

    public Tokenizer( String input ) {
        this.input = input;
        this.nextCharIndex = 0;
        this.partialToken = new StringBuilder();
        this.state = 0;
    }

    public String getToken() {
        // processed input one char at a time until a token is created.
        // return that token.

        while(true) {
            if (nextCharIndex == input.length()) {
                if (partialToken.length() == 0) {
                    return null;
                } else {
                    String token = partialToken.toString();
                    partialToken.setLength(0);
                    return token;
                }
            }

            char nextChar = input.charAt(nextCharIndex++);
            switch (state) {
                case 0:
                    switch (nextChar) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                        case '(':
                        case ')':
                            // stay on state 0 and return operator.
                            return Character.toString(nextChar);
                        default:
                            if( Character.isDigit(nextChar) ) {
                                // Start building a number, go to start 1, building a number.
                                partialToken.append(nextChar);
                                state = 1;
                            }
                            // else ignore the character for now.
                            // Probably should through an exception
                            break;
                    }
                    break;

                case 1: // Building a number
                    if (Character.isDigit(nextChar)) {
                        // append the next digit, continue building (stay on state 1).
                        partialToken.append(nextChar);
                    } else {
                        nextCharIndex--;  // unfetch this char, we're not actually processing it yet!
                        state = 0;
                        String token = partialToken.toString();
                        partialToken.setLength(0);
                        return token;
                    }
                    break;
            }
        }
    }

    static public void main( String[] args ) {
        Tokenizer t = new Tokenizer("(435 * (54+4,300) - 454)");

        String token = t.getToken();
        while( token != null ) {
            System.out.println( token );
            token = t.getToken();
        }
    }
}

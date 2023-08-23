package Exceptions;

public class ErrorTypeFileException extends Exception {
    public ErrorTypeFileException(String expectedType, String arg) {
        super("Argument: " + "<<" + arg + ">>" +
              " incorrect\nMust be: " + expectedType);
    }
}

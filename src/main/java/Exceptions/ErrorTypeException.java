package Exceptions;

public class ErrorTypeException extends Exception {
    public ErrorTypeException(String message, String arg) { super(message + "<<" + arg + ">>"); }
}

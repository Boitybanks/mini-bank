/**
 * Thrown when a withdrawal would break an account's rules — e.g. going
 * past a savings account's balance, or past a checking account's
 * overdraft limit. It's a checked exception on purpose: callers are
 * forced to decide how to handle a failed withdrawal rather than letting
 * it crash the program.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

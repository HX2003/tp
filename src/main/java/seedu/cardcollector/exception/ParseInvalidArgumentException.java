package seedu.cardcollector.exception;

/**
 * Signals an argument error during the parsing of a command.
 */
public class ParseInvalidArgumentException extends ParseException {
    private final String[] usage;

    /**
     * Constructs a new exception with a specified message.
     *
     * @param message A message explaining the cause of the exception.
     * @param usage A text to explain the intended usage of the command.
     */
    public ParseInvalidArgumentException(String message, String[] usage) {
        super(message);
        this.usage = usage;
    }

    public String[] getUsage() {
        return usage;
    }
}

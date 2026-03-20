package seedu.cardcollector.command;

public class CommandResult {
    private final boolean isExit;

    public CommandResult(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getIsExit() {
        return isExit;
    }
}

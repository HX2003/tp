package seedu.cardcollector.command;

public abstract class Command {
    protected boolean isReversible = false;

    public abstract CommandResult execute(CommandContext context);
    public CommandResult undo(CommandContext context) {
        throw new UnsupportedOperationException("This command does not support undo.");
    }
    public boolean isReversible() {
        return isReversible;
    }
}

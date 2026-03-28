package seedu.cardcollector.command;

public class UndoCommand extends Command {

    @Override
    public CommandResult execute(CommandContext context) {
        // 1. Get the stack of previous commands from the context
        var history = context.getCommandHistory();

        if (history.isEmpty()) {
            context.getUi().echo("Nothing to Undo.");
            return new CommandResult(false);
        }

        // 2. Pop the last command and call its undo method
        Command lastCommand = history.pop();
        return lastCommand.undo(context);
    }
}

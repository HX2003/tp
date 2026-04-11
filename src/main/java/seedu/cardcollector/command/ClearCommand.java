package seedu.cardcollector.command;

import seedu.cardcollector.card.CardsList;

public class ClearCommand extends Command {
    private CardsList previousState;

    public ClearCommand() {
        this.isReversible = true;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var targetList = getAffectedList(context);
        this.previousState = targetList.deepCopy();

        targetList.clear();

        context.getUi().printCleared(targetList);
        return new CommandResult(false);
    }

    @Override
    public CommandResult undo(CommandContext context) {
        var targetList = getAffectedList(context);
        targetList.replaceWith(previousState);

        context.getUi().printUndoSuccess(targetList);
        return new CommandResult(false);
    }
}

package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;

public class TagCommand extends Command {
    public enum Operation {
        ADD,
        REMOVE
    }

    private final Operation operation;
    private final int targetIndex;
    private final String tag;

    public TagCommand(Operation operation, int targetIndex, String tag) {
        this.operation = operation;
        this.targetIndex = targetIndex;
        this.tag = tag;
        this.isReversible = true;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var ui = context.getUi();
        var cards = context.getTargetList();
        if (targetIndex < 0 || targetIndex >= cards.getSize()) {
            ui.printInvalidIndex();
            return new CommandResult(false, false);
        }

        Card card = cards.getCard(targetIndex);
        boolean changed = switch (operation) {
        case ADD -> cards.addTag(targetIndex, tag);
        case REMOVE -> cards.removeTag(targetIndex, tag);
        };

        this.isReversible = changed;
        if (!changed) {
            ui.printTagNoChange(card, tag, operation.name().toLowerCase());
            return new CommandResult(false, false);
        }

        ui.printTagUpdated(cards, targetIndex, tag, operation.name().toLowerCase());
        return new CommandResult(false);
    }

    @Override
    public CommandResult undo(CommandContext context) {
        switch (operation) {
        case ADD -> context.getTargetList().removeTag(targetIndex, tag);
        case REMOVE -> context.getTargetList().addTag(targetIndex, tag);
        default -> throw new IllegalStateException("Unexpected operation: " + operation);
        }
        context.getUi().printUndoSuccess(context.getTargetList());
        return new CommandResult(false);
    }
}

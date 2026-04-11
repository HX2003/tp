package seedu.cardcollector.command;

import seedu.cardcollector.card.CardsList;

public abstract class Command {
    protected boolean isReversible = false;

    protected boolean isWishlistOperation = false;

    public void markAsWishlistOperation() {
        this.isWishlistOperation = true;
    }

    protected CardsList getAffectedList(CommandContext context) {
        return isWishlistOperation ? context.getWishlist() : context.getTargetList();
    }

    public abstract CommandResult execute(CommandContext context);
    public CommandResult undo(CommandContext context) {
        throw new UnsupportedOperationException("This command does not support undo.");
    }
    public boolean isReversible() {
        return isReversible;
    }
}

package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;

import java.util.ArrayList;

public class ListCommand extends Command {
    private final String tag;

    public ListCommand() {
        this(null);
    }

    public ListCommand(String tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        if (tag == null) {
            context.getUi().printList(context.getTargetList());
            return new CommandResult(false);
        }

        ArrayList<Card> results = context.getTargetList().findCards(
                null, null, null, null, null, null, null, null, tag);
        context.getUi().printTaggedList(results, tag);
        return new CommandResult(false);
    }
}

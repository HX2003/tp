package seedu.cardcollector.command;

import seedu.cardcollector.card.CardSortCriteria;

public class ReorderCommand extends Command {

    private final CardSortCriteria criteria;
    private final boolean isAscending;

    public ReorderCommand(CardSortCriteria criteria, boolean isAscending) {
        this.criteria = criteria;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var ui = context.getUi();
        var targetList = context.getTargetList();

        targetList.reorder(criteria, isAscending);
        ui.printReordered(targetList);

        return new CommandResult(false);
    }
}

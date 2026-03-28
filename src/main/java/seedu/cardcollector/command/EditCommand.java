package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;

public class EditCommand extends Command {

    private final int targetIndex;
    private final String newName;
    private final Integer newQuantity;
    private final Float newPrice;

    private String oldName;
    private Integer oldQuantity;
    private Float oldPrice;

    public EditCommand(int targetIndex, String newName, Integer newQuantity, Float newPrice) {
        this.targetIndex = targetIndex;
        this.newName = newName;
        this.newQuantity = newQuantity;
        this.newPrice = newPrice;
        this.isReversible = true;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var ui = context.getUi();
        var inventory = context.getTargetList();
        if (targetIndex < 0 || targetIndex >= inventory.getSize()) {
            ui.printInvalidIndex();
            return new CommandResult(false, false);
        }

        Card card = inventory.getCard(targetIndex);
        this.oldName = card.getName();
        this.oldQuantity = card.getQuantity();
        this.oldPrice = card.getPrice();

        boolean changed = inventory.editCard(targetIndex, newName, newQuantity, newPrice);
        this.isReversible = changed;

        if (changed) {
            ui.printEdited(inventory, targetIndex);
        } else {
            ui.printNotEdited(inventory);
        }
        return new CommandResult(false, changed);
    }

    @Override
    public CommandResult undo(CommandContext context) {
        context.getTargetList().editCard(targetIndex,oldName,oldQuantity,oldPrice);
        context.getUi().printUndoSuccess(context.getTargetList());
        return new CommandResult(false);
    }
}

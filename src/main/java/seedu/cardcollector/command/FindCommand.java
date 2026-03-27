package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String name;
    private final Float price;
    private final Integer quantity;

    public FindCommand(String name, Float price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var ui = context.getUi();
        var inventory = context.getTargetList();
        ArrayList<Card> results = inventory.findCards(name, price, quantity);
        ui.printFound(results);
        return new CommandResult(false);
    }
}

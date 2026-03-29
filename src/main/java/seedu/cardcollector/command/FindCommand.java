package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String name;
    private final Float price;
    private final Integer quantity;
    private final String cardSet;
    private final String rarity;
    private final String condition;
    private final String language;
    private final String cardNumber;
    private final String tag;

    public FindCommand(String name, Float price, Integer quantity,
            String cardSet, String rarity, String condition, String language, String cardNumber, String tag) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cardSet = cardSet;
        this.rarity = rarity;
        this.condition = condition;
        this.language = language;
        this.cardNumber = cardNumber;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var ui = context.getUi();
        var inventory = context.getTargetList();
        ArrayList<Card> results = inventory.findCards(
                name, price, quantity, cardSet, rarity, condition, language, cardNumber, tag);
        ui.printFound(results);
        return new CommandResult(false);
    }
}

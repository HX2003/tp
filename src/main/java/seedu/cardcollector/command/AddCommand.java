package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;
import java.util.UUID;

public class AddCommand extends Command {
    private final UUID uid;
    private final String name;
    private final int quantity;
    private final float price;

    private Card addedCard;
    private int addedIndex;
    private boolean wasMerged = false;

    public AddCommand(UUID uid, String name, int quantity, float price) {
        this.uid = uid;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isReversible = true;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        var inventory = context.getTargetList();
        this.addedCard = new Card.Builder()
                .uid(uid)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();

        for (int i = 0; i < inventory.getSize(); i++) {
            Card existing = inventory.getCard(i);
            if (existing.getName().equalsIgnoreCase(name) && existing.getPrice() == price) {
                this.wasMerged = true;
                this.addedIndex = i;
                break;
            }
        }

        inventory.addCard(addedCard);

        if (!wasMerged) {
            this.addedIndex = inventory.getCards().size() - 1;
        }

        context.getUi().printAdded(inventory);
        return new CommandResult(false);
    }

    @Override
    public CommandResult undo(CommandContext context) {
        var inventory = context.getTargetList();

        if (wasMerged) {
            Card existing = inventory.getCard(addedIndex);
            int restoredQuantity = existing.getQuantity() - quantity;
            inventory.editCard(addedIndex, null, restoredQuantity, null);
        } else {
            inventory.removeCardByIndex(addedIndex);
        }

        context.getUi().printUndoSuccess(inventory);
        return new CommandResult(false);
    }
}

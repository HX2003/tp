package seedu.cardcollector.command;

import seedu.cardcollector.card.Card;
import seedu.cardcollector.card.CardsAnalytics;
import seedu.cardcollector.card.CardsList;

public class AnalyticsCommand extends Command {
    private static final int MOST_EXPENSIVE_LIMIT = 3;
    private static final int TOP_SET_LIMIT = 3;

    @Override
    public CommandResult execute(CommandContext context) {
        CardsList targetList = context.getTargetList();
        CardsAnalytics analytics = targetList.getAnalytics(MOST_EXPENSIVE_LIMIT, TOP_SET_LIMIT);
        String listName = targetList == context.getWishlist() ? "wishlist" : "inventory";

        int cardsWithNotes = 0;
        int cardsWithSet = 0;
        int expensiveCards = 0;
        double maxPrice = 0;
        double minPrice = 0;
        boolean isFirstCard = true;

        for (Card card : targetList.getCards()) {
            if (card.getNote() != null && !card.getNote().isBlank()) {
                cardsWithNotes++;
            }

            if (card.getCardSet() != null && !card.getCardSet().isBlank()) {
                cardsWithSet++;
            }

            if (card.getPrice() > 100) {
                expensiveCards++;
            }

            if (isFirstCard) {
                maxPrice = card.getPrice();
                minPrice = card.getPrice();
                isFirstCard = false;
            } else {
                if (card.getPrice() > maxPrice) {
                    maxPrice = card.getPrice();
                }

                if (card.getPrice() < minPrice) {
                    minPrice = card.getPrice();
                }
            }
        }

        context.getUi().printAnalytics(listName, analytics);

        System.out.println("Extra analytics summary:");
        System.out.println("Cards with notes: " + cardsWithNotes);
        System.out.println("Cards with set information: " + cardsWithSet);
        System.out.println("Cards above $100: " + expensiveCards);
        System.out.println("Highest single-card price: $" + String.format("%.2f", maxPrice));
        System.out.println("Lowest single-card price: $" + String.format("%.2f", minPrice));
        context.getUi().printBorder();

        return new CommandResult(false, false);
    }
}
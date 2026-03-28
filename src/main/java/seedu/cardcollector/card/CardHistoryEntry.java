package seedu.cardcollector.card;

import java.util.LinkedHashMap;

public class CardHistoryEntry {
    private final CardHistoryType cardHistoryType;

    private final Card previous;
    private final Card current;

    CardHistoryEntry(Card previous, Card current) {
        assert previous != current : "Cards in history should not share the same reference";

        this.previous = previous;
        this.current = current;

        int changedQuantity = getChangedQuantity();
        if (changedQuantity > 0) {
            this.cardHistoryType = CardHistoryType.ADDED;
        } else if (changedQuantity < 0) {
            this.cardHistoryType = CardHistoryType.REMOVED;
        } else {
            this.cardHistoryType = CardHistoryType.MODIFIED;
        }
    }

    public CardHistoryEntry copy() {
        Card copyOfPrevious = null;
        Card copyOfCurrent = null;

        if (previous != null) {
            copyOfPrevious = previous.copy();
        }

        if (current != null) {
            copyOfCurrent = current.copy();
        }

        return new CardHistoryEntry(copyOfPrevious, copyOfCurrent);
    }

    public Card getPrevious() {
        return previous;
    }

    public Card getCurrent() {
        return current;
    }

    public Card getMostRecent() {
        Card current = getCurrent();

        if (current != null) {
            return current;
        }

        return previous;
    }
    public CardHistoryType getCardHistoryType() {
        return cardHistoryType;
    }

    public int getChangedQuantity() {
        int currentQuantity = 0;
        int previousQuantity = 0;

        if (current != null) {
            currentQuantity = current.getQuantity();
        }

        if (previous != null) {
            previousQuantity = previous.getQuantity();
        }

        return currentQuantity - previousQuantity;
    }

    public LinkedHashMap<String, CardFieldChange> getChangedFields() {
        assert previous != null;
        assert current != null;
        assert cardHistoryType == CardHistoryType.MODIFIED;

        LinkedHashMap<String, CardFieldChange> changedFields = new LinkedHashMap<>();

        String previousName = previous.getName();
        String currentName = current.getName();
        if (!currentName.equals(previousName)) {
            CardFieldChange change = new CardFieldChange(
                    previousName, currentName);
            changedFields.put("name", change);
        }

        float previousPrice = previous.getPrice();
        float currentPrice = current.getPrice();
        if (currentPrice != previousPrice) {
            CardFieldChange change = new CardFieldChange(
                    String.valueOf(previousPrice), String.valueOf(currentPrice));
            changedFields.put("price", change);
        }

        return changedFields;
    }
}

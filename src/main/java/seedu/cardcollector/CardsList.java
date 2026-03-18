package seedu.cardcollector;

import java.time.Instant;
import java.util.ArrayList;

public class CardsList {
    private final ArrayList<Card> cards;
    private final ArrayList<Card> removedCards;
    private final ArrayList<Card> addedCards;

    public CardsList() {
        this.cards = new ArrayList<Card>();
        this.removedCards = new ArrayList<Card>();
        this.addedCards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        if (card == null) {
            System.out.println("seedu.cardcollector.Card not found!");
            return;
        }

        Instant currentInstant = Instant.now();
        card.setLastAdded(currentInstant);
        card.setLastModified(currentInstant);
        cards.add(card);
        addedCards.add(card);
    }

    public void removeCard(int index) {
        assert cards != null : "Cards list should be initialized";

        int sizeBefore = cards.size();

        if (index < 0) {
            System.out.println("Index cannot be 0 or negative!");
            assert cards.size() == sizeBefore;
        } else if (index >= cards.size()) {
            System.out.println("Index cannot be greater than inventory size!");
            assert cards.size() == sizeBefore;
        } else {
            Card removed = cards.remove(index);

            Instant currentInstant = Instant.now();
            removed.setLastModified(currentInstant);
            removedCards.add(removed);

            assert cards.size() == sizeBefore - 1 : "Size should decrease after removal";
            assert removedCards.contains(removed) : "Removed card must be tracked";
        }
    }

    public boolean removeCardByName(String name) {
        assert name != null : "Name should not be null";

        int sizeBefore = cards.size();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getName().equalsIgnoreCase(name)) {
                Card removed = cards.remove(i);

                Instant currentInstant = Instant.now();
                removed.setLastModified(currentInstant);
                removedCards.add(removed);

                assert cards.size() == sizeBefore - 1 : "Size should decrease after removal";
                assert removedCards.contains(removed) : "Removed card must be tracked";

                return true;
            }
        }

        assert cards.size() == sizeBefore : "Size should not change if not found";
        return false;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Card> getRemovedCards() {
        return removedCards;
    }

    public ArrayList<Card> getAddedCards() {
        return addedCards;
    }

    public int getSize() {
        return cards.size();
    }

    public int getRemovedSize() {
        return removedCards.size();
    }

    public int getAddedSize() {
        return addedCards.size();
    }

    public ArrayList<Card> findCards(String name, Float price, Integer quantity) {
        assert cards != null : "Cards inventory should be initialized before searching";

        ArrayList<Card> results = new ArrayList<>();
        for (Card card : cards) {
            boolean matches = true;
            if (name != null && !card.getName().toLowerCase().contains(name.toLowerCase())) {
                matches = false;
            }
            if (price != null && card.getPrice() != price) {
                matches = false;
            }
            if (quantity != null && card.getQuantity() != quantity) {
                matches = false;
            }
            if (matches) {
                results.add(card);
            }
        }

        assert results != null : "The results list should not be null";
        assert results.size() <= cards.size();

        return results;
    }
}
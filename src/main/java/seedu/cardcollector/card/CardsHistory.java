package seedu.cardcollector.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stores a chronological history of card state changes as a list of CardHistoryEntry.
 * Each entry records the change from a previous card to a current card.
 * <p>
 * Note that a single {@code CardHistoryEntry} is unable to fully record
 * when both quantity and other fields changes. So multiple entries may be required
 * to represent a single edit command.
 */
public class CardsHistory {
    private final ArrayList<CardHistoryEntry> historyList;

    /**
     * Creates an empty card history.
     */
    public CardsHistory() {
        historyList = new ArrayList<CardHistoryEntry>();
    }

    /**
     * Creates a history from a flattened list of alternating previous/current cards.
     * The list size must be even.
     *
     * @param flattenedCards A list where each pair (previous, current) forms one entry.
     */
    public CardsHistory(ArrayList<Card> flattenedCards) {
        historyList = new ArrayList<>();

        assert (flattenedCards.size() % 2 == 0) :
                "Size of flattened cards should be multiple of 2";

        for (int i = 0; i < flattenedCards.size() - 1; i += 2) {
            historyList.add(new CardHistoryEntry(
                    flattenedCards.get(i), flattenedCards.get(i + 1)));
        }
    }

    /**
     * Returns a flattened list by constructing pairs of alternating previous/current from every entry.
     *
     * @return ArrayList containing previous and current cards in alternating order.
     */
    public ArrayList<Card> getFlattenedCards() {
        return historyList.stream()
                .flatMap(entry -> Stream.of(entry.getPrevious(), entry.getCurrent()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a sorted shallow copy of the history list, optionally reversed.
     *
     * @param isDescending If true, returns entries in reverse order (newest first).
     * @return A new list of CardHistoryEntry in the requested order.
     */
    public ArrayList<CardHistoryEntry> getSortedHistoryList(boolean isDescending) {
        ArrayList<CardHistoryEntry> historyListCopy = new ArrayList<>(historyList);

        if (isDescending) {
            Collections.reverse(historyListCopy);
        }

        return historyListCopy;
    }

    /**
     * Creates a history entry from a previous and current card and adds it to the history.
     *
     * @param previous The card before the change, or null if the card was newly added.
     * @param current The card after the change, or null if the card was removed.
     */
    public void add(Card previous, Card current) {
        CardHistoryEntry entry = new CardHistoryEntry(previous, current);
        historyList.add(entry);
    }

    /**
     * Adds the given history entry to the history.
     *
     * @param entry The history entry to add.
     */
    public void add(CardHistoryEntry entry) {
        historyList.add(entry);
    }

    /**
     * Returns the number of entries in the history.
     *
     * @return The size of the history list.
     */
    public int getSize() {
        return historyList.size();
    }

    /**
     * Returns a deep copy of this CardsHistory.
     *
     * @return A new CardsHistory instance.
     */
    public CardsHistory copy() {
        CardsHistory newCardHistory = new CardsHistory();
        for (CardHistoryEntry entry: historyList) {
            newCardHistory.add(entry.copy());
        }
        return newCardHistory;
    }
}

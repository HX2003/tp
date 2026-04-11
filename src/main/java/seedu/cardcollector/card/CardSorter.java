package seedu.cardcollector.card;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardSorter {
    /**
     * Returns a comparator for sorting cards according to the specified criteria.
     *
     * @param criteria The sorting criteria to use (e.g. INDEX, NAME, QUANTITY, etc.).
     * @return A comparator that sorts cards by the given criteria.
     */
    public static Comparator<Card> getSortComparator(CardSortCriteria criteria) {
        switch (criteria) {
        case INDEX -> {
            assert false : "index criteria should not use a comparator";
        }
        case NAME -> {
            return Comparator.comparing(Card::getName, String.CASE_INSENSITIVE_ORDER);
        }
        case QUANTITY -> {
            return Comparator.comparingInt(Card::getQuantity);
        }
        case PRICE -> {
            return Comparator.comparingDouble(Card::getPrice);
        }
        case SET -> {
            return Comparator.comparing(Card::getCardSet,
                    Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        }
        case RARITY -> {
            return Comparator.comparing(Card::getRarity,
                    Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        }
        case CONDITION -> {
            return Comparator.comparing(Card::getCondition,
                    Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        }
        case LANGUAGE -> {
            return Comparator.comparing(Card::getLanguage,
                    Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        }
        case NUMBER -> {
            return Comparator.comparing(Card::getCardNumber,
                    Comparator.nullsFirst(getCardNumberComparator()));
        }
        case NOTE -> {
            return Comparator.comparing(Card::getNote,
                    Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        }
        case ADDED -> {
            return Comparator.comparing(Card::getLastAdded,
                    Comparator.nullsFirst(Instant::compareTo));
        }
        case MODIFIED -> {
            return Comparator.comparing(Card::getLastModified,
                    Comparator.nullsFirst(Instant::compareTo));
        }
        case REMOVED -> {
            return Comparator.comparing(Card::getLastRemoved,
                    Comparator.nullsFirst(Instant::compareTo));
        }
        default -> {
            assert false : "Unhandled CardSortCriteria";
        }
        }
        return null;
    }

    /**
     * Returns a new sorted ArrayList of cards by the specified criteria,
     * results can be limited to a maximum size and ordered ascending or descending.
     *
     * @param cards The ArrayList of cards.
     * @param criteria The criteria to sort by (e.g. INDEX, NAME, QUANTITY, etc.).
     * @param maxLimit The maximum number of cards to return. Uses defaultMaxLimit if negative.
     * @param defaultMaxLimit The limit applied when maxLimit is negative.
     * @param isDescending True for descending order, false for ascending.
     * @return A new sorted list of cards.
     */
    public static ArrayList<Card> sort(
            ArrayList<Card> cards,
            CardSortCriteria criteria,
            int maxLimit,
            int defaultMaxLimit,
            boolean isDescending) {

        if (cards.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Card> cardsCopy = new ArrayList<>(cards);

        if (criteria == CardSortCriteria.INDEX && isDescending) {
            // Directly apply ascending/descending order,
            // without using comparator
            Collections.reverse(cardsCopy);
        }

        Stream<Card> cardsStream = cardsCopy.stream();
        if (criteria != CardSortCriteria.INDEX) {
            Comparator<Card> comparator = getSortComparator(criteria);

            assert comparator != null : "No available comparator for criteria";

            // Apply ascending/descending order using comparator
            if (isDescending) {
                comparator = comparator.reversed();
            }

            cardsStream = cardsStream.sorted(comparator);
        }

        int recordsLimit = (maxLimit < 0) ? defaultMaxLimit :
                Math.min(cards.size(), maxLimit);

        return cardsStream
                .limit(recordsLimit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a comparator that compares card number strings by their numeric segments.
     * Empty or blank strings are treated as [0]. Each / delimited segment
     * is compared numerically; missing segments default to 0.
     *
     * @return A comparator for card number strings.
     */
    private static Comparator<String> getCardNumberComparator() {
        return (Comparator<String> & Serializable) (s1, s2) -> {
            long[] nums1 = parseToLongArray(s1);
            long[] nums2 = parseToLongArray(s2);

            int maxLength = Math.max(nums1.length, nums2.length);

            for (int i = 0; i < maxLength; i++) {
                long num1 = (i < nums1.length) ? nums1[i] : 0;
                long num2 = (i < nums2.length) ? nums2[i] : 0;

                if (num1 != num2) {
                    return Long.compare(num1, num2);
                }
            }

            // Tie-breaker
            return Integer.compare(nums1.length, nums2.length);
        };
    }

    /**
     * Parses a card number string into an array of numeric long segments.
     * Empty or blank input yields [0]. Consecutive slashes produce zero segments.
     *
     * @param s The card number string to parse.
     * @return An array of longs representing each numeric segment.
     */
    private static long[] parseToLongArray(String s) {
        if (s == null || s.isBlank()) {
            return new long[]{0};
        }

        // Split keeping empty parts, as these will be assumed to mean 0
        String[] parts = s.split("/", -1);

        try {
            return Arrays.stream(parts)
                    .mapToLong(part -> part.isEmpty() ? 0 : Long.parseLong(part))
                    .toArray();
        } catch (NumberFormatException e) {
            return new long[]{0};
        }
    }
}

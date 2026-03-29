package seedu.cardcollector.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsAnalytics {
    private final int distinctCards;
    private final int totalQuantity;
    private final double totalValue;
    private final ArrayList<CardMetric> mostExpensiveCards;
    private final ArrayList<SetMetric> topSetsByCount;

    public CardsAnalytics(int distinctCards, int totalQuantity, double totalValue,
                          List<CardMetric> mostExpensiveCards, List<SetMetric> topSetsByCount) {
        this.distinctCards = distinctCards;
        this.totalQuantity = totalQuantity;
        this.totalValue = totalValue;
        this.mostExpensiveCards = new ArrayList<>(mostExpensiveCards);
        this.topSetsByCount = new ArrayList<>(topSetsByCount);
    }

    public int getDistinctCards() {
        return distinctCards;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public List<CardMetric> getMostExpensiveCards() {
        return Collections.unmodifiableList(mostExpensiveCards);
    }

    public List<SetMetric> getTopSetsByCount() {
        return Collections.unmodifiableList(topSetsByCount);
    }

    public static class CardMetric {
        private final Card card;
        private final double lineValue;

        public CardMetric(Card card, double lineValue) {
            this.card = card;
            this.lineValue = lineValue;
        }

        public Card getCard() {
            return card;
        }

        public double getLineValue() {
            return lineValue;
        }
    }

    public static class SetMetric {
        private final String setName;
        private final int totalCount;

        public SetMetric(String setName, int totalCount) {
            this.setName = setName;
            this.totalCount = totalCount;
        }

        public String getSetName() {
            return setName;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }
}

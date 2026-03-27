package seedu.cardcollector.card;

public enum CardSortCriteria {
    NAME("name"),
    QUANTITY("quantity"),
    PRICE("price"),
    LAST_ADDED("added"),
    LAST_MODIFIED("modified"),
    LAST_REMOVED("removed");

    private final String name;

    CardSortCriteria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

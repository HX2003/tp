package seedu.cardcollector;

public enum CardSortCriteria {
    NAME("name"),
    QUANTITY("quantity"),
    PRICE("price"),
    LAST_ADDED("added"),
    LAST_MODIFIED("modified");

    private final String name;

    CardSortCriteria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

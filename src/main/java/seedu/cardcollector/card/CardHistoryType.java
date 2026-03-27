package seedu.cardcollector.card;

public enum CardHistoryType {
    ADDED("added"),
    REMOVED("removed"),
    MODIFIED("modified"),
    ENTIRE("entire");

    private final String name;

    CardHistoryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

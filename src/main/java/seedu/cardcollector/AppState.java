package seedu.cardcollector;

import seedu.cardcollector.card.CardsList;

public class AppState {
    private final CardsList inventory;
    private final CardsList wishlist;

    public AppState(CardsList inventory, CardsList wishlist) {
        this.inventory = inventory;
        this.wishlist = wishlist;
    }

    public CardsList getInventory() {
        return inventory;
    }

    public CardsList getWishlist() {
        return wishlist;
    }

    public AppState deepCopy() {
        return new AppState(inventory.deepCopy(), wishlist.deepCopy());
    }
}

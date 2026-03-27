package seedu.cardcollector.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardsListTest {

    //@@author bryankuah
    @Test
    public void addCard_card_success() {
        CardsList cardsList = new CardsList();

        Card card = new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .build();
        cardsList.addCard(card);

        assertEquals(1, cardsList.getSize());
        assertEquals(card, cardsList.getCard(0));
    }

    @Test
    public void findCards_byName_success() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Charizard")
                .price(15.00f)
                .quantity(2)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Pikachu VMAX")
                .price(20.00f)
                .quantity(3)
                .build());

        // Case-insensitive and partial match for "pika"
        ArrayList<Card> results = cardsList.findCards("pika", null, null);
        
        assertEquals(2, results.size());
        assertEquals("Pikachu", results.get(0).getName());
        assertEquals("Pikachu VMAX", results.get(1).getName());
    }

    @Test
    public void findCards_byPrice_success() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Charizard")
                .price(10.00f)
                .quantity(2)
                .build());

        // Exact price match
        ArrayList<Card> results = cardsList.findCards(null, 10.00f, null);
        
        assertEquals(1, results.size());
        assertEquals("Charizard", results.get(0).getName());
    }

    @Test
    public void findCards_byQuantity_success() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Bulbasaur")
                .price(2.00f)
                .quantity(5)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Squirtle")
                .price(3.00f)
                .quantity(1)
                .build());

        // Exact quantity match
        ArrayList<Card> results = cardsList.findCards(null, null, 5);
        
        assertEquals(1, results.size());
        assertEquals("Bulbasaur", results.get(0).getName());
    }

    @Test
    public void findCards_multipleAttributes_success() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Mewtwo")
                .price(20.00f)
                .quantity(3)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Mewtwo")
                .price(5.00f)
                .quantity(1)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Mew")
                .price(15.00f)
                .quantity(3)
                .build());

        // Matches both Name (contains "Mew") AND Quantity (exactly 3)
        ArrayList<Card> results = cardsList.findCards("Mew", null, 3);
        
        assertEquals(2, results.size());
        assertEquals(20.00f, results.get(0).getPrice());
        assertEquals(15.00f, results.get(1).getPrice());
    }

    @Test
    public void findCards_noMatch_returnsEmptyList() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Eevee")
                .price(4.00f)
                .quantity(1)
                .build());

        // Searching for attributes that don't exist
        ArrayList<Card> results = cardsList.findCards("Snorlax", 100.00f, null);
        
        assertEquals(0, results.size());
    }

    @Test
    public void editCard_partialAndFullEdit_success() {
        CardsList cardsList = new CardsList();

        Card original = new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .build();
        cardsList.addCard(original);

        // partial edit (only name + quantity)
        cardsList.editCard(0, "Pikachu VMAX", 5, null);
        assertEquals("Pikachu VMAX", cardsList.getCard(0).getName());
        assertEquals(5, cardsList.getCard(0).getQuantity());
        assertEquals(5.50f, cardsList.getCard(0).getPrice()); // price unchanged

        // full edit
        cardsList.editCard(0, "Charizard", 10, 25.0f);
        assertEquals("Charizard", cardsList.getCard(0).getName());
        assertEquals(10, cardsList.getCard(0).getQuantity());
        assertEquals(25.0f, cardsList.getCard(0).getPrice());
    }

    @Test
    public void inventoryAndWishlistAreIndependent_success() {
        CardsList inventory = new CardsList();
        CardsList wishlist = new CardsList();

        // Add to inventory only
        Card invCard = new Card.Builder()
                .name("Pikachu")
                .price(5.5f)
                .quantity(1)
                .build();
        inventory.addCard(invCard);

        // Add to wishlist only
        Card wishCard = new Card.Builder()
                .name("Charizard")
                .price(99.99f)
                .quantity(1)
                .build();
        wishlist.addCard(wishCard);

        // Verify separation
        assertEquals(1, inventory.getSize());
        assertEquals(1, wishlist.getSize());
        assertEquals("Pikachu", inventory.getCard(0).getName());
        assertEquals("Charizard", wishlist.getCard(0).getName());

        // Remove from one doesn't affect the other
        inventory.removeCardByIndex(0);
        assertEquals(0, inventory.getSize());
        assertEquals(1, wishlist.getSize());
    }

    @Test
    public void getTwoDifferentCardsForComparison_success() {
        CardsList cardsList = new CardsList();

        Card card1 = new Card.Builder()
                .name("Pikachu")
                .price(5.5f)
                .quantity(1)
                .build();
        Card card2 = new Card.Builder()
                .name("Charizard")
                .price(99.99f)
                .quantity(1)
                .build();

        cardsList.addCard(card1);
        cardsList.addCard(card2);

        // Verify cards can be retrieved for comparison (same style as edit test)
        assertEquals("Pikachu | Quantity: 1 | Price: 5.5", cardsList.getCard(0).toString());
        assertEquals("Charizard | Quantity: 1 | Price: 99.99", cardsList.getCard(1).toString());
    }

    @Test
    public void acquired_movesCardFromWishlistToInventory_success() {
        CardsList inventory = new CardsList();
        CardsList wishlist = new CardsList();

        Card wishCard = new Card.Builder()
                .name("Charizard")
                .price(99.99f)
                .quantity(1)
                .build();
        wishlist.addCard(wishCard);

        // Simulate acquired behaviour
        int index = 0;
        Card card = wishlist.getCard(index);
        wishlist.removeCardByIndex(index);
        inventory.addCard(card);

        assertEquals(0, wishlist.getSize());
        assertEquals(1, inventory.getSize());
        assertEquals("Charizard", inventory.getCard(0).getName());
    }

    @Test
    public void reorder_byNameAscending_success() {
        CardsList cardsList = new CardsList();

        Card cardZ = new Card.Builder().name("Zebra").price(10).quantity(1).build();
        Card cardA = new Card.Builder().name("Apple").price(20).quantity(1).build();
        Card cardM = new Card.Builder().name("Monkey").price(15).quantity(1).build();

        cardsList.addCard(cardZ);
        cardsList.addCard(cardA);
        cardsList.addCard(cardM);

        cardsList.reorder(CardSortCriteria.NAME, true);

        ArrayList<Card> ordered = cardsList.getCards();
        assertEquals("Apple", ordered.get(0).getName());
        assertEquals("Monkey", ordered.get(1).getName());
        assertEquals("Zebra", ordered.get(2).getName());
    }

    @Test
    public void reorder_byPriceDescending_success() {
        CardsList cardsList = new CardsList();

        Card cardLow = new Card.Builder().name("Low").price(5).quantity(1).build();
        Card cardHigh = new Card.Builder().name("High").price(100).quantity(1).build();
        Card cardMid = new Card.Builder().name("Mid").price(50).quantity(1).build();

        cardsList.addCard(cardLow);
        cardsList.addCard(cardHigh);
        cardsList.addCard(cardMid);

        cardsList.reorder(CardSortCriteria.PRICE, false);

        ArrayList<Card> ordered = cardsList.getCards();
        assertEquals("High", ordered.get(0).getName());
        assertEquals("Mid", ordered.get(1).getName());
        assertEquals("Low", ordered.get(2).getName());
    }

    //@@author HX2003
    @Test
    public void cardsList_addEditRemove_historySuccess() {
        CardsList cardsList = new CardsList();

        Card card0 = new Card.Builder().name("Zero").price(5.0f).quantity(1).build();
        Card card1 = new Card.Builder().name("One").price(15.0f).quantity(5).build();

        // Add various cards
        cardsList.addCard(card0);
        cardsList.addCard(card1);

        // Remove a card entirely
        cardsList.removeCardByIndex(1);

        // Edit name of the card only
        cardsList.editCard(0, "Zero noro", null, null);

        // Edit quantity (from 1 to 5) of the card only
        cardsList.editCard(0, null, 5, null);

        // Edit quantity (from 5 to 4) of the card only
        cardsList.editCard(0, null, 4, null);

        // Edit quantity (from 4 to 4) of the card only,
        // but actually quantity was not changed
        cardsList.editCard(0, null, 4, null);

        // Edit quantity (from 4 to 3) of the card,
        // at the same time change its price
        cardsList.editCard(0, null, 3, 9.99f);

        CardsHistory history = cardsList.getHistory();
        ArrayList<CardHistoryEntry> historyList = history.getSortedHistoryList(false);

        // Now check whether the history is correct
        assertEquals(CardHistoryType.ADDED, historyList.get(0).getCardHistoryType());
        assertEquals(card0.getUid(), historyList.get(0).getMostRecent().getUid());

        assertEquals(CardHistoryType.ADDED, historyList.get(1).getCardHistoryType());
        assertEquals(card1.getUid(), historyList.get(1).getMostRecent().getUid());

        assertEquals(CardHistoryType.REMOVED, historyList.get(2).getCardHistoryType());
        assertEquals(card1.getUid(), historyList.get(2).getMostRecent().getUid());

        assertEquals(CardHistoryType.MODIFIED, historyList.get(3).getCardHistoryType());
        assertEquals("Zero noro", historyList.get(3).getMostRecent().getName());

        assertEquals(CardHistoryType.ADDED, historyList.get(4).getCardHistoryType());
        assertEquals(5, historyList.get(4).getMostRecent().getQuantity());

        assertEquals(CardHistoryType.REMOVED, historyList.get(5).getCardHistoryType());
        assertEquals(4, historyList.get(5).getMostRecent().getQuantity());

        assertEquals(CardHistoryType.REMOVED, historyList.get(6).getCardHistoryType());
        assertEquals(3, historyList.get(6).getMostRecent().getQuantity());

        assertEquals(CardHistoryType.MODIFIED, historyList.get(7).getCardHistoryType());
        assertEquals(9.99f, historyList.get(7).getMostRecent().getPrice());
    }
    //@@author
}

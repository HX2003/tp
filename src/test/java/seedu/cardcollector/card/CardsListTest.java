package seedu.cardcollector.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ArrayList<Card> results = cardsList.findCards("pika", null, null, null, null, null, null, null, null);
        
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
        ArrayList<Card> results = cardsList.findCards(null, 10.00f, null, null, null, null, null, null, null);
        
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
        ArrayList<Card> results = cardsList.findCards(null, null, 5, null, null, null, null, null, null);
        
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
        ArrayList<Card> results = cardsList.findCards("Mew", null, 3, null, null, null, null, null, null);
        
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
        ArrayList<Card> results = cardsList.findCards("Snorlax", 100.00f, null, null, null, null, null, null, null);
        
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
        cardsList.editCard(0, "Pikachu VMAX", 5, null, null, null, null, null, null);
        assertEquals("Pikachu VMAX", cardsList.getCard(0).getName());
        assertEquals(5, cardsList.getCard(0).getQuantity());
        assertEquals(5.50f, cardsList.getCard(0).getPrice()); // price unchanged

        // full edit
        cardsList.editCard(0, "Charizard", 10, 25.0f, null, null, null, null, null);
        assertEquals("Charizard", cardsList.getCard(0).getName());
        assertEquals(10, cardsList.getCard(0).getQuantity());
        assertEquals(25.0f, cardsList.getCard(0).getPrice());
    }

    @Test
    public void addAndFindCards_withMetadata_success() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .cardSet("Base Set")
                .rarity("Rare")
                .condition("Near Mint")
                .language("English")
                .cardNumber("58/102")
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .cardSet("Jungle")
                .rarity("Common")
                .condition("Played")
                .language("Japanese")
                .cardNumber("12/64")
                .build());

        ArrayList<Card> results = cardsList.findCards(
                null, null, null, "base", "rare", "near", "english", "58/102", null);

        assertEquals(1, results.size());
        assertEquals("Base Set", results.get(0).getCardSet());
    }

    @Test
    public void addTagRemoveTagAndFindByTag_success() {
        CardsList cardsList = new CardsList();
        cardsList.addCard(new Card.Builder()
                .name("Pikachu")
                .price(5.50f)
                .quantity(1)
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Charizard")
                .price(99.99f)
                .quantity(1)
                .build());

        assertTrue(cardsList.addTag(0, "deck"));
        assertFalse(cardsList.addTag(0, "deck"));
        assertTrue(cardsList.getCard(0).hasTag("DECK"));

        ArrayList<Card> taggedResults = cardsList.findCards(
                null, null, null, null, null, null, null, null, "deck");
        assertEquals(1, taggedResults.size());
        assertEquals("Pikachu", taggedResults.get(0).getName());

        assertTrue(cardsList.removeTag(0, "deck"));
        assertFalse(cardsList.getCard(0).hasTag("deck"));
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

    @Test
    public void getAnalytics_populatedList_success() {
        CardsList cardsList = new CardsList();

        cardsList.addCard(new Card.Builder()
                .name("Charizard")
                .price(100.0f)
                .quantity(2)
                .cardSet("Base Set")
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Blastoise")
                .price(80.0f)
                .quantity(1)
                .cardSet("Base Set")
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Pikachu")
                .price(20.0f)
                .quantity(4)
                .cardSet("Jungle")
                .build());
        cardsList.addCard(new Card.Builder()
                .name("Mew")
                .price(90.0f)
                .quantity(1)
                .build());

        CardsAnalytics analytics = cardsList.getAnalytics(3, 3);

        assertEquals(4, analytics.getDistinctCards());
        assertEquals(8, analytics.getTotalQuantity());
        assertEquals(450.0, analytics.getTotalValue(), 0.001);

        assertEquals(3, analytics.getMostExpensiveCards().size());
        assertEquals("Charizard", analytics.getMostExpensiveCards().get(0).getCard().getName());
        assertEquals("Mew", analytics.getMostExpensiveCards().get(1).getCard().getName());
        assertEquals("Blastoise", analytics.getMostExpensiveCards().get(2).getCard().getName());
        assertEquals(200.0, analytics.getMostExpensiveCards().get(0).getLineValue(), 0.001);

        assertEquals(3, analytics.getTopSetsByCount().size());
        assertEquals("Jungle", analytics.getTopSetsByCount().get(0).getSetName());
        assertEquals(4, analytics.getTopSetsByCount().get(0).getTotalCount());
        assertEquals("Base Set", analytics.getTopSetsByCount().get(1).getSetName());
        assertEquals(3, analytics.getTopSetsByCount().get(1).getTotalCount());
        assertEquals("Unspecified Set", analytics.getTopSetsByCount().get(2).getSetName());
        assertEquals(1, analytics.getTopSetsByCount().get(2).getTotalCount());
    }

    @Test
    public void getAnalytics_emptyList_success() {
        CardsList cardsList = new CardsList();

        CardsAnalytics analytics = cardsList.getAnalytics(3, 3);

        assertEquals(0, analytics.getDistinctCards());
        assertEquals(0, analytics.getTotalQuantity());
        assertEquals(0.0, analytics.getTotalValue(), 0.001);
        assertTrue(analytics.getMostExpensiveCards().isEmpty());
        assertTrue(analytics.getTopSetsByCount().isEmpty());
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
        cardsList.editCard(0, "Zero noro", null, null, null, null, null, null, null);

        // Edit quantity (from 1 to 5) of the card only
        cardsList.editCard(0, null, 5, null, null, null, null, null, null);

        // Edit quantity (from 5 to 4) of the card only
        cardsList.editCard(0, null, 4, null, null, null, null, null, null);

        // Edit quantity (from 4 to 4) of the card only,
        // but actually quantity was not changed
        cardsList.editCard(0, null, 4, null, null, null, null, null, null);

        // Edit quantity (from 4 to 3) of the card,
        // at the same time change its price
        cardsList.editCard(0, null, 3, 9.99f, null, null, null, null, null);

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

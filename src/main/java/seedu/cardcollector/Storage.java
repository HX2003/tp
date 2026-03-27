package seedu.cardcollector;

import seedu.cardcollector.card.Card;
import seedu.cardcollector.card.CardsHistory;
import seedu.cardcollector.card.CardsList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Storage {
    private static final String FILE_HEADER = "CARDCOLLECTOR_STORAGE_V2";
    private static final String NULL_VALUE = "-";

    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public static Storage createDefault() {
        return new Storage(Path.of("data", "cardcollector.txt"));
    }

    public AppState load() throws IOException {
        if (!Files.exists(filePath)) {
            return new AppState(new CardsList(), new CardsList());
        }

        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        if (lines.isEmpty() || !FILE_HEADER.equals(lines.get(0))) {
            throw new IOException("Invalid storage file header");
        }

        Map<String, ArrayList<Card>> sections = new HashMap<>();
        String currentSection = null;

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("SECTION ")) {
                currentSection = line.substring("SECTION ".length());
                sections.putIfAbsent(currentSection, new ArrayList<>());
                continue;
            }

            if ("ENDSECTION".equals(line)) {
                currentSection = null;
                continue;
            }

            if (currentSection == null || line.isBlank()) {
                continue;
            }

            sections.get(currentSection).add(parseCard(line));
        }

        return new AppState(
                buildList(sections, "inventory", "inventory_history"),
                buildList(sections, "wishlist", "wishlist_history"));
    }

    public void save(AppState state) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add(FILE_HEADER);
        appendSection(lines, "inventory", state.getInventory().getCards());
        appendSection(lines, "inventory_history", state.getInventory().getHistory().getFlattenedCards());
        appendSection(lines, "wishlist", state.getWishlist().getCards());
        appendSection(lines, "wishlist_history", state.getWishlist().getHistory().getFlattenedCards());

        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }

    public Path getFilePath() {
        return filePath;
    }

    private static CardsList buildList(Map<String, ArrayList<Card>> sections,
                                       String cardsSection,
                                       String historySection) {
        ArrayList<Card> flattenedCards = sections.getOrDefault(historySection, new ArrayList<>());
        CardsHistory history = new CardsHistory(flattenedCards);

        return new CardsList(sections.getOrDefault(cardsSection, new ArrayList<>()), history);
    }

    private static void appendSection(ArrayList<String> lines, String name, ArrayList<Card> cards) {
        lines.add("SECTION " + name);
        for (Card card : cards) {
            lines.add(serializeCard(card));
        }
        lines.add("ENDSECTION");
    }

    private static String serializeCard(Card card) {
        if (card == null) {
            return NULL_VALUE;
        }

        return String.join("\t",
                card.getUid().toString(),
                Base64.getEncoder().encodeToString(card.getName().getBytes(StandardCharsets.UTF_8)),
                String.valueOf(card.getQuantity()),
                String.valueOf(card.getPrice()),
                serializeInstant(card.getLastAdded()),
                serializeInstant(card.getLastModified()),
                serializeInstant(card.getLastRemoved()));
    }

    private static Card parseCard(String line) throws IOException {
        if (line.equals(NULL_VALUE)) {
            return null;
        }

        String[] parts = line.split("\t", -1);
        if (parts.length != 7) {
            throw new IOException("Malformed card record");
        }

        try {
            UUID uid = UUID.fromString(parts[0]);
            String name = new String(Base64.getDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            int quantity = Integer.parseInt(parts[2]);
            float price = Float.parseFloat(parts[3]);
            Instant lastAdded = parseInstant(parts[4]);
            Instant lastModified = parseInstant(parts[5]);
            Instant lastRemoved = parseInstant(parts[6]);

            return new Card.Builder()
                    .uid(uid)
                    .name(name)
                    .quantity(quantity)
                    .price(price)
                    .lastAdded(lastAdded)
                    .lastModified(lastModified)
                    .lastRemoved(lastRemoved)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new IOException("Malformed card record", e);
        }
    }

    private static String serializeInstant(Instant instant) {
        return instant == null ? NULL_VALUE : instant.toString();
    }

    private static Instant parseInstant(String value) {
        return NULL_VALUE.equals(value) ? null : Instant.parse(value);
    }
}

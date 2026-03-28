package seedu.cardcollector;

import seedu.cardcollector.card.Card;
import seedu.cardcollector.card.CardFieldChange;
import seedu.cardcollector.card.CardsHistory;
import seedu.cardcollector.card.CardHistoryEntry;
import seedu.cardcollector.card.CardHistoryType;
import seedu.cardcollector.card.CardsList;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Ui {
    private static final String FORMAT_UNKNOWN_COMMAND = "Unknown command \"%1$s\" entered%n";
    private static final String FORMAT_BLANK_COMMAND = "Blank command entered, did you mean to type something?%n";
    private static final String FORMAT_INVALID_ARGUMENT = "%1$s%n%n";
    private static final String FORMAT_INVALID_ARGUMENT_SYNTAX_USAGE = "Usage: \"%1$s\"%n";
    private static final String FORMAT_INVALID_ARGUMENT_EXAMPLE_USAGE = "Example: \"%1$s\"%n";

    private static final String FORMAT_HISTORY_NO_RECORD = "No relevant history found!%n";
    private static final String FORMAT_HISTORY_ADDED_RECORD = "[%1$s] + ADDED %2$s UNITS OF %3$s%n";
    private static final String FORMAT_HISTORY_MODIFIED_RECORD = "[%1$s] # MODIFIED TO %2$s%n%3$s%n";
    private static final String FORMAT_HISTORY_REMOVED_RECORD = "[%1$s] - REMOVED %2$s UNITS OF %3$s%n";
    private static final String FORMAT_HISTORY_DISPLAY_ALL_RECORDS = "Displaying all %1$d records:%n";
    private static final String FORMAT_HISTORY_DISPLAY_N_RECORDS = "Displaying latest %1$d out of %2$d records:%n";
    private static final String FORMAT_HISTORY_CHANGED_FIELD = "\"%1$s\": %2$s -> %3$s";

    private static final int HISTORY_DISPLAY_DEFAULT_LIMIT = 15;

    private final Scanner scanner;
    private final DateTimeFormatter dateTimeFormatter;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.dateTimeFormatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
    }

    public void printBorder() {
        System.out.println("_______________________________________________________");
    }

    public void echo(String input) {
        printBorder();
        System.out.println(input);
        printBorder();
    }

    public void printWelcome() {
        String logo =
                "  ____              _  ____      _ _           _             \n"
                        + " / ___|__ _ _ __ __| |/ ___|___ | | | ___  ___| |_ ___  _ __ \n"
                        + "| |   / _` | '__/ _` | |   / _ \\| | |/ _ \\/ __| __/ _ \\| '__|\n"
                        + "| |__| (_| | | | (_| | |__| (_) | | |  __/ (__| || (_) | |   \n"
                        + " \\____\\__,_|_|  \\__,_|\\____\\___/|_|_|\\___|\\___|\\__\\___/|_|   \n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        printBorder();
    }

    public void printInvalidArgumentWarning(String message, String[] usage) {
        printBorder();
        System.out.printf(FORMAT_INVALID_ARGUMENT, message);

        assert usage.length > 0;

        System.out.printf(FORMAT_INVALID_ARGUMENT_SYNTAX_USAGE, usage[0]);
        for (int i = 1; i < usage.length; i++) {
            System.out.printf(FORMAT_INVALID_ARGUMENT_EXAMPLE_USAGE, usage[i]);
        }
        printBorder();
    }

    public void printUnknownCommandWarning(String message) {
        printBorder();
        System.out.printf(FORMAT_UNKNOWN_COMMAND, message);
        printBorder();
    }

    public void printBlankCommandWarning() {
        printBorder();
        System.out.printf(FORMAT_BLANK_COMMAND);
        printBorder();
    }

    public void printExit() {
        printBorder();
        System.out.println("Bye! See you again");
        printBorder();
    }

    public String readInput() {
        return scanner.nextLine().trim();
    }

    public void printAdded(CardsList inventory) {
        System.out.println("I have added a new card!");
        printList(inventory);
    }

    public void printRemoved(CardsList inventory, int index) {
        System.out.println("I have removed card " + (index + 1));
        System.out.println("You have " + inventory.getSize() + " card(s) left");
        printList(inventory);
    }

    public void printEdited(CardsList inventory, int index) {
        System.out.println("I have edited card " + (index + 1) + "!");
        printList(inventory);
    }

    public void printNotEdited(CardsList inventory) {
        System.out.println("No changes found!");
    }

    public void printCompared(CardsList list, int index1, int index2) {
        printBorder();
        System.out.println("Comparing card " + (index1 + 1) + " and card " + (index2 + 1) + ":");
        System.out.println("Card " + (index1 + 1) + ": " + list.getCard(index1));
        System.out.println("Card " + (index2 + 1) + ": " + list.getCard(index2));
        printBorder();
    }

    public void printAcquired(CardsList inventory) {
        System.out.println("I have acquired the card and added it to your inventory!");
        printList(inventory);
    }

    public void printReordered(CardsList list) {
        System.out.println("I have reordered the cards!");
        printList(list);
    }

    public void printRemoveByNameSuccess(String targetName, CardsList inventory) {
        printBorder();
        System.out.println("Card \"" + targetName + "\" removed successfully");
        System.out.println("You have " + inventory.getSize() + " card(s) left");
        printBorder();
    }

    public void printCardNotFound(String targetName) {
        printBorder();
        System.out.println("No card named \"" + targetName + "\" was found.");
        printBorder();
    }

    public void printInvalidIndex() {
        printBorder();
        System.out.println("Invalid card index.");
        printBorder();
    }

    public void printList(CardsList list) {
        assert list != null : "List should not be null when printing";
        printBorder();
        int listSize = list.getSize();
        assert listSize >= 0 : "List size cannot be negative";

        if (listSize == 0) {
            System.out.println("Your card list is empty!");
        } else {
            System.out.println("Here is your card list!");
            for (int i = 0; i < listSize; i++) {
                Card card = list.getCard(i);
                assert card != null : "List should not contain null cards";
                System.out.println((i + 1) + ". " + card);
            }
        }
        printBorder();
    }

    public void printFound(ArrayList<Card> results) {
        assert results != null : "Results list passed to Ui should not be null";

        printBorder();
        if (results.isEmpty()) {
            System.out.println("No cards found matching your criteria!");
        } else {
            System.out.println("Here are the matching cards!");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
        printBorder();
    }

    public void printUndoSuccess(CardsList list) {
        printBorder();
        System.out.println("Undo Successful!");
        printList(list);
    }

    public void printDownloadSuccess(Path path) {
        printBorder();
        System.out.println("Saved current CardCollector data to: " + path);
        printBorder();
    }

    public void printUploadSuccess(Path sourcePath, Path activePath) {
        printBorder();
        System.out.println("Loaded CardCollector data from: " + sourcePath);
        System.out.println("Active storage file remains: " + activePath);
        System.out.println("Run \"undoupload\" to restore the previous session data.");
        printBorder();
    }

    public boolean confirmUpload(Path sourcePath, Path activePath) {
        printBorder();
        System.out.println("Warning: upload will replace your current inventory and wishlist.");
        System.out.println("Imported file: " + sourcePath);
        System.out.println("Your active save file will remain: " + activePath);
        System.out.println("Type YES to continue or anything else to cancel.");
        printBorder();
        return "YES".equals(readInput());
    }

    public void printUploadCancelled() {
        printBorder();
        System.out.println("Upload cancelled. Current data was not changed.");
        printBorder();
    }

    public void printUndoUploadSuccess(Path activePath) {
        printBorder();
        System.out.println("Restored the data from before the last upload.");
        System.out.println("Active storage file: " + activePath);
        printBorder();
    }

    public void printNoUploadUndoAvailable() {
        printBorder();
        System.out.println("No upload action is available to undo.");
        printBorder();
    }

    public void printStorageTransferError(String action, Path path, String errorMessage) {
        printBorder();
        System.out.println("Failed to " + action + " storage file: " + path);
        System.out.println(errorMessage);
        printBorder();
    }

    private void printHistoryRecordCount(int originalSize, int maxLimitSize) {
        if (originalSize > maxLimitSize) {
            System.out.printf(FORMAT_HISTORY_DISPLAY_N_RECORDS, maxLimitSize, originalSize);
        } else {
            System.out.printf(FORMAT_HISTORY_DISPLAY_ALL_RECORDS, originalSize);
        }
    }

    public void printHistory(CardsHistory history, CardHistoryType displayHistoryType,
            int maxDisplayCount, boolean isDescending) {
        printBorder();

        ArrayList<CardHistoryEntry> historyList = history.getSortedHistoryList(isDescending);

        ArrayList<CardHistoryEntry> filteredHistoryList;

        if (displayHistoryType == CardHistoryType.ENTIRE) {
            filteredHistoryList = historyList;
        } else {
            filteredHistoryList = historyList.stream()
                    .filter(entry -> entry.getCardHistoryType() == displayHistoryType)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        int recordsLimit = (maxDisplayCount == -1) ? HISTORY_DISPLAY_DEFAULT_LIMIT :
                Math.min(filteredHistoryList.size(), maxDisplayCount);

        if (filteredHistoryList.isEmpty()) {
            System.out.printf(FORMAT_HISTORY_NO_RECORD);
        } else {
            printHistoryRecordCount(filteredHistoryList.size(), recordsLimit);
        }

        for (int i = 0; i < recordsLimit && i < filteredHistoryList.size(); i++) {
            CardHistoryEntry entry = filteredHistoryList.get(i);
            CardHistoryType historyType = entry.getCardHistoryType();

            printHistoryAddedEntry(entry, historyType, displayHistoryType);
            printHistoryModifiedEntry(entry, historyType, displayHistoryType);
            printHistoryRemovedEntry(entry, historyType, displayHistoryType);
        }

        printBorder();
    }

    private void printHistoryAddedEntry(CardHistoryEntry entry,
                                        CardHistoryType historyType, CardHistoryType displayHistoryType) {
        if (historyType != CardHistoryType.ADDED) {
            return;
        }

        Card current = entry.getCurrent();
        Instant lastAdded = current.getLastAdded();
        assert lastAdded != null;
        int addedQuantity = entry.getChangedQuantity();
        String date = dateTimeFormatter.format(lastAdded);

        System.out.printf(FORMAT_HISTORY_ADDED_RECORD, date, addedQuantity, current);
    }

    private void printHistoryModifiedEntry(CardHistoryEntry entry,
                                           CardHistoryType historyType, CardHistoryType displayHistoryType) {
        if (historyType != CardHistoryType.MODIFIED) {
            return;
        }

        Card current = entry.getCurrent();
        LinkedHashMap<String, CardFieldChange> changedFields = entry.getChangedFields();
        Instant lastModified = current.getLastModified();
        assert lastModified != null;

        String date = dateTimeFormatter.format(lastModified);

        String fieldsString = changedFields.entrySet().stream()
                .map(field -> String.format(FORMAT_HISTORY_CHANGED_FIELD,
                        field.getKey(), field.getValue().previous(), field.getValue().current()))
                .collect(Collectors.joining(", "));

        System.out.printf(FORMAT_HISTORY_MODIFIED_RECORD, date, current, fieldsString);
    }

    private void printHistoryRemovedEntry(CardHistoryEntry entry,
                                          CardHistoryType historyType, CardHistoryType displayHistoryType) {
        if (historyType != CardHistoryType.REMOVED) {
            return;
        }

        Card mostRecent = entry.getMostRecent();

        Instant lastRemoved = mostRecent.getLastRemoved();
        assert lastRemoved != null;
        int removedQuantity = -entry.getChangedQuantity();
        String date = dateTimeFormatter.format(lastRemoved);

        System.out.printf(FORMAT_HISTORY_REMOVED_RECORD, date, removedQuantity, mostRecent);
    }

}


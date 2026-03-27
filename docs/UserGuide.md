# User Guide

## Introduction

CardCollector is a CLI application for tracking a trading card inventory and a separate wishlist. Each card stores a name, quantity, price, and timestamps used by the history commands.

## Quick Start

1. Ensure that Java 17 or above is installed.
2. Run `./gradlew run` from the project root.
3. Enter commands in the terminal.

## Features

### Adding a card: `add`

Adds a new card to the current list.

**Format:** `add /n NAME /q QUANTITY /p PRICE`

- `NAME` can contain spaces.
- `QUANTITY` must be an integer greater than or equal to 0.
- `PRICE` must be a valid number.

**Example:** `add /n Pikachu VMAX /q 2 /p 25.50`

### Editing a card: `edit`

Edits the name, quantity, or price of an existing card.

**Format:** `edit INDEX [/n NEW_NAME] [/q NEW_QUANTITY] [/p NEW_PRICE]`

**Examples:**
`edit 1 /n Dragonite VMAX`
`edit 2 /q 5 /p 12.99`

### Comparing cards: `compare`

Compares two cards from the same list.

**Format:** `compare INDEX1 INDEX2`

**Examples:**
`compare 1 3`
`wishlist compare 2 4`

### Listing cards: `list`

Displays all cards in the current list.

**Format:** `list`

### Finding cards: `find`

Searches the current list by name, price, quantity, or any combination of them.

**Format:** `find [/n NAME] [/p PRICE] [/q QUANTITY]`

**Examples:**
`find /n pika`
`find /p 5.99`
`find /n charizard /q 2`

### Removing a card by index: `removeindex`

Removes a card by its displayed position.

**Format:** `removeindex INDEX`

**Example:** `removeindex 2`

### Removing a card by name: `removename`

Removes the first exact case-insensitive name match.

**Format:** `removename NAME`

**Example:** `removename Pikachu`

### Viewing history: `history`

Displays cards ordered by added, modified, or removed timestamps.

**Format:** `history [added | modified | removed] [NUMBER | all]`

**Examples:**
`history added`
`history removed all`

### Using the wishlist: `wishlist`

Prefix any list-based command with `wishlist ` to run it on the wishlist instead of the main inventory.

**Examples:**
`wishlist add /n Charizard /q 1 /p 99.99`
`wishlist list`
`wishlist edit 1 /n Shiny Charizard`
`wishlist removeindex 2`

### Downloading a storage snapshot: `download`

Exports the current full app state, including inventory and wishlist, to a file path of your choice.

**Format:** `download /f FILE_PATH`

**Example:** `download /f backups/cardcollector.txt`

### Uploading a storage snapshot: `upload`

Imports a previously exported storage file into the current session.

**Format:** `upload /f FILE_PATH`

**Example:** `upload /f backups/cardcollector.txt`

`upload` warns before replacing the current in-memory inventory and wishlist. After a successful upload, you can use `undoupload` once to restore the previous session state. The app continues to auto-save to `data/cardcollector.txt`.

### Undoing the last upload: `undoupload`

Restores the inventory and wishlist from before the last successful upload.

**Format:** `undoupload`

### Exiting the program: `bye`

Exits the application.

**Format:** `bye`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Run `download /f some-file.txt`, move that file to the other computer, then run `upload /f some-file.txt` there.

## Command Summary

- `add /n NAME /q QTY /p PRICE`
- `edit INDEX [/n NAME] [/q QTY] [/p PRICE]`
- `list`
- `find [/n NAME] [/p PRICE] [/q QUANTITY]`
- `compare INDEX1 INDEX2`
- `removeindex INDEX`
- `removename NAME`
- `history [added | modified | removed] [NUMBER | all]`
- `download /f FILE_PATH`
- `upload /f FILE_PATH`
- `undoupload`
- `wishlist <list command>`
- `bye`

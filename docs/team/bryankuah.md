# bryankuah's Project Portfolio Page

## Project: CardCollector

CardCollector is a lightweight command-line application for trading card enthusiasts to manage their collections quickly and efficiently.

### Enhancements

**New Feature**: Added the `edit` command
* What it does: Allows users to modify any field (name, quantity, price, etc.) of an existing card in the inventory or wishlist using a clean flag-based syntax (e.g. `edit 3 -q 5 -p 120`).
* Justification: Users frequently need to update card details as their collection changes. A flexible edit command improves usability significantly over having separate commands for each field.
* Highlights: Supports partial updates (only specified fields are changed) and works on both inventory and wishlist with robust validation.
* Relevant PRs: [#57](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/57), [#59](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/59)

**New Feature**: Added the `compare` command
* What it does: Compares two cards side-by-side, clearly highlighting similarities and differences in key attributes.
* Justification: Helps users make informed decisions when deciding which cards to acquire or trade.
* Highlights: Supports comparison between any two cards from inventory or wishlist with clean formatted output.
* Relevant PRs: [#61](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/61)

**New Feature**: Added the `reorder` command
* What it does: Permanently reorders the cards in the inventory or wishlist based on multiple criteria (name, price, quantity, last added, last modified) with support for ascending/descending order.
* Justification: Provides users better organization of their collection according to their preference.
* Highlights: Supports multiple sorting criteria and persists the new order.
* Relevant PRs: [#70](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/70)

**New Feature**: Added the `find` command with advanced search
* What it does: Supports multi-field substring matching and range operators (e.g. price ranges).
* Justification: As the collection grows, users need powerful search capabilities to quickly locate specific cards.
* Highlights: Works on both inventory and wishlist lists.
* Relevant PRs: [#120](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/120)

**New Feature**: Implemented Wishlist support + `acquired` command
* What it does: Adds full support for a separate `wishlist` list with the `wishlist acquired` command to move cards from wishlist to main inventory.
* Justification: Many collectors maintain a wishlist of desired cards separate from owned cards.
* Highlights: Integrated smoothly with existing commands and the undo system.
* Relevant PRs: [#140](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/140), [#57](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/57)

**New Feature**: Added the `clear` command
* What it does: Clears all cards from the current list (inventory or wishlist). Reversible with the undo feature.
* Justification: Users sometimes need to reset their list (e.g. starting fresh or after bulk operations).
* Highlights: Works on both lists with proper confirmation and undo support.
* Relevant PRs: [#202](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/202)

### Code contributed

[RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=bryankuah)

### Documentation - User Guide

* Wrote comprehensive documentation for the `edit`, `compare`, `reorder`, `find`, `wishlist` (including `acquired`), and `clear` commands.
* Provided multiple usage examples and detailed notes for each feature.
* Updated the Command Summary table and Glossary.

### Documentation - Developer Guide

* Added implementation details and design considerations for all features listed above.
* Updated relevant class diagrams and sequence diagrams.
* Documented the wishlist routing mechanism.

### Contributions to Team Project

* Wrote a comprehensive suite of unit and integration tests for all implemented features.
* Reviewed pull requests from other team members.
* Helped coordinate feature integration and resolve merge conflicts.
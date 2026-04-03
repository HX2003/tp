# Raven Tang - Project Portfolio Page

## Overview

CardCollector is a desktop application for trading card collectors who prefer a fast CLI workflow over a GUI. It helps users manage an inventory and wishlist of cards, track metadata such as set and rarity, and review collection history directly from the terminal.

I focused mainly on persistence and inventory-management usability. My work covered local storage, import/export support, tag or folder-based organization, and list-related behavior and documentation.

### Summary of Contributions

#### Code contributed

- [RepoSense contribution dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=Simplificatedd&tabRepo=AY2526S2-CS2113-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&chartGroupIndex=18&chartIndex=4)

#### Enhancements implemented

- **Implemented local storage for application state**: Added persistent storage support so inventory data can survive across runs instead of existing only in memory. This required extending the application state and storage layer and adding test coverage for storage behavior. Relevant merge: [#62](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/93e1b15512365646573a4cd78ffbe9941ab7ec07)

- **Implemented upload/download support for storage snapshots**: Added commands for exporting the current state to a user-specified file and importing it back later. I also implemented `undoupload` support so users can safely roll back a mistaken import, which required handling full-state replacement cleanly. Relevant merge: [#64](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/47b474fe29b172dbea70a5f81ef8c62e029dfe3c)

- **Implemented tag/folder support and list filtering support**: Added tag-based organization for cards, with parser, command, model, and UI support. This allowed users to group cards under lightweight labels and then retrieve them more easily through related list and find workflows. Relevant merge: [#79](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/5f6c83b696be165702b939119b87e1a5ea5a0fc5)

- **Contributed to the list feature**: Added support for list-related command behavior in the application flow during the earlier project stage. Relevant merge: [#19](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/761d375c5ffd7d9ec2d4ac44d6bea6709af0e6ab)

#### Documentation contributions

- Wrote and updated the User Guide and Developer Guide sections for the commands and features I implemented, especially the storage import/export flow and tag or folder support.
- Contributed command documentation examples and usage details so the implemented features were reflected in user-facing documentation. Relevant merges: [#64](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/47b474fe29b172dbea70a5f81ef8c62e029dfe3c), [#79](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/5f6c83b696be165702b939119b87e1a5ea5a0fc5)

#### Contributions to project quality

- Added assertions for list-related logic to make failures surface earlier during development and strengthen defensive programming around command execution. Relevant commit: [Add Assertions for List](https://github.com/AY2526S2-CS2113-T11-3/tp/commit/5d42d2a6ed416825e5b628b24ac18f4d2ffae1c5)


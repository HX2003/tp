# WeiHeng2003's Project Portfolio Page

## Overview
CardCollector is a lightweight command-line application for trading card enthusiasts to manage their collections quickly and efficiently.

## Summary of contributions
### Code Contributed
- [RepoSense Report](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=weiheng2003&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=WeiHeng2003&tabRepo=AY2526S2-CS2113-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented
#### 1. Enhance `add` features with duplicate merging ([#52](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/52)) 
  -  Cards with identical name, price (and other metadata if available) are automatically merged rather than creating duplicate entries, increasing quantity instead.
  - Justification: Appending duplicate cards to the end of the list as separate entries makes it difficult for the user to track and clutters the list unnecessarily.  

#### 2. Added `undo` feature ([#77](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/77))
  - Allows user to reverse the most recent reversible command (`add`, `edit`, `removeindex`, `removename`, `clear`, `tag`)
  - Justification: Provides a safety net for accidental or incorrect commands, avoiding the need to manually retype corrective commands.
  - Difficulties faced: 
    - **Merge vs New entry:** Having to break down the different cases of the command to undo. E.g. `add` has 2 cases, adding a new card and merging duplicates, requiring `wasMarged` boolean flag to distinguish. A normal undo to `add` will delete the new entry, while a undo to merged `add` requires a change in quantity.
    - **Command history stack lifeline:**`CommandContext` was originally constructed inside the loop, which meant that the `commandHistory` stack was reset on every command. To fix this, the stack was moved into `CardCollector` and passed in each iteration.
    - **No-op edit handling:** If an `edit` command results in no actual changes made, it should not be pushed to the history stack. This was handled by setting `isReversible` dynamically inside `execute()` based on whether `editCard()` returned `true`.  

#### 3. Implemented card removal by index and by name ([#42](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/42))
  - Introduced `RemoveCardByIndex` and `RemoveCardByName` as two separate removal strategies.
  - Justification: Removal by index is faster when viewing in the list, while removal by name is more intuitive when the user knows the card name without checking the list.
  - Difficulties faced:
    - **Duplicate Names:** Cards of the same name can exist but of different price and other metadata. Hence, when calling `removename` a prompt appears for the user to select the exact card they want to delete.
  - Both commands are reversible via `undo`
  

### Contributions to the UG
- Documented the `add` command including merge behavior, optional flags and example inputs.
- Documented the `undo` command including support reversible commands, edge cases (no-op edit) and manual test cases.
- Reviewed and ensured accuracy of undo-related documentation across all features that support undo. 

### Contributions to the DG
- Formatted the document with table of contents and hyperlinks between sections ([#87](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/87)).
- Converted the PUML codes into rendered SVG diagrams and attached them ([#90](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/90)).
- Documented `add` and `undo` features and design, inclusive of manual test cases, Class and Sequence diagrams
  ([#102](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/102), [#147](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/147), [#148](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/148)).

### Contribution to Team-Based Tasks
- Setting up of GitHub team organization and repositary
- Releases of product ([v1.0](https://github.com/AY2526S2-CS2113-T11-3/tp/releases/tag/v1.0), [v2.0](https://github.com/AY2526S2-CS2113-T11-3/tp/releases/tag/v2.0))
- Documenting the target user profile in the Developer Guide Appendix 

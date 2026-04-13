# Calvin-GH's Project Portfolio Page

## Overview
CardCollector is a lightweight command-line application designed for trading card enthusiasts to efficiently manage and track their collections. It supports inventory management, search, analytics, duplicate detection, and history tracking, enabling users to interact with their collections quickly through a CLI interface.

## Summary of contributions

### Code Contributed
- [RepoSense Report](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=calvin-gh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=)

---

### Enhancements implemented

- **Implemented `remove` feature with multiple workflows (`RemoveCardByIndex`, `RemoveCardByName`)**
  - Extended command parsing logic to support different removal modes based on user input.
  - Handled ambiguous cases where multiple cards share the same name by prompting user selection.
  - Added validation for edge cases such as invalid indices and empty lists.
  - **Justification:** Improves usability by allowing users to remove cards based on either list position or known card name.

- **Added `notes` feature to allow users to attach additional information to cards**
  - Extended the card data model to support storing notes as an additional attribute.
  - Updated parsing logic to handle new input formats for notes.
  - Ensured compatibility with existing card comparison and storage logic.
  - **Justification:** Enables users to store contextual details such as condition, remarks, or transaction notes.

- **Implemented `duplicate` detection feature**
  - Designed comparison logic to identify cards with identical attributes.
  - Ensured duplicate detection aligns with existing card equality logic.
  - **Justification:** Helps users identify repeated entries efficiently.

- **Implemented and enhanced `analytics` feature for summarising card data**
  - Added support for advanced analytics functionality.
  - Fixed issues related to numeric precision and float handling.
  - Expanded test coverage to ensure correctness.
  - **Justification:** Provides users with meaningful insights into their collection.

- **Improved `history` feature to track user actions**
  - Fixed edge cases to ensure consistency when recording add/remove operations.
  - Improved reliability of historical records across workflows.
  - **Justification:** Enables users to review past actions and improves traceability.

- **Refactored command structure and improved command integration**
  - Contributed to restructuring command handling for better modularity and scalability.
  - Improved consistency across parsing and execution.
  - Supported integration of commands such as `find`, `list`, and `exit`.
  - **Justification:** Improves maintainability and ease of future extensions.

- Overall, my contributions focused on improving usability, data handling, and system extensibility, forming core features used by end users.

---

### Contributions to the UG
- Documented and updated multiple command features, including:
  - `notes` feature (usage, examples, expected behaviour)
  - `duplicate` detection feature
  - `remove` workflows (`removeindex`, `removename`)
- Added usage examples and clarified expected outputs and edge case behaviour.
- Improved clarity, consistency, and usability of command documentation.
- Ensured documentation remains aligned with implementation after updates and refactoring.

---

### Contributions to the DG
- Documented implementation details for key features:
  - Notes feature (data model extension and command flow)
  - Duplicate detection logic and comparison behaviour
  - Removal workflows and command parsing design
- Explained design decisions and how features integrate with the system architecture.
- Updated documentation to reflect command structure refactoring.
- Improved clarity for future developers to understand and extend the system.

---

### Contributions to Team-Based Tasks
- Assisted in integrating features into the main codebase.
- Helped resolve merge conflicts and maintain consistency across contributions.
- Supported testing and debugging during feature integration.
- Contributed to improving overall code quality and structure.

---

### Review / mentoring contributions

- Contributed through multiple pull requests covering feature implementation, bug fixes, and documentation:

  - [#18](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/18) Add remove feature by index and name
  - [#34](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/34) Fix edge cases in history tracking
  - [#47](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/47) Complete command architecture refactor and add find/list/exit commands
  - [#107](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/107) Add card notes, note search, duplicates command, wishlist-specific UI and test cases
  - [#109](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/109) Update User Guide and Developer Guide
  - [#115](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/115) Fix Developer Guide documentation for notes feature
  - [#117](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/117) Add advanced analytics and update documentation
  - [#118](https://github.com/AY2526S2-CS2113-T11-3/tp/pull/118) Fix analytics behaviour, improve float handling, and expand test coverage

- Ensured correctness and consistency across features by addressing edge cases, improving test coverage, and refining system behaviour.

---

### Contributions beyond the project team

- Reported multiple bugs during PE-D:
  - Identified and documented 10+ issues across functionality, usability, and documentation.
  - Included clear reproduction steps, expected vs actual behaviour, and severity classification.
  - Covered issues such as command parsing inconsistencies, incorrect application behaviour, and usability flaws.
 
- Demonstrated systematic bug reporting:
  - Categorised bugs by severity (Low, Medium, High) and type (Functionality, Documentation, Feature flaws).
  - Ensured reports were structured and actionable for developers to reproduce and fix efficiently.
# CardCollector

**CardCollector** is a lightweight, command-line application built for trading card enthusiasts to manage their collections quickly and efficiently.

If you prefer typing over clicking, CardCollector allows you to track the cards you own, search your inventory using complex attributes, and monitor your collection's history—all from the comfort of your terminal.

---

## 🚀 Features

* **Inventory Management**
  Add, edit, and remove cards by index or name.

* **Granular Tracking**
  Track card names, prices, quantities, and tags.

* **Advanced Search & Filtering**
  Filter your inventory using combinations of attributes such as:

    * Name
    * Price
    * Quantity

* **Wishlist Support**
  Maintain a separate wishlist for cards you plan to acquire.

* **History Logs**
  View timestamps of when cards were added, modified, or removed.

* **Data Persistence**
  Your data is automatically saved and loaded between sessions.

---

## 🛠️ Getting Started

### Prerequisites

* Java 17 (or later)
* Gradle (if running manually)

### Running the Application

```bash
./gradlew run
```

### Running Tests

```bash
./gradlew test
```

---

## 💡 Example Usage

```bash
add n/Pikachu p/10 q/2
list
find n/Pikachu
delete 1
```

---

## 📂 Project Structure

```plaintext
src/
 ├── main/java/seedu/cardcollector
 │   ├── command        # Command logic
 │   ├── parsing        # Input parsing
 │   ├── card           # Card models
 │   ├── storage        # File handling
 │   └── ui             # User interface
 └── test/java/seedu/cardcollector
     ├── command        # Command tests
     ├── parsing        # Parser tests
     └── card           # Model tests
```

---

## 🧪 Testing

CardCollector includes unit tests for:

* Commands
* Parsing logic
* Core data structures

To run all tests:

```bash
./gradlew test
```

---

## ⚠️ Known Limitations

* CLI-only interface (no GUI)
* Limited support for bulk operations
* Input must follow strict command format

---

## 🔮 Future Improvements

* GUI version
* Import/export enhancements
* More analytics features
* Better error handling and suggestions

---

## 📖 Documentation

* [User Guide](docs/UserGuide.md)
* [Developer Guide](docs/DeveloperGuide.md)
* [About Us](docs/AboutUs.md)

---

## 👥 Team

See the [About Us](docs/AboutUs.md) page for details about contributors.

---

## 📄 License

This project is developed for educational purposes.

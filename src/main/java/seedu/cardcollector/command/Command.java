package seedu.cardcollector.command;

import seedu.cardcollector.CardsList;
import seedu.cardcollector.Ui;

public abstract class Command {
    public abstract CommandResult execute(Ui ui, CardsList inventory);
}

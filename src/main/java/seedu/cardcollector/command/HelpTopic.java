package seedu.cardcollector.command;

import java.util.List;

public record HelpTopic(String name, List<String> aliases, String summary, String[] usage) {
    public String getDisplayName() {
        if (aliases.isEmpty()) {
            return name;
        }
        return name + " (" + String.join(", ", aliases) + ")";
    }
}

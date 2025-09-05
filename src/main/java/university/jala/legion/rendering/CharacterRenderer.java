package university.jala.legion.rendering;

import university.jala.legion.model.interfaces.IBattlefield;
import university.jala.legion.model.interfaces.ICharacter;

/**
 * Renders the battlefield using character symbols for units with proper alignment.
 */
public class CharacterRenderer implements BattlefieldRenderer {
    @Override
    public String render(IBattlefield battlefield) {
        int size = battlefield.getSize();
        int maxLength = 1; // Start with 1 for the '*' empty symbol

        // First, find the maximum length of any unit's symbol.
        // While characters are currently single, this makes it robust for future changes.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ICharacter unit = battlefield.getUnitAt(i, j);
                if (unit != null) {
                    int length = String.valueOf(unit.getSymbol()).length();
                    if (length > maxLength) {
                        maxLength = length;
                    }
                }
            }
        }

        // Create a format string for left-aligned padding
        String format = "%-" + maxLength + "s ";
        StringBuilder sb = new StringBuilder();

        // Build the formatted grid string
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ICharacter unit = battlefield.getUnitAt(i, j);
                String symbol = (unit != null) ? String.valueOf(unit.getSymbol()) : "*";
                sb.append(String.format(format, symbol));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

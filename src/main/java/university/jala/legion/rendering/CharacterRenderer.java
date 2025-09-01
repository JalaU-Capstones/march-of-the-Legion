package university.jala.legion.rendering;

import university.jala.legion.model.interfaces.IBattlefield;
import university.jala.legion.model.interfaces.ICharacter;

/**
 * Renders the battlefield using character symbols for units.
 */
public class CharacterRenderer implements BattlefieldRenderer {
    @Override
    public String render(IBattlefield battlefield) {
        StringBuilder sb = new StringBuilder();
        int size = battlefield.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ICharacter unit = battlefield.getUnitAt(i, j);
                sb.append(unit != null ? unit.getSymbol() : "*");
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

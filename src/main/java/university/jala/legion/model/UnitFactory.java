package university.jala.legion.model;

import university.jala.legion.model.enums.UnitType;
import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.model.units.*;

/**
 * A factory for creating character units based on a given type.
 * This class centralizes the instantiation of unit objects, making it easy to manage
 * and extend the available unit types.
 */
public class UnitFactory {
    /**
     * Creates a new character unit of the specified type.
     *
     * @param type The enum type of the unit to create.
     * @return An instance of the requested {@link ICharacter}.
     * @throws IllegalArgumentException if the specified type is unknown.
     */
    public static ICharacter createUnit(UnitType type) {
        switch (type) {
            case COMMANDER:
                return new Commander();
            case MEDIC:
                return new Medic();
            case TANK:
                return new Tank();
            case SNIPER:
                return new Sniper();
            case INFANTRY:
                return new Infantry();
            default:
                // This case is unlikely to be reached with an enum but is good practice.
                throw new IllegalArgumentException("Unsupported unit type: " + type);
        }
    }
}

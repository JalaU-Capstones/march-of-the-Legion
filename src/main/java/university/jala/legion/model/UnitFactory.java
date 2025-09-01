package university.jala.legion.model;

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
     * @param type The type of unit to create (e.g., "Commander", "Medic").
     * @return An instance of the requested {@link ICharacter}.
     * @throws IllegalArgumentException if the specified type is unknown.
     */
    public static ICharacter createUnit(String type) {
        switch (type.toLowerCase()) {
            case "commander":
                return new Commander();
            case "medic":
                return new Medic();
            case "tank":
                return new Tank();
            case "sniper":
                return new Sniper();
            case "infantry":
                return new Infantry();
            default:
                throw new IllegalArgumentException("Unknown unit type: " + type);
        }
    }
}

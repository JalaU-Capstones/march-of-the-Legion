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
     * Creates a new character unit of the specified type with a default index.
     * @param type The enum type of the unit to create.
     * @return An instance of the requested {@link ICharacter}.
     */
    public static ICharacter createUnit(UnitType type) {
        return createUnit(type, 0);
    }

    /**
     * Creates a new character unit of the specified type with a cyclic index.
     *
     * @param type  The enum type of the unit to create.
     * @param index The creation index of the unit, used for cyclic symbol assignment.
     * @return An instance of the requested {@link ICharacter}.
     * @throws IllegalArgumentException if the specified type is unknown.
     */
    public static ICharacter createUnit(UnitType type, int index) {
        char symbol;
        int numericValue;

        switch (type) {
            case COMMANDER:
                symbol = (char) ('a' + (index % 10));
                numericValue = 1 + (index % 10);
                return new Commander(symbol, numericValue);
            case MEDIC:
                symbol = (char) ('k' + (index % 10));
                numericValue = 11 + (index % 10);
                return new Medic(symbol, numericValue);
            case TANK:
                int tankMod = index % 16;
                if (tankMod < 6) {
                    symbol = (char) ('u' + tankMod);
                } else {
                    symbol = (char) ('A' + tankMod - 6);
                }
                numericValue = 21 + (index % 10);
                return new Tank(symbol, numericValue);
            case SNIPER:
                symbol = (char) ('K' + (index % 4));
                numericValue = 31 + (index % 10);
                return new Sniper(symbol, numericValue);
            case INFANTRY:
                symbol = (char) ('O' + (index % 10));
                numericValue = 41 + (index % 10);
                return new Infantry(symbol, numericValue);
            default:
                throw new IllegalArgumentException("Unsupported unit type: " + type);
        }
    }
}

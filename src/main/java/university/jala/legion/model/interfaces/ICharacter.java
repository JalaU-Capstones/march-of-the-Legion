package university.jala.legion.model.interfaces;

/**
 * Represents a character in the simulation, combining multiple behaviors.
 * This interface serves as the primary contract for all character units, ensuring they
 * are positionable, displayable, and have a specific type and rank.
 */
public interface ICharacter extends Positionable, Displayable {
    /**
     * Gets the rank of the character, which determines its hierarchy.
     * @return the rank of the character.
     */
    int getRank();

    /**
     * Gets the type or role of the character (e.g., "Commander", "Medic").
     * @return the type of the character.
     */
    String getType();
}

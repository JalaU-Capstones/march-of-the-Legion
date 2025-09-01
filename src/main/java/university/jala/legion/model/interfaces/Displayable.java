package university.jala.legion.model.interfaces;

/**
 * Represents an object that has a visual or textual representation.
 * This interface is used for components that need to display objects
 * without being coupled to their specific implementation details.
 */
public interface Displayable {
    /**
     * Gets the character symbol representing the object.
     * @return the display symbol.
     */
    char getSymbol();

    /**
     * Gets the numeric range associated with the object.
     * @return the numeric range identifier.
     */
    int getNumericRange();
}

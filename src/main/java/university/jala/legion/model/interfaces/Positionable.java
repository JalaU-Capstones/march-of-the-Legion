package university.jala.legion.model.interfaces;

import university.jala.legion.model.Position;

/**
 * Represents an object that can be positioned on a 2D grid.
 * This interface allows components to interact with objects based on their position
 * without needing to know about other details of the object.
 */
public interface Positionable {
    /**
     * Gets the current position of the object.
     * @return the {@link Position} of the object.
     */
    Position getPosition();

    /**
     * Sets the position of the object.
     * @param position the new {@link Position} for the object.
     */
    void setPosition(Position position);
}

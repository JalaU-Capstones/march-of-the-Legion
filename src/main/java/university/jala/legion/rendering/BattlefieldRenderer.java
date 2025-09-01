package university.jala.legion.rendering;

import university.jala.legion.model.interfaces.IBattlefield;

/**
 * Defines the contract for rendering a battlefield to a string representation.
 * This interface allows for different rendering strategies (e.g., character vs. numeric)
 * to be implemented and used interchangeably.
 */
public interface BattlefieldRenderer {
    /**
     * Renders the state of the battlefield into a formatted string.
     *
     * @param battlefield The battlefield to render.
     * @return A string representation of the battlefield.
     */
    String render(IBattlefield battlefield);
}

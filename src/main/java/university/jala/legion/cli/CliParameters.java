package university.jala.legion.cli;

/**
 * Defines the contract for accessing command-line parameters for the simulation.
 * This interface allows for dependency inversion, enabling high-level modules to depend on
 * this abstraction rather than a concrete implementation.
 */
public interface CliParameters {
    /**
     * Gets the selected sorting algorithm code.
     * @return A character representing the algorithm ('c', 'r', 'i', 'q').
     */
    String getAlgorithm();

    /**
     * Gets the troop sorting orientation.
     * @return A character representing the orientation ('n', 's', 'e', 'w').
     */
    String getOrientation();

    /**
     * Gets the list type for troop representation.
     * @return A character representing the type ('c' for character, 'n' for numeric).
     */
    String getType();

    /**
     * Gets the size of the battlefield (N for an N x N grid).
     * @return The size of the battlefield.
     */
    int getBattlefieldSize();

    /**
     * Gets the distribution of units across different roles.
     * @return An array of 5 integers representing the count for each unit type.
     */
    int[] getUnitDistribution();
}

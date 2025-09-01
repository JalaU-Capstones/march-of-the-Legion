package university.jala.legion.simulation.reporter;

import university.jala.legion.cli.CliParameters;
import university.jala.legion.model.interfaces.IBattlefield;

import java.util.stream.IntStream;

/**
 * Handles the display of simulation information to the console.
 * This class separates reporting concerns from the core simulation logic.
 */
public class SimulationReporter {

    private final CliParameters params;

    /**
     * Constructs a new SimulationReporter.
     *
     * @param params The command-line parameters to be displayed.
     */
    public SimulationReporter(CliParameters params) {
        this.params = params;
    }

    /**
     * Displays the initial simulation configuration parameters.
     */
    public void displayParameters() {
        System.out.println("Algorithm: [" + params.getAlgorithm() + "]");
        System.out.println("Type: [" + (params.getType().equals("c") ? "Character" : "Numeric") + "]");
        System.out.println("Orientation: [" + getOrientationName(params.getOrientation()) + "]");
        System.out.println("Troops: [" + IntStream.of(params.getUnitDistribution()).sum() + "]");
        System.out.println("Battlefield: [" + params.getBattlefieldSize() + " x " + params.getBattlefieldSize() + "]");
    }

    /**
     * Displays the execution time of the sorting algorithm.
     *
     * @param executionTime The time in milliseconds.
     */
    public void displayExecutionTime(long executionTime) {
        System.out.println("\nExecution time: " + executionTime + "ms");
    }

    private String getOrientationName(String orientationCode) {
        return switch (orientationCode.toLowerCase()) {
            case "n" -> "North";
            case "s" -> "South";
            case "e" -> "East";
            case "w" -> "West";
            default -> "Unknown";
        };
    }
}

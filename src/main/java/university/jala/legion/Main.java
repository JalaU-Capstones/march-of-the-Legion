package university.jala.legion;

import university.jala.legion.cli.Parameters;
import university.jala.legion.simulation.Simulation;

/**
 * The main entry point for the March of the Legion simulation.
 * This class is responsible for initializing and running the simulation based on command-line arguments.
 */
public class Main {
    /**
     * The main method that starts the application.
     *
     * @param args The command-line arguments that configure the simulation.
     */
    public static void main(String[] args) {
        try {
            // 1. Parse and validate command-line parameters.
            Parameters params = new Parameters(args);

            // 2. Create and run the simulation.
            Simulation simulation = new Simulation(params);
            simulation.run();

        } catch (IllegalArgumentException e) {
            // Catch any configuration or runtime errors and display a user-friendly message.
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            // Catch any unexpected errors.
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}

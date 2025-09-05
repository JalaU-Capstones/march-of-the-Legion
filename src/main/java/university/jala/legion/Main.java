package university.jala.legion;

import university.jala.legion.cli.Parameters;
import university.jala.legion.exception.SimulationException;
import university.jala.legion.model.enums.AlgorithmType;
import university.jala.legion.simulation.Simulation;
import university.jala.legion.util.AnsiColor;
import university.jala.legion.util.StartupPresenter;

import java.util.Arrays;

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
        new StartupPresenter().showBannerAndPlaySound();

        Parameters params = new Parameters(args);

        if (!params.isValid()) {
            // If validation fails, print the context and all collected errors.
            printErrorContext(params);
            System.exit(1);
        } else {
            // If validation succeeds, run the simulation.
            try {
                Simulation simulation = new Simulation(params);
                simulation.run();
            } catch (SimulationException e) {
                // Catch known, user-facing simulation errors and display them gracefully.
                System.out.println(AnsiColor.red("\nError: " + e.getMessage()));
                if (e.getHint() != null) {
                    System.out.println(AnsiColor.red("Hint: " + e.getHint()));
                }
                System.exit(1);
            } catch (Exception e) {
                // Catch any other unexpected errors and prevent stack traces from being shown.
                System.err.println(AnsiColor.red("\nAn unexpected system error occurred: " + e.getMessage()));
                System.exit(1);
            }
        }
    }

    /**
     * Prints the parameter context and the list of validation errors.
     * This method ensures that the user sees their input and receives clear feedback
     * on why the validation failed, as per the requirements.
     *
     * @param params The validated parameters object containing the raw inputs and errors.
     */
    private static void printErrorContext(Parameters params) {
        String algorithmName = AlgorithmType.getFullNameByCode(params.getRawValue("a"));
        System.out.println("Algorithm: [" + algorithmName + "]");
        System.out.println("Type: [" + params.getRawValue("t") + "]");
        System.out.println("Orientation: [" + params.getRawValue("o") + "]");

        String troopsStr = "[not provided]";
        if (!params.getRawValue("u").equals("[not provided]")) {
            try {
                int troopCount = Arrays.stream(params.getUnitDistribution()).sum();
                troopsStr = String.valueOf(troopCount);
            } catch (Exception e) {
                troopsStr = "invalid"; // In case parsing fails
            }
        }
        System.out.println("Troops: [" + troopsStr + "]");

        System.out.println("Battlefield: [" + params.getRawValue("f") + " x " + params.getRawValue("f") + "]");

        System.out.println(AnsiColor.red("\nErrors:"));
        for (String error : params.getValidationErrors()) {
            System.out.println(AnsiColor.red("- " + error));
        }
    }
}

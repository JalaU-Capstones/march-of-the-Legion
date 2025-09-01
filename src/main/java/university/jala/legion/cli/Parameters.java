package university.jala.legion.cli;

import university.jala.legion.cli.validation.ParameterValidator;
import university.jala.legion.cli.validation.ParameterValidatorFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles and validates the command-line parameters for the March of the Legion simulator.
 * This class is responsible for parsing, validating, and providing access to the simulation parameters.
 */
public class Parameters implements CliParameters {
    private final Map<String, String> parameters;

    private static final String DEFAULT_BATTLEFIELD_SIZE = "6";
    private static final String DEFAULT_ORIENTATION = "n";
    private static final String DEFAULT_TYPE = "c";

    /**
     * Constructs a new Parameters instance from the given command-line arguments.
     * It parses the arguments, sets default values for missing optional parameters,
     * and validates all parameters.
     *
     * @param args The command-line arguments in key=value format.
     */
    public Parameters(String[] args) {
        parameters = new HashMap<>();
        parseArgs(args);
        validateParameters();
    }

    private void parseArgs(String[] args) {
        Arrays.stream(args)
                .filter(arg -> arg.contains("="))
                .forEach(arg -> {
                    String[] parts = arg.split("=", 2);
                    parameters.put(parts[0].toLowerCase(), parts[1].toLowerCase());
                });

        parameters.putIfAbsent("f", DEFAULT_BATTLEFIELD_SIZE);
        parameters.putIfAbsent("o", DEFAULT_ORIENTATION);
        parameters.putIfAbsent("t", DEFAULT_TYPE);
    }

    private void validateParameters() {
        List<ParameterValidator> validators = ParameterValidatorFactory.createValidators();
        for (ParameterValidator validator : validators) {
            validator.validate(parameters);
        }
    }

    @Override
    public String getAlgorithm() {
        return parameters.get("a");
    }

    @Override
    public String getOrientation() {
        return parameters.get("o");
    }

    @Override
    public String getType() {
        return parameters.get("t");
    }

    @Override
    public int getBattlefieldSize() {
        return Integer.parseInt(parameters.get("f"));
    }

    @Override
    public int[] getUnitDistribution() {
        return Arrays.stream(parameters.get("u").split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}

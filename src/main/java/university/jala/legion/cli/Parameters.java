package university.jala.legion.cli;

import university.jala.legion.cli.validation.ParameterValidator;
import university.jala.legion.cli.validation.ParameterValidatorFactory;

import java.util.*;

/**
 * Handles and validates the command-line parameters for the March of the Legion simulator.
 * This class is responsible for parsing, validating, and providing access to the simulation parameters.
 */
public class Parameters implements CliParameters {
    private final Map<String, String> parameters;
    private final List<String> validationErrors = new ArrayList<>();

    private static final String DEFAULT_BATTLEFIELD_SIZE = "6";
    private static final String DEFAULT_ORIENTATION = "n";
    private static final String DEFAULT_ALGORITHM = "i"; // Default to Insertion Sort

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

        // Handle 'r' as an alias for 'u'
        if (parameters.containsKey("r")) {
            parameters.put("u", parameters.get("r"));
        }

        // Apply default values if parameters are not provided
        parameters.putIfAbsent("f", DEFAULT_BATTLEFIELD_SIZE);
        parameters.putIfAbsent("o", DEFAULT_ORIENTATION);
        parameters.putIfAbsent("a", DEFAULT_ALGORITHM);
        // 't' and 'u'/'r' are mandatory and do not have defaults
    }

    private void validateParameters() {
        List<ParameterValidator> validators = ParameterValidatorFactory.createValidators();
        for (ParameterValidator validator : validators) {
            validator.validate(parameters).forEach(validationErrors::add);
        }
    }

    /**
     * Checks if all parameters are valid.
     * @return true if no validation errors were found, false otherwise.
     */
    public boolean isValid() {
        return validationErrors.isEmpty();
    }

    /**
     * Gets the list of validation error messages.
     * @return A list of error messages.
     */
    public List<String> getValidationErrors() {
        return Collections.unmodifiableList(validationErrors);
    }

    @Override
    public String getRawValue(String key) {
        if (key.equalsIgnoreCase("r")) {
            key = "u";
        }
        return parameters.getOrDefault(key, "[not provided]");
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
        try {
            return Integer.parseInt(parameters.get("f"));
        } catch (NumberFormatException e) {
            return -1; // Invalid format will be caught by validator
        }
    }

    @Override
    public int[] getUnitDistribution() {
        String value = parameters.get("u");
        if (value == null) {
            return new int[0]; // Will be caught by validator
        }
        try {
            return Arrays.stream(value.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            return new int[0]; // Will be caught by validator
        }
    }
}

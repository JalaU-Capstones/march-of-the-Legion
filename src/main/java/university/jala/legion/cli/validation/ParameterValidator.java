package university.jala.legion.cli.validation;

import java.util.Map;

/**
 * Defines the contract for validating a specific command-line parameter.
 * This interface supports the Open/Closed Principle by allowing new validation
 * strategies to be added without modifying existing validation logic.
 */
public interface ParameterValidator {
    /**
     * Validates the given parameter.
     * @param parameters The map of all command-line parameters.
     * @throws IllegalArgumentException if the parameter is invalid.
     */
    void validate(Map<String, String> parameters);
}

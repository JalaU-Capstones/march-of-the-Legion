package university.jala.legion.cli.validation;

import java.util.List;
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
     * @return A list of error messages. The list is empty if validation is successful.
     */
    List<String> validate(Map<String, String> parameters);
}

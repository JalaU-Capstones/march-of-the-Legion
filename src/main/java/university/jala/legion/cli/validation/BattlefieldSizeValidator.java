package university.jala.legion.cli.validation;

import java.util.Map;

/**
 * Validates the battlefield size parameter ('f').
 */
public class BattlefieldSizeValidator implements ParameterValidator {
    @Override
    public void validate(Map<String, String> parameters) {
        try {
            int size = Integer.parseInt(parameters.get("f"));
            if (size < 5 || size > 1000) {
                throw new IllegalArgumentException("Battlefield size must be between 5 and 1000");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid battlefield size format");
        }
    }
}

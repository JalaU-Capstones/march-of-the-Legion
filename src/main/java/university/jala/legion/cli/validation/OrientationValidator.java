package university.jala.legion.cli.validation;

import java.util.Map;

/**
 * Validates the orientation parameter ('o').
 */
public class OrientationValidator implements ParameterValidator {
    @Override
    public void validate(Map<String, String> parameters) {
        String orientation = parameters.get("o");
        if (!orientation.matches("[nsew]")) {
            throw new IllegalArgumentException("Invalid orientation code: " + orientation);
        }
    }
}

package university.jala.legion.cli.validation;

import java.util.Map;

/**
 * Validates the list type parameter ('t').
 */
public class TypeValidator implements ParameterValidator {
    @Override
    public void validate(Map<String, String> parameters) {
        String type = parameters.get("t");
        if (!type.matches("[cn]")) {
            throw new IllegalArgumentException("Invalid display type code: " + type);
        }
    }
}

package university.jala.legion.cli.validation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Validates the list type parameter ('t').
 */
public class TypeValidator implements ParameterValidator {
    private static final List<String> VALID_TYPES = List.of("c", "n", "C", "N");

    @Override
    public List<String> validate(Map<String, String> parameters) {
        if (!parameters.containsKey("t")) {
            return List.of("Parameter 't' (Type) is mandatory and was not provided.");
        }

        String type = parameters.get("t");
        if (!VALID_TYPES.contains(type)) {
            String errorMessage = String.format(
                "Value of Type is invalid. Accepted values are: c (Character), n (Numeric). You entered: '%s'.",
                type
            );
            return List.of(errorMessage);
        }

        return Collections.emptyList();
    }
}

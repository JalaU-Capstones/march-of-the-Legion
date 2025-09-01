package university.jala.legion.cli.validation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Validates the orientation parameter ('o').
 */
public class OrientationValidator implements ParameterValidator {
    private static final List<String> VALID_ORIENTATIONS = List.of("n", "s", "e", "w");

    @Override
    public List<String> validate(Map<String, String> parameters) {
        String orientation = parameters.get("o");

        // The 'o' parameter has a default, so it should always be present.
        if (!VALID_ORIENTATIONS.contains(orientation)) {
            String errorMessage = String.format(
                "Value of Orientation is invalid. Accepted values are: n (North), s (South), e (East), w (West). You entered: '%s'.",
                orientation
            );
            return List.of(errorMessage);
        }

        return Collections.emptyList();
    }
}

package university.jala.legion.cli.validation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Validates the battlefield size parameter ('f').
 */
public class BattlefieldSizeValidator implements ParameterValidator {
    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 1000;

    @Override
    public List<String> validate(Map<String, String> parameters) {
        String sizeStr = parameters.get("f");

        // The 'f' parameter has a default, so it should always be present.
        try {
            int size = Integer.parseInt(sizeStr);
            if (size < MIN_SIZE || size > MAX_SIZE) {
                String errorMessage = String.format(
                    "Value of Battlefield Size is out of range. It must be between %d and %d. You entered: '%d'.",
                    MIN_SIZE, MAX_SIZE, size
                );
                return List.of(errorMessage);
            }
        } catch (NumberFormatException e) {
            String errorMessage = String.format(
                "Value of Battlefield Size is not a valid integer. You entered: '%s'.",
                sizeStr
            );
            return List.of(errorMessage);
        }

        return Collections.emptyList();
    }
}

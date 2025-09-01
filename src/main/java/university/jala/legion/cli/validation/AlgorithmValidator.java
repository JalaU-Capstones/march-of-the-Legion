package university.jala.legion.cli.validation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Validates the sorting algorithm parameter ('a').
 */
public class AlgorithmValidator implements ParameterValidator {
    // Using a Set is more efficient for lookups and handles null gracefully.
    private static final Set<String> VALID_ALGORITHMS = Set.of("c", "r", "i", "q");

    @Override
    public List<String> validate(Map<String, String> parameters) {
        String algorithm = parameters.get("a");

        if (algorithm == null || !VALID_ALGORITHMS.contains(algorithm)) {
            String errorMessage = String.format(
                "Value of Algorithm is invalid. Accepted values are: c (Counting), r (Radix), i (Insertion), q (Quick). You entered: '%s'.",
                algorithm
            );
            return List.of(errorMessage);
        }

        return Collections.emptyList();
    }
}

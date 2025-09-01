package university.jala.legion.cli.validation;

import java.util.Map;

/**
 * Validates the sorting algorithm parameter ('a').
 */
public class AlgorithmValidator implements ParameterValidator {
    @Override
    public void validate(Map<String, String> parameters) {
        if (!parameters.containsKey("a")) {
            throw new IllegalArgumentException("Sorting algorithm parameter 'a' is required");
        }
        String algorithm = parameters.get("a");
        if (!algorithm.matches("[criq]")) {
            throw new IllegalArgumentException("Invalid sorting algorithm code. Valid codes are: c (Counting), r (Radix), i (Insertion), q (Quick)");
        }
    }
}

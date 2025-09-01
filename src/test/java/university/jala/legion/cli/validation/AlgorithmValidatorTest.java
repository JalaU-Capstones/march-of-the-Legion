package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AlgorithmValidator} class.
 */
class AlgorithmValidatorTest {

    private AlgorithmValidator validator;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        validator = new AlgorithmValidator();
        parameters = new HashMap<>();
    }

    @Test
    void testMissingAlgorithmParameterThrowsException() {
        // It should throw an exception if the 'a' parameter is missing.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Sorting algorithm parameter 'a' is required", exception.getMessage());
    }

    @Test
    void testInvalidAlgorithmCodeThrowsException() {
        // It should throw an exception for an unsupported algorithm code.
        parameters.put("a", "x");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Invalid sorting algorithm code. Valid codes are: c (Counting), r (Radix), i (Insertion), q (Quick)", exception.getMessage());
    }

    @Test
    void testValidAlgorithmCodesPass() {
        // It should not throw an exception for valid algorithm codes.
        String[] validCodes = {"c", "r", "i", "q"};
        for (String code : validCodes) {
            parameters.put("a", code);
            assertDoesNotThrow(() -> validator.validate(parameters));
        }
    }
}

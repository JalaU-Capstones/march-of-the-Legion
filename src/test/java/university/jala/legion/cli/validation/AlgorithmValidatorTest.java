package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
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
    void testInvalidAlgorithmCodeReturnsError() {
        // It should return an error message for an unsupported algorithm code.
        parameters.put("a", "x");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Value of Algorithm is invalid. Accepted values are: c (Counting), r (Radix), i (Insertion), q (Quick). You entered: 'x'.", errors.get(0));
    }

    @Test
    void testNullAlgorithmCodeReturnsError() {
        // It should handle a null value gracefully and return an error.
        parameters.put("a", null);
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertTrue(errors.get(0).contains("You entered: 'null'"));
    }

    @Test
    void testValidAlgorithmCodesPass() {
        // It should not return any errors for valid algorithm codes.
        String[] validCodes = {"c", "r", "i", "q"};
        for (String code : validCodes) {
            parameters.put("a", code);
            List<String> errors = validator.validate(parameters);
            assertTrue(errors.isEmpty(), "Validation should pass for code: " + code);
        }
    }
}

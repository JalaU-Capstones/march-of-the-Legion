package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link OrientationValidator} class.
 */
class OrientationValidatorTest {

    private OrientationValidator validator;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        validator = new OrientationValidator();
        parameters = new HashMap<>();
    }

    @Test
    void testValidOrientationCodes() {
        // It should not return any errors for valid orientation codes.
        String[] validCodes = {"n", "s", "e", "w"};
        for (String code : validCodes) {
            parameters.put("o", code);
            List<String> errors = validator.validate(parameters);
            assertTrue(errors.isEmpty(), "Validation should pass for code: " + code);
        }
    }

    @Test
    void testInvalidOrientationCodeReturnsError() {
        // It should return an error message for an unsupported orientation code.
        parameters.put("o", "x");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Value of Orientation is invalid. Accepted values are: n (North), s (South), e (East), w (West). You entered: 'x'.", errors.get(0));
    }
}

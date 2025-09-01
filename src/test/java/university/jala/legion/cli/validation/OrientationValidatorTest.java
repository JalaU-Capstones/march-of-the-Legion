package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
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
        // It should not throw an exception for valid orientation codes.
        String[] validCodes = {"n", "s", "e", "w"};
        for (String code : validCodes) {
            parameters.put("o", code);
            assertDoesNotThrow(() -> validator.validate(parameters));
        }
    }

    @Test
    void testInvalidOrientationCodeThrowsException() {
        // It should throw an exception for an unsupported orientation code.
        parameters.put("o", "x");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Invalid orientation code: x", exception.getMessage());
    }
}

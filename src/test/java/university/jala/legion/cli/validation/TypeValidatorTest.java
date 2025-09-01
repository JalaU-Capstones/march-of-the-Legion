package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link TypeValidator} class.
 */
class TypeValidatorTest {

    private TypeValidator validator;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        validator = new TypeValidator();
        parameters = new HashMap<>();
    }

    @Test
    void testValidTypeCodes() {
        // It should not throw an exception for valid type codes.
        String[] validCodes = {"c", "n"};
        for (String code : validCodes) {
            parameters.put("t", code);
            assertDoesNotThrow(() -> validator.validate(parameters));
        }
    }

    @Test
    void testInvalidTypeCodeThrowsException() {
        // It should throw an exception for an unsupported type code.
        parameters.put("t", "x");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Invalid display type code: x", exception.getMessage());
    }
}

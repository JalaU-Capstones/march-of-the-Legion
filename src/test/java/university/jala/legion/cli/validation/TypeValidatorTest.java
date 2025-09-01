package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
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
        // It should not return any errors for valid type codes.
        String[] validCodes = {"c", "n"};
        for (String code : validCodes) {
            parameters.put("t", code);
            List<String> errors = validator.validate(parameters);
            assertTrue(errors.isEmpty(), "Validation should pass for code: " + code);
        }
    }

    @Test
    void testInvalidTypeCodeReturnsError() {
        // It should return an error message for an unsupported type code.
        parameters.put("t", "x");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Value of Type is invalid. Accepted values are: c (Character), n (Numeric). You entered: 'x'.", errors.get(0));
    }

    @Test
    void testMissingTypeParameterReturnsError() {
        // It should return an error because the 't' parameter is mandatory.
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Parameter 't' (Type) is mandatory and was not provided.", errors.get(0));
    }
}

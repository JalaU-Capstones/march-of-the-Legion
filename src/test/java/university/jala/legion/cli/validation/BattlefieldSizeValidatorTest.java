package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BattlefieldSizeValidator} class.
 */
class BattlefieldSizeValidatorTest {

    private BattlefieldSizeValidator validator;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        validator = new BattlefieldSizeValidator();
        parameters = new HashMap<>();
    }

    @Test
    void testValidBattlefieldSize() {
        // It should not return any errors for a size within the valid range.
        parameters.put("f", "100");
        List<String> errors = validator.validate(parameters);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testBattlefieldSizeTooSmallReturnsError() {
        // It should return an error message if the size is below the minimum.
        parameters.put("f", "4");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Value of Battlefield Size is out of range. It must be between 5 and 1000. You entered: '4'.", errors.get(0));
    }

    @Test
    void testBattlefieldSizeTooLargeReturnsError() {
        // It should return an error message if the size is above the maximum.
        parameters.put("f", "1001");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Value of Battlefield Size is out of range. It must be between 5 and 1000. You entered: '1001'.", errors.get(0));
    }

    @Test
    void testInvalidFormatForBattlefieldSizeReturnsError() {
        // It should return an error message for a non-numeric size.
        parameters.put("f", "abc");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Value of Battlefield Size is not a valid integer. You entered: 'abc'.", errors.get(0));
    }
}

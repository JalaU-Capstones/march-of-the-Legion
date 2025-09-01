package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
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
        // It should not throw an exception for a size within the valid range.
        parameters.put("f", "100");
        assertDoesNotThrow(() -> validator.validate(parameters));
    }

    @Test
    void testBattlefieldSizeTooSmallThrowsException() {
        // It should throw an exception if the size is below the minimum.
        parameters.put("f", "4");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Battlefield size must be between 5 and 1000", exception.getMessage());
    }

    @Test
    void testBattlefieldSizeTooLargeThrowsException() {
        // It should throw an exception if the size is above the maximum.
        parameters.put("f", "1001");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Battlefield size must be between 5 and 1000", exception.getMessage());
    }

    @Test
    void testInvalidFormatForBattlefieldSizeThrowsException() {
        // It should throw an exception for a non-numeric size.
        parameters.put("f", "abc");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Invalid battlefield size format", exception.getMessage());
    }
}

package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link UnitDistributionValidator} class.
 */
class UnitDistributionValidatorTest {

    private UnitDistributionValidator validator;
    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        validator = new UnitDistributionValidator();
        parameters = new HashMap<>();
        parameters.put("f", "10"); // Default battlefield size for capacity checks
    }

    @Test
    void testMissingUnitDistributionThrowsException() {
        // It should throw an exception if the 'u' parameter is missing.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Unit distribution parameter 'u' is required", exception.getMessage());
    }

    @Test
    void testWrongNumberOfUnitTypesThrowsException() {
        // It should throw an exception if the distribution does not have 5 values.
        parameters.put("u", "1,2,3,4");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Unit distribution must specify 5 unit types", exception.getMessage());
    }

    @Test
    void testNegativeUnitCountThrowsException() {
        // It should throw an exception if any unit count is negative.
        parameters.put("u", "1,2,-3,4,5");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Unit counts cannot be negative", exception.getMessage());
    }

    @Test
    void testInvalidUnitCountFormatThrowsException() {
        // It should throw an exception for a non-numeric unit count.
        parameters.put("u", "1,2,abc,4,5");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Invalid unit count format", exception.getMessage());
    }

    @Test
    void testTotalUnitsExceedingCapacityThrowsException() {
        // It should throw an exception if the total units exceed the battlefield area.
        parameters.put("f", "5"); // 5x5 = 25
        parameters.put("u", "10,10,3,2,1"); // Total = 26
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(parameters);
        });
        assertEquals("Total units exceed battlefield capacity", exception.getMessage());
    }

    @Test
    void testValidUnitDistribution() {
        // It should not throw an exception for a valid distribution.
        parameters.put("u", "1,2,3,4,5");
        assertDoesNotThrow(() -> validator.validate(parameters));
    }
}

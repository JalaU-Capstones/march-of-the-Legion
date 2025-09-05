package university.jala.legion.cli.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
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
        // Provide a default battlefield size for capacity checks, as the validator depends on it.
        parameters.put("f", "10");
    }

    @Test
    void testMissingUnitDistributionReturnsError() {
        // It should return an error if the 'u' or 'r' parameter is missing.
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Parameter 'u' or 'r' (Unit Distribution) is mandatory and was not provided.", errors.get(0));
    }

    @Test
    void testWrongNumberOfUnitTypesReturnsError() {
        // It should return an error if the distribution does not have 5 values.
        parameters.put("u", "1,2,3,4");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Unit Distribution must contain 5 comma-separated values. You provided 4.", errors.get(0));
    }

    @Test
    void testNegativeUnitCountReturnsError() {
        // It should return an error if any unit count is negative.
        parameters.put("u", "1,2,-3,4,5");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Unit counts cannot be negative. You entered: '-3'.", errors.get(0));
    }

    @Test
    void testInvalidUnitCountFormatReturnsError() {
        // It should return an error for a non-numeric unit count.
        parameters.put("u", "1,2,abc,4,5");
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertEquals("Unit Distribution contains an invalid number. All values must be integers. You entered: '1,2,abc,4,5'.", errors.get(0));
    }

    @Test
    void testTotalUnitsExceedingCapacityReturnsError() {
        // It should return an error if the total units exceed the battlefield area.
        parameters.put("f", "5"); // 5x5 = 25
        parameters.put("u", "10,10,3,2,1"); // Total = 26
        List<String> errors = validator.validate(parameters);
        assertFalse(errors.isEmpty());
        assertTrue(errors.get(0).contains("exceeds the battlefield capacity"));
    }

    @Test
    void testValidUnitDistribution() {
        // It should not return any errors for a valid distribution.
        parameters.put("u", "1,2,3,4,5");
        List<String> errors = validator.validate(parameters);
        assertTrue(errors.isEmpty());
    }
}

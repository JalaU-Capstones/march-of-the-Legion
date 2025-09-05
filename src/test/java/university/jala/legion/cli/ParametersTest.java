package university.jala.legion.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the Parameters class, focusing on validation logic and default values.
 */
class ParametersTest {

    @Test
    void testValidParametersAreValid() {
        // It should be valid when all parameters are correct.
        String[] args = {"a=c", "t=c", "u=1,1,1,1,1", "f=10", "o=n"};
        Parameters params = new Parameters(args);
        assertTrue(params.isValid());
        assertTrue(params.getValidationErrors().isEmpty());
    }

    @Test
    void testDefaultValuesAreAppliedCorrectly() {
        // It should apply defaults for 'f', 'o', and 'a' when they are missing.
        String[] args = {"t=c", "u=1,1,1,1,1"}; // Missing f, o, a
        Parameters params = new Parameters(args);

        assertTrue(params.isValid(), "Validation should pass with default values.");
        assertEquals(6, params.getBattlefieldSize(), "Default battlefield size should be 6.");
        assertEquals("n", params.getOrientation(), "Default orientation should be 'n'.");
        assertEquals("i", params.getAlgorithm(), "Default algorithm should be 'i'.");
    }

    @Test
    void testMissingMandatoryParametersIsInvalid() {
        // It should be invalid if mandatory parameters 't' or 'u'/'r' are missing.
        String[] argsWithoutT = {"a=c", "u=1,1,1,1,1"};
        Parameters paramsWithoutT = new Parameters(argsWithoutT);
        assertFalse(paramsWithoutT.isValid());
        assertTrue(paramsWithoutT.getValidationErrors().stream().anyMatch(e -> e.contains("Parameter 't' (Type) is mandatory")));

        String[] argsWithoutU = {"a=c", "t=c"};
        Parameters paramsWithoutU = new Parameters(argsWithoutU);
        assertFalse(paramsWithoutU.isValid());
        assertTrue(paramsWithoutU.getValidationErrors().stream().anyMatch(e -> e.contains("Parameter 'u' or 'r' (Unit Distribution) is mandatory")));
    }

    @Test
    void testMultipleErrorsAreCollected() {
        // It should collect and report all validation errors, not just the first one.
        String[] args = {"a=x", "f=4", "o=z", "t=y", "u=1,2"};
        Parameters params = new Parameters(args);
        assertFalse(params.isValid());
        List<String> errors = params.getValidationErrors();
        assertEquals(5, errors.size(), "Should collect all 5 errors.");
        assertTrue(errors.stream().anyMatch(e -> e.contains("Algorithm is invalid")));
        assertTrue(errors.stream().anyMatch(e -> e.contains("Battlefield Size is out of range")));
        assertTrue(errors.stream().anyMatch(e -> e.contains("Orientation is invalid")));
        assertTrue(errors.stream().anyMatch(e -> e.contains("Type is invalid")));
        assertTrue(errors.stream().anyMatch(e -> e.contains("Unit Distribution must contain 5")));
    }

    @Test
    void testInvalidBattlefieldSizeIsInvalid() {
        String[] args = {"t=c", "u=1,1,1,1,1", "f=abc"};
        Parameters params = new Parameters(args);
        assertFalse(params.isValid());
        assertEquals(1, params.getValidationErrors().size());
        assertTrue(params.getValidationErrors().get(0).contains("not a valid integer"));
    }

    @Test
    void testUnitDistributionExceedsCapacityIsInvalid() {
        String[] args = {"t=c", "u=10,10,10,10,10", "f=5"}; // 50 units on a 5x5 grid
        Parameters params = new Parameters(args);
        assertFalse(params.isValid());
        assertEquals(1, params.getValidationErrors().size());
        assertTrue(params.getValidationErrors().getFirst().contains("exceeds the battlefield capacity"));
    }
}

package university.jala.legion.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Parameters class.
 */
class ParametersTest {

    // Helper method to create valid arguments
    private String[] createValidArgs() {
        return new String[]{
                "a=c",  // Counting sort
                "u=1,1,1,1,1",
                "o=n",  // North
                "t=c",  // Console display
                "f=20"  // Battlefield size 20
        };
    }

    @Test
    void testValidParameters() {
        // Test with a complete set of valid parameters
        String[] args = createValidArgs();
        Parameters parameters = new Parameters(args);

        assertEquals("c", parameters.getAlgorithm());
        assertEquals("n", parameters.getOrientation());
        assertEquals("c", parameters.getType());
        assertEquals(20, parameters.getBattlefieldSize());
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, parameters.getUnitDistribution());
    }

    @Test
    void testDefaultValuesWhenMissing() {
        // Test with only the required parameters, checking if defaults are applied
        String[] args = new String[]{
                "a=r",
                "u=10,0,0,0,0"
        };
        Parameters parameters = new Parameters(args);

        assertEquals("r", parameters.getAlgorithm());
        assertEquals("n", parameters.getOrientation()); // Default
        assertEquals("c", parameters.getType());       // Default
        assertEquals(6, parameters.getBattlefieldSize()); // Default
        assertArrayEquals(new int[]{10, 0, 0, 0, 0}, parameters.getUnitDistribution());
    }

    @Test
    void testMissingRequiredParameters_Algorithm() {
        // Test case for missing 'a' parameter
        String[] args = new String[]{"u=1,1,1,1,1"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Sorting algorithm parameter 'a' is required", thrown.getMessage());
    }

    @Test
    void testMissingRequiredParameters_UnitDistribution() {
        // Test case for missing 'u' parameter
        String[] args = new String[]{"a=q"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Unit distribution parameter 'u' is required", thrown.getMessage());
    }

    @Test
    void testInvalidAlgorithm() {
        // Test case for an invalid algorithm code
        String[] args = new String[]{"a=x", "u=1,1,1,1,1"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Invalid sorting algorithm code. Valid codes are: c (Counting), r (Radix), i (Insertion), q (Quick)", thrown.getMessage());
    }

    @Test
    void testInvalidOrientation() {
        // Test case for an invalid orientation code
        String[] args = new String[]{"a=i", "u=1,1,1,1,1", "o=x"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertTrue(thrown.getMessage().contains("Invalid orientation code: x"));
    }

    @Test
    void testInvalidType() {
        // Test case for an invalid type code
        String[] args = new String[]{"a=q", "u=1,1,1,1,1", "t=x"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertTrue(thrown.getMessage().contains("Invalid display type code: x"));
    }

    @Test
    void testBattlefieldSizeTooSmall() {
        // Test case for battlefield size less than 5
        String[] args = new String[]{"a=c", "u=1,1,1,1,1", "f=4"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Battlefield size must be between 5 and 1000", thrown.getMessage());
    }

    @Test
    void testBattlefieldSizeTooLarge() {
        // Test case for battlefield size greater than 1000
        String[] args = new String[]{"a=c", "u=1,1,1,1,1", "f=1001"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Battlefield size must be between 5 and 1000", thrown.getMessage());
    }

    @Test
    void testBattlefieldSizeInvalidFormat() {
        // Test case for non-integer battlefield size
        String[] args = new String[]{"a=c", "u=1,1,1,1,1", "f=abc"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Invalid battlefield size format", thrown.getMessage());
    }

    @Test
    void testUnitDistributionWrongLength() {
        // Test case for unit distribution with wrong number of units (not 5)
        String[] args = new String[]{"a=c", "u=1,1,1,1,1,1"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Unit distribution must specify 5 unit types", thrown.getMessage());
    }

    @Test
    void testUnitDistributionNegativeCount() {
        // Test case for negative unit count
        String[] args = new String[]{"a=c", "u=1,1,-1,1,1"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Unit counts cannot be negative", thrown.getMessage());
    }

    @Test
    void testUnitDistributionInvalidFormat() {
        // Test case for non-integer unit count
        String[] args = new String[]{"a=c", "u=1,1,a,1,1"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Invalid unit count format", thrown.getMessage());
    }

    @Test
    void testUnitDistributionExceedsBattlefieldCapacity() {
        // Test case where total units exceed battlefield capacity (10*10=100)
        String[] args = new String[]{"a=c", "u=20,20,20,20,21", "f=10"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Total units exceed battlefield capacity", thrown.getMessage());
    }

    @Test
    void testUnitDistributionExceedsBattlefieldCapacity_Large() {
        // Test case where total units exceed a larger battlefield capacity (50*50=2500)
        String[] args = new String[]{"a=c", "u=500,500,500,500,501", "f=50"};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Parameters(args));
        assertEquals("Total units exceed battlefield capacity", thrown.getMessage());
    }
}

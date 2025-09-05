package university.jala.legion.model.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AlgorithmType} enum.
 */
class AlgorithmTypeTest {

    @Test
    void testGetFullNameByCode() {
        // It should return the correct full name for each valid code.
        assertEquals("Counting Sort", AlgorithmType.getFullNameByCode("c"));
        assertEquals("Radix Sort", AlgorithmType.getFullNameByCode("r"));
        assertEquals("Quick Sort", AlgorithmType.getFullNameByCode("q"));
        assertEquals("Insertion Sort", AlgorithmType.getFullNameByCode("i"));
    }

    @Test
    void testGetFullNameByCodeIsCaseInsensitive() {
        // It should handle uppercase codes correctly.
        assertEquals("Counting Sort", AlgorithmType.getFullNameByCode("C"));
        assertEquals("Radix Sort", AlgorithmType.getFullNameByCode("R"));
        assertEquals("Quick Sort", AlgorithmType.getFullNameByCode("Q"));
        assertEquals("Insertion Sort", AlgorithmType.getFullNameByCode("I"));
    }

    @Test
    void testGetFullNameForInvalidCode() {
        // It should return a default value for an unknown code.
        assertEquals("Unknown", AlgorithmType.getFullNameByCode("x"));
        assertEquals("Unknown", AlgorithmType.getFullNameByCode(""));
        assertEquals("Unknown", AlgorithmType.getFullNameByCode(null));
    }
}

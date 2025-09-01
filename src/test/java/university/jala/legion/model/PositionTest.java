package university.jala.legion.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Position} record.
 */
class PositionTest {

    @Test
    void testPositionInitialization() {
        // Create a new Position instance
        Position position = new Position(5, 10);

        // Assert that the row and column are set correctly using record accessors
        assertEquals(5, position.row());
        assertEquals(10, position.column());
    }

    @Test
    void testEqualsAndHashCode() {
        // Records provide reliable implementations of equals() and hashCode().
        Position pos1 = new Position(3, 7);
        Position pos2 = new Position(3, 7);
        Position pos3 = new Position(5, 7);

        // Assert that two positions with the same coordinates are equal and have the same hash code.
        assertEquals(pos1, pos2);
        assertEquals(pos1.hashCode(), pos2.hashCode());

        // Assert that two positions with different coordinates are not equal.
        assertNotEquals(pos1, pos3);
    }

    @Test
    void testToString() {
        // Create a Position instance
        Position position = new Position(1, 2);

        // Assert that the toString() method returns the expected record format.
        assertEquals("Position[row=1, column=2]", position.toString());
    }
}

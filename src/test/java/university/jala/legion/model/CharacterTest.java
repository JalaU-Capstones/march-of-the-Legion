package university.jala.legion.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the abstract Character class.
 * This class uses a concrete subclass for testing purposes.
 */
class CharacterTest {

    // A concrete subclass of Character for testing.
    static class TestCharacter extends Character {
        public TestCharacter(int rank, char symbol, int numericRange) {
            super(rank, symbol, numericRange);
        }

        @Override
        public String getType() {
            return "TestType";
        }
    }

    @Test
    void testCharacterConstructorAndGetters() {
        // Create an instance of the concrete subclass
        TestCharacter character = new TestCharacter(10, 'A', 5);

        // Verify the initial values set by the constructor
        assertEquals(10, character.getRank());
        assertEquals('A', character.getSymbol());
        assertEquals(5, character.getNumericRange());
        assertEquals("TestType", character.getType());
        assertNull(character.getPosition());
    }

    @Test
    void testSetAndGetPosition() {
        // Create an instance and a position
        TestCharacter character = new TestCharacter(5, 'B', 2);
        Position position = new Position(3, 4);

        // Set the position and verify it was set correctly
        character.setPosition(position);
        assertEquals(position, character.getPosition());
    }

    @Test
    void testToString() {
        // Test that toString() returns the character's symbol as a string
        TestCharacter character = new TestCharacter(1, 'X', 1);
        assertEquals("X", character.toString());
    }
}

package university.jala.legion.rendering;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link RendererFactory} class.
 */
class RendererFactoryTest {

    @Test
    void testCreateCharacterRenderer() {
        // It should return an instance of CharacterRenderer for type 'c'.
        BattlefieldRenderer renderer = RendererFactory.create("c");
        assertInstanceOf(CharacterRenderer.class, renderer);
    }

    @Test
    void testCreateNumericRenderer() {
        // It should return an instance of NumericRenderer for type 'n'.
        BattlefieldRenderer renderer = RendererFactory.create("n");
        assertInstanceOf(NumericRenderer.class, renderer);
    }

    @Test
    void testCreateIsCaseInsensitive() {
        // It should create the correct renderer regardless of case.
        BattlefieldRenderer renderer = RendererFactory.create("C");
        assertInstanceOf(CharacterRenderer.class, renderer);
    }

    @Test
    void testCreateWithInvalidTypeThrowsException() {
        // It should throw an exception for an unknown renderer type.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RendererFactory.create("x");
        });
        assertEquals("Invalid display type code: x", exception.getMessage());
    }
}

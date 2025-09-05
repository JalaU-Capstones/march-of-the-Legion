package university.jala.legion.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link StartupPresenter} class.
 */
class StartupPresenterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintBanner() {
        // It should print the banner text to the console.
        // This test calls printBanner() directly to avoid the sound and sleep logic.
        StartupPresenter presenter = new StartupPresenter();
        presenter.printBanner();

        String output = outContent.toString();
        // Assert against a unique line from the new ASCII art.
        assertTrue(output.contains("███╗   ███╗ █████╗ ██████╗  ██████╗██╗  ██╗"), "Output should contain the new banner art.");
    }
}

package university.jala.legion.simulation.reporter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import university.jala.legion.cli.CliParameters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link SimulationReporter} class.
 */
class SimulationReporterTest {

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
    void testDisplayParametersShowsFullAlgorithmName() {
        // It should display the full algorithm name, not the short code.
        CliParameters mockParams = Mockito.mock(CliParameters.class);
        Mockito.when(mockParams.getAlgorithm()).thenReturn("q");
        Mockito.when(mockParams.getType()).thenReturn("c");
        Mockito.when(mockParams.getOrientation()).thenReturn("n");
        Mockito.when(mockParams.getUnitDistribution()).thenReturn(new int[]{1, 1, 1, 1, 1});
        Mockito.when(mockParams.getBattlefieldSize()).thenReturn(5);

        SimulationReporter reporter = new SimulationReporter(mockParams);
        reporter.displayParameters();

        String output = outContent.toString();
        assertTrue(output.contains("Algorithm: [Quick Sort]"), "Output should contain the full algorithm name.");
    }
}

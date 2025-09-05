package university.jala.legion.simulation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.jala.legion.cli.CliParameters;
import university.jala.legion.exception.SimulationException;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the {@link Simulation} class.
 */
class SimulationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final int DELAY_MS = 2000;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private CliParameters createMockParameters() {
        CliParameters params = Mockito.mock(CliParameters.class);
        Mockito.when(params.getAlgorithm()).thenReturn("c");
        Mockito.when(params.getType()).thenReturn("c");
        Mockito.when(params.getOrientation()).thenReturn("n");
        Mockito.when(params.getBattlefieldSize()).thenReturn(10);
        Mockito.when(params.getUnitDistribution()).thenReturn(new int[]{1, 2, 3, 4, 5});
        return params;
    }

    @Test
    void testSimulationRunCompletesSuccessfully() throws SimulationException {
        // It should run the full simulation without throwing exceptions for valid parameters.
        CliParameters params = createMockParameters();
        Simulation simulation = new Simulation(params);

        simulation.run();

        String output = outContent.toString();
        assertTrue(output.contains("Initial Position:"));
        assertTrue(output.contains("Final Position:"));
        assertTrue(output.contains("Execution time:"));
    }

    @Test
    void testSimulationRunIncludesDelay() throws SimulationException {
        // It should pause between printing the initial and final states.
        CliParameters params = createMockParameters();
        Simulation simulation = new Simulation(params);

        long startTime = System.currentTimeMillis();
        simulation.run();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        assertTrue(duration >= DELAY_MS, "The simulation should take at least " + DELAY_MS + "ms due to the delay.");
    }

    @Test
    void testSimulationRunFailsWithImpossiblePlacement() {
        // It should throw SimulationException when the units cannot be placed on the battlefield.
        CliParameters params = Mockito.mock(CliParameters.class);
        Mockito.when(params.getAlgorithm()).thenReturn("c");
        Mockito.when(params.getType()).thenReturn("c");
        Mockito.when(params.getOrientation()).thenReturn("n");
        Mockito.when(params.getBattlefieldSize()).thenReturn(2); // Only 2 rows
        Mockito.when(params.getUnitDistribution()).thenReturn(new int[]{1, 1, 1, 0, 0}); // 3 ranks

        Simulation simulation = new Simulation(params);

        SimulationException exception = assertThrows(SimulationException.class, simulation::run);
        assertTrue(exception.getMessage().contains("Not enough space on the battlefield"));
    }
}

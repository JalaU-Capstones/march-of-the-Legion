package university.jala.legion.simulation;

import university.jala.legion.cli.CliParameters;
import university.jala.legion.exception.SimulationException;
import university.jala.legion.model.Battlefield;
import university.jala.legion.model.UnitFactory;
import university.jala.legion.model.enums.UnitType;
import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.rendering.BattlefieldRenderer;
import university.jala.legion.rendering.RendererFactory;
import university.jala.legion.simulation.reporter.SimulationReporter;
import university.jala.legion.sorting.TroopArranger;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the entire simulation workflow from start to finish.
 * This class orchestrates the creation of units, battlefield setup, initial and final
 * placement, and the rendering of the results, acting as the core engine of the application.
 */
public class Simulation {

    private final Battlefield battlefield;
    private final List<ICharacter> units;
    private final BattlefieldRenderer renderer;
    private final SimulationReporter reporter;
    private final TroopArranger arranger;
    private static final int DELAY_BETWEEN_STATES_MS = 2000;

    /**
     * Constructs a new Simulation instance based on the provided command-line parameters.
     *
     * @param params The validated command-line parameters for the simulation.
     */
    public Simulation(CliParameters params) {
        this.battlefield = new Battlefield(params.getBattlefieldSize());
        this.units = createUnits(params.getUnitDistribution());
        this.renderer = RendererFactory.create(params.getType());
        this.reporter = new SimulationReporter(params);
        this.arranger = new TroopArranger(params.getAlgorithm(), params.getOrientation().charAt(0));
    }

    /**
     * Executes the simulation, running through all the steps from initial setup to final report.
     *
     * @throws SimulationException if the simulation encounters a runtime error, such as units not fitting.
     */
    public void run() throws SimulationException {
        reporter.displayParameters();

        battlefield.placeUnitsRandomly(units);
        System.out.println("\nInitial Position:");
        System.out.println(renderer.render(battlefield));

        try {
            Thread.sleep(DELAY_BETWEEN_STATES_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long startTime = System.currentTimeMillis();
        arranger.arrange(units, battlefield.getSize());
        long endTime = System.currentTimeMillis();

        battlefield.setUnits(units);

        System.out.println("\nFinal Position:");
        System.out.println(renderer.render(battlefield));

        reporter.displayExecutionTime(endTime - startTime);
    }

    private List<ICharacter> createUnits(int[] distribution) {
        List<ICharacter> createdUnits = new ArrayList<>();
        UnitType[] types = UnitType.values();
        for (int i = 0; i < distribution.length; i++) {
            for (int j = 0; j < distribution[i]; j++) {
                createdUnits.add(UnitFactory.createUnit(types[i], j));
            }
        }
        return createdUnits;
    }
}

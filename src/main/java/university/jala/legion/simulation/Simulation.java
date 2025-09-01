package university.jala.legion.simulation;

import university.jala.legion.cli.CliParameters;
import university.jala.legion.model.Battlefield;
import university.jala.legion.model.Position;
import university.jala.legion.model.UnitFactory;
import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.rendering.BattlefieldRenderer;
import university.jala.legion.rendering.RendererFactory;
import university.jala.legion.sorting.TroopArranger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Manages the entire simulation workflow from start to finish.
 * This class orchestrates the creation of units, battlefield setup, initial and final
 * placement, and the rendering of the results, acting as the core engine of the application.
 */
public class Simulation {

    private final CliParameters params;
    private final Battlefield battlefield;
    private final List<ICharacter> units;
    private final BattlefieldRenderer renderer;

    /**
     * Constructs a new Simulation instance based on the provided command-line parameters.
     *
     * @param params The validated command-line parameters for the simulation.
     */
    public Simulation(CliParameters params) {
        this.params = params;
        this.battlefield = new Battlefield(params.getBattlefieldSize());
        this.units = createUnits(params.getUnitDistribution());
        this.renderer = RendererFactory.create(params.getType());
    }

    /**
     * Executes the simulation, running through all the steps from initial setup to final report.
     */
    public void run() {
        displayParameters();

        placeUnitsRandomly();
        System.out.println("\nInitial Position:");
        System.out.println(renderer.render(battlefield));

        TroopArranger arranger = new TroopArranger(params.getAlgorithm(), params.getOrientation().charAt(0));

        long startTime = System.currentTimeMillis();
        arranger.arrange(units, params.getBattlefieldSize());
        long endTime = System.currentTimeMillis();

        battlefield.setUnits(units);

        System.out.println("\nFinal Position:");
        System.out.println(renderer.render(battlefield));

        System.out.println("\nExecution time: " + (endTime - startTime) + "ms");
    }

    private List<ICharacter> createUnits(int[] distribution) {
        List<ICharacter> createdUnits = new ArrayList<>();
        String[] types = {"commander", "medic", "tank", "sniper", "infantry"};
        for (int i = 0; i < distribution.length; i++) {
            for (int j = 0; j < distribution[i]; j++) {
                createdUnits.add(UnitFactory.createUnit(types[i]));
            }
        }
        return createdUnits;
    }

    private void placeUnitsRandomly() {
        battlefield.clear();
        List<ICharacter> randomUnits = new ArrayList<>(units);
        Random random = new Random();
        int size = params.getBattlefieldSize();

        List<Integer> availableSlots = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            availableSlots.add(i);
        }
        Collections.shuffle(availableSlots, random);

        for (int i = 0; i < randomUnits.size(); i++) {
            int slot = availableSlots.get(i);
            randomUnits.get(i).setPosition(new Position(slot / size, slot % size));
        }
        battlefield.setUnits(randomUnits);
    }

    private void displayParameters() {
        System.out.println("Algorithm: [" + params.getAlgorithm() + "]");
        System.out.println("Type: [" + (params.getType().equals("c") ? "Character" : "Numeric") + "]");
        System.out.println("Orientation: [" + getOrientationName(params.getOrientation()) + "]");
        System.out.println("Troops: [" + IntStream.of(params.getUnitDistribution()).sum() + "]");
        System.out.println("Battlefield: [" + params.getBattlefieldSize() + " x " + params.getBattlefieldSize() + "]");
    }

    private String getOrientationName(String orientationCode) {
        return switch (orientationCode.toLowerCase()) {
            case "n" -> "North";
            case "s" -> "South";
            case "e" -> "East";
            case "w" -> "West";
            default -> "Unknown";
        };
    }
}

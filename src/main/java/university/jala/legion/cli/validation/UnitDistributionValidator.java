package university.jala.legion.cli.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Validates the unit distribution parameter ('u' or 'r').
 */
public class UnitDistributionValidator implements ParameterValidator {
    private static final int EXPECTED_UNIT_TYPES = 5;

    @Override
    public List<String> validate(Map<String, String> parameters) {
        if (!parameters.containsKey("u")) {
            return List.of("Parameter 'u' or 'r' (Unit Distribution) is mandatory and was not provided.");
        }

        String distributionStr = parameters.get("u");
        String[] unitCountsStr = distributionStr.split(",");

        if (unitCountsStr.length != EXPECTED_UNIT_TYPES) {
            String errorMessage = String.format(
                "Unit Distribution must contain %d comma-separated values. You provided %d.",
                EXPECTED_UNIT_TYPES, unitCountsStr.length
            );
            return List.of(errorMessage);
        }

        List<Integer> unitCounts = new ArrayList<>();
        try {
            for (String countStr : unitCountsStr) {
                int count = Integer.parseInt(countStr.trim());
                if (count < 0) {
                    String errorMessage = String.format(
                        "Unit counts cannot be negative. You entered: '%s'.",
                        countStr
                    );
                    return List.of(errorMessage);
                }
                unitCounts.add(count);
            }
        } catch (NumberFormatException e) {
            String errorMessage = String.format(
                "Unit Distribution contains an invalid number. All values must be integers. You entered: '%s'.",
                distributionStr
            );
            return List.of(errorMessage);
        }

        int battlefieldSize;
        try {
            battlefieldSize = Integer.parseInt(parameters.get("f"));
        } catch (NumberFormatException e) {
            return Collections.emptyList(); // Handled by BattlefieldSizeValidator
        }

        // Validate total unit count against the total area
        int totalUnits = unitCounts.stream().mapToInt(Integer::intValue).sum();
        if (totalUnits > battlefieldSize * battlefieldSize) {
            String errorMessage = String.format(
                "Total number of units (%d) exceeds the battlefield capacity of %d (%dx%d).",
                totalUnits, battlefieldSize * battlefieldSize, battlefieldSize, battlefieldSize
            );
            return List.of(errorMessage);
        }

        // Validate that the units can be placed according to the placement strategy.
        // Each unit type requires its own set of rows/columns.
        int requiredDimension = 0;
        for (int count : unitCounts) {
            if (count > 0) {
                // Calculate rows/cols needed for this unit type using integer ceiling division
                requiredDimension += (count + battlefieldSize - 1) / battlefieldSize;
            }
        }

        if (requiredDimension > battlefieldSize) {
            String errorMessage = String.format(
                "Not enough space on the battlefield. The given units require %d rows/columns, but the battlefield size is only %d.",
                requiredDimension, battlefieldSize
            );
            return List.of(errorMessage);
        }

        return Collections.emptyList();
    }
}

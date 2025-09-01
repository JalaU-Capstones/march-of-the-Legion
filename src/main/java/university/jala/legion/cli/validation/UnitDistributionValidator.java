package university.jala.legion.cli.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Validates the unit distribution parameter ('u').
 */
public class UnitDistributionValidator implements ParameterValidator {
    private static final int EXPECTED_UNIT_TYPES = 5;

    @Override
    public List<String> validate(Map<String, String> parameters) {
        if (!parameters.containsKey("u")) {
            return List.of("Parameter 'u' (Unit Distribution) is mandatory and was not provided.");
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
            // Defensively parse the battlefield size. If it's invalid, we can't check capacity.
            battlefieldSize = Integer.parseInt(parameters.get("f"));
        } catch (NumberFormatException e) {
            // The BattlefieldSizeValidator is responsible for reporting this error.
            // We can safely return here as the capacity check cannot be performed.
            return Collections.emptyList();
        }

        int totalUnits = unitCounts.stream().mapToInt(Integer::intValue).sum();
        if (totalUnits > battlefieldSize * battlefieldSize) {
            String errorMessage = String.format(
                "Total number of units (%d) exceeds the battlefield capacity of %d (%dx%d).",
                totalUnits, battlefieldSize * battlefieldSize, battlefieldSize, battlefieldSize
            );
            return List.of(errorMessage);
        }

        return Collections.emptyList();
    }
}

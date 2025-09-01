package university.jala.legion.cli.validation;

import java.util.Map;

/**
 * Validates the unit distribution parameter ('u').
 */
public class UnitDistributionValidator implements ParameterValidator {
    @Override
    public void validate(Map<String, String> parameters) {
        if (!parameters.containsKey("u")) {
            throw new IllegalArgumentException("Unit distribution parameter 'u' is required");
        }
        String distribution = parameters.get("u");
        String[] units = distribution.split(",");
        if (units.length != 5) {
            throw new IllegalArgumentException("Unit distribution must specify 5 unit types");
        }
        int totalUnits = 0;
        try {
            for (String unit : units) {
                int count = Integer.parseInt(unit);
                if (count < 0) {
                    throw new IllegalArgumentException("Unit counts cannot be negative");
                }
                totalUnits += count;
            }
            int battlefieldSize = Integer.parseInt(parameters.get("f"));
            if (totalUnits > battlefieldSize * battlefieldSize) {
                throw new IllegalArgumentException("Total units exceed battlefield capacity");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid unit count format");
        }
    }
}

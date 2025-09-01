package university.jala.legion.cli.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and configures all the necessary parameter validators.
 * This factory supports the Open/Closed Principle by allowing new validators
 * to be added to the validation chain without modifying existing code.
 */
public class ParameterValidatorFactory {
    /**
     * Creates a list of all parameter validators.
     * @return A list of configured {@link ParameterValidator} instances.
     */
    public static List<ParameterValidator> createValidators() {
        List<ParameterValidator> validators = new ArrayList<>();
        validators.add(new AlgorithmValidator());
        validators.add(new BattlefieldSizeValidator());
        validators.add(new OrientationValidator());
        validators.add(new TypeValidator());
        validators.add(new UnitDistributionValidator());
        return validators;
    }
}

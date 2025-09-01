package university.jala.legion.sorting;

import university.jala.legion.model.interfaces.ICharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements the Counting Sort algorithm to sort characters by their rank.
 * This algorithm is efficient for sorting objects with a small range of integer keys,
 * such as the ranks of military units.
 */
public class CountingSort implements SortingStrategy {

    @Override
    public void sort(List<ICharacter> units) {
        if (units == null || units.size() <= 1) {
            return;
        }

        int maxRank = units.stream()
                .mapToInt(ICharacter::getRank)
                .max()
                .orElse(0);

        int[] count = new int[maxRank + 1];
        List<ICharacter> output = new ArrayList<>(Collections.nCopies(units.size(), null));

        for (ICharacter unit : units) {
            count[unit.getRank()]++;
        }

        for (int i = 1; i <= maxRank; i++) {
            count[i] += count[i - 1];
        }

        for (int i = units.size() - 1; i >= 0; i--) {
            ICharacter unit = units.get(i);
            int rank = unit.getRank();
            int position = count[rank] - 1;
            output.set(position, unit);
            count[rank]--;
        }

        for (int i = 0; i < units.size(); i++) {
            units.set(i, output.get(i));
        }
    }
}

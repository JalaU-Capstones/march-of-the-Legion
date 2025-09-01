package university.jala.legion.sorting;

import university.jala.legion.model.interfaces.ICharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements the Radix Sort algorithm to sort characters by their rank.
 * Radix sort is a non-comparative sorting algorithm that processes integer keys
 * by grouping them by individual digits sharing the same significant position.
 */
public class RadixSort implements SortingStrategy {

    @Override
    public void sort(List<ICharacter> units) {
        if (units == null || units.size() <= 1) {
            return;
        }

        int maxRank = units.stream()
                .mapToInt(ICharacter::getRank)
                .max()
                .orElse(0);

        for (int exp = 1; maxRank / exp > 0; exp *= 10) {
            countingSortByDigit(units, exp);
        }
    }

    private void countingSortByDigit(List<ICharacter> units, int exp) {
        int n = units.size();
        List<ICharacter> output = new ArrayList<>(Collections.nCopies(n, null));
        int[] count = new int[10];

        for (ICharacter unit : units) {
            count[(unit.getRank() / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            ICharacter unit = units.get(i);
            int digit = (unit.getRank() / exp) % 10;
            output.set(count[digit] - 1, unit);
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            units.set(i, output.get(i));
        }
    }
}

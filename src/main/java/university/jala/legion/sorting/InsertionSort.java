package university.jala.legion.sorting;

import university.jala.legion.model.interfaces.ICharacter;

import java.util.List;

/**
 * Implements the Insertion Sort algorithm to sort characters by their rank.
 * This is a simple, in-place sorting algorithm that is efficient for small datasets
 * or partially sorted data.
 */
public class InsertionSort implements SortingStrategy {

    @Override
    public void sort(List<ICharacter> units) {
        if (units == null || units.size() <= 1) {
            return;
        }

        for (int i = 1; i < units.size(); i++) {
            ICharacter key = units.get(i);
            int j = i - 1;

            while (j >= 0 && units.get(j).getRank() > key.getRank()) {
                units.set(j + 1, units.get(j));
                j--;
            }
            units.set(j + 1, key);
        }
    }
}

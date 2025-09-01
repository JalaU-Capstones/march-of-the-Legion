package university.jala.legion.sorting;

import university.jala.legion.model.interfaces.ICharacter;

import java.util.Collections;
import java.util.List;

/**
 * Implements the Quick Sort algorithm to sort characters by their rank.
 * This is a highly efficient, in-place, divide-and-conquer sorting algorithm.
 */
public class QuickSort implements SortingStrategy {

    @Override
    public void sort(List<ICharacter> units) {
        if (units != null && units.size() > 1) {
            quickSort(units, 0, units.size() - 1);
        }
    }

    private void quickSort(List<ICharacter> units, int low, int high) {
        if (low < high) {
            int pi = partition(units, low, high);
            quickSort(units, low, pi - 1);
            quickSort(units, pi + 1, high);
        }
    }

    private int partition(List<ICharacter> units, int low, int high) {
        ICharacter pivot = units.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (units.get(j).getRank() <= pivot.getRank()) {
                i++;
                Collections.swap(units, i, j);
            }
        }

        Collections.swap(units, i + 1, high);
        return i + 1;
    }
}

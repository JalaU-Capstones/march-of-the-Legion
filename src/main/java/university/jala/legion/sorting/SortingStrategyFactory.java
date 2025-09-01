package university.jala.legion.sorting;

/**
 * A factory for creating sorting strategies based on an algorithm code.
 * This class decouples the client from the concrete implementation of sorting algorithms,
 * allowing for flexible selection of the sorting method.
 */
public class SortingStrategyFactory {
    /**
     * Creates a {@link SortingStrategy} based on the given algorithm code.
     *
     * @param code The algorithm code ('c', 'r', 'q', 'i').
     * @return The corresponding sorting strategy.
     * @throws IllegalArgumentException if the algorithm code is invalid.
     */
    public static SortingStrategy create(String code) {
        return switch (code.toLowerCase()) {
            case "c" -> new CountingSort();
            case "r" -> new RadixSort();
            case "q" -> new QuickSort();
            case "i" -> new InsertionSort();
            default -> throw new IllegalArgumentException("Invalid sorting algorithm code. Valid codes are: c (Counting), r (Radix), q (Quick), i (Insertion)");
        };
    }
}

package university.jala.legion.model.enums;

import java.util.Arrays;

/**
 * Enum representing the available sorting algorithms.
 */
public enum AlgorithmType {
    COUNTING_SORT("c", "Counting Sort"),
    RADIX_SORT("r", "Radix Sort"),
    QUICK_SORT("q", "Quick Sort"),
    INSERTION_SORT("i", "Insertion Sort");

    private final String code;
    private final String fullName;

    AlgorithmType(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the full name of the algorithm from its short code.
     * @param code The short code of the algorithm.
     * @return The full name of the algorithm, or "Unknown" if not found.
     */
    public static String getFullNameByCode(String code) {
        return Arrays.stream(values())
            .filter(type -> type.getCode().equalsIgnoreCase(code))
            .findFirst()
            .map(AlgorithmType::getFullName)
            .orElse("Unknown");
    }
}

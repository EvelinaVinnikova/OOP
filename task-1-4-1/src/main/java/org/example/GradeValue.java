package org.example;

/**
 * Enum representing the standard academic grade values used in the Russian higher education system.
 * Each grade has a human-readable name (in Russian, as used in official documents)
 * and a corresponding numeric value for calculations (e.g., GPA).
 */
public enum GradeValue {

    /**
     * Excellent ("отлично") — the highest possible grade.
     * Numeric value: 5.
     * Required for red diploma eligibility and high scholarship consideration
     */
    EXCELLENT("отлично", 5),

    /**
     * Good ("хорошо") — a strong performance.
     * Numeric value: 4
     * Acceptable for most academic requirements, but not sufficient for red diploma
     */
    GOOD("хорошо", 4),

    /**
     * Satisfactory ("удовлетворительно") — minimum passing grade.
     * Numeric value: 3.
     * Presence of this grade on exams disqualifies a student from budget transfer.
     * Any occurrence prevents eligibility for a red diploma.
     */
    SATISFACTORY("удовлетворительно", 3),

    /**
     * Unsatisfactory ("неудовлетворительно") — failing grade.
     * Numeric value: 2.
     */
    UNSATISFACTORY("неудовлетворительно", 2);

    /**
     * The official Russian name of the grade as it appears in transcripts and diplomas.
     */
    private final String name;

    /**
     * The numeric equivalent of the grade used for average calculations.
     * Mapping: excellent → 5, good → 4, satisfactory → 3, unsatisfactory → 2.
     */
    private final int numericValue;

    /**
     * Constructs a grade value with its display name and numeric representation.
     *
     * @param name          the Russian textual name of the grade
     * @param numericValue  the integer value used in GPA and average computations
     */
    GradeValue(String name, int numericValue) {
        this.name = name;
        this.numericValue = numericValue;
    }

    /**
     * Returns the Russian name of this grade.
     * This name matches the terminology used in university transcripts and diplomas.
     *
     * @return the textual grade name (e.g., "отлично")
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the numeric value of this grade for computational purposes.
     *
     * @return the numeric value (5 for excellent, 4 for good, etc.)
     */
    public int getNumericValue() {
        return numericValue;
    }
}
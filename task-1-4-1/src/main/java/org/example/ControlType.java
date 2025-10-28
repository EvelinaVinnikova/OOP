package org.example;

/**
 * Enum representing types of academic assessment used in a student's grade book.
 * Each constant corresponds to a specific form of evaluation, such as exams, tests,
 * coursework, or thesis defense. This enum is used in the Grade class to
 * specify the nature of each recorded grade and influences business logic in
 * GradeBook (e.g., eligibility for budget transfer or honors diploma).
 */
public enum ControlType {

    /**
     * Final examination — a formal assessment typically held at the end of a course
     * graded on a scale (e.g., "satisfactory", "good", "excellent").
     * Exam grades are strictly evaluated when checking eligibility for transfer
     * to state-funded (budget) education and for a red diploma.
     */
    EXAM("Exam"),

    /**
     * Differentiated test — a type of assessment that results in a graded mark
     * (unlike a pass/fail test). While it uses the same grading scale as exams,
     * a "satisfactory" grade here is permitted when evaluating budget transfer eligibility.
     */
    DIFF_TEST("Differentiated test"),

    /**
     * Pass/fail test — a non-graded assessment where the outcome is typically
     * "credited" or "not credited". In this model, it is represented with a grade value
     * for uniformity, but may be excluded from average calculations or eligibility checks
     * depending on institutional policy.
     */
    TEST("Test"),

    /**
     * Coursework — a student's independent research or practical project,
     * submitted and defended during the semester, and evaluated with a standard grade.
     */
    COURSEWORK("Coursework"),

    /**
     * Practice report defense — assessment following an internship or practical training,
     * evaluated based on the submitted report and oral presentation.
     */
    PRACTICE_REPORT("Practice report defense"),

    /**
     * Bachelor's thesis defense — the final qualifying assessment for graduation.
     */
    BACHELOR_THESIS("Bachelor's thesis defense");

    /**
     * Human-readable description of the assessment type.
     */
    private final String description;

    /**
     * Constructs a ControlType constant with the given description.
     *
     * @param description a user-friendly label for the assessment type (e.g., "Exam")
     */
    ControlType(String description) {
        this.description = description;
    }

    /**
     * Returns the descriptive label of this assessment type.
     *
     * @return the description as a string (e.g., "Exam")
     */
    public String getDescription() {
        return description;
    }
}
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an electronic grade book for a student of the Faculty of Mechanics and Mathematics
 * This class stores all academic grades, manages the student's enrollment type (budget/fee-paying),
 * and provides methods to evaluate eligibility for:
 *   Transfer from fee-paying to budget education
 *   Receiving a red diploma
 *   Receiving an increased academic scholarship
 */
public class GradeBook {

    /**
     * The list of all recorded grades, ordered by addition.
     * Each grade includes subject, assessment type, value, and semester number.
     */
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Indicates whether the student is enrolled on a budget basis.
     */
    private boolean isBudget = false;

    /**
     * The grade received for the Bachelor's thesis (final qualifying work).
     */
    private GradeValue thesisGrade = null;

    /**
     * Adds a new grade to the grade book.
     * The grade is appended to the internal list and will be included in all subsequent calculations.
     *
     * @param grade the grade to add; must not be {@code null}
     * @throws NullPointerException if {@code grade} is {@code null}
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Sets the student's current enrollment type.
     *
     * @param budget {@code true} if the student is on a budget basis, {@code false} if fee-paying
     */
    public void setBudget(boolean budget) {
        isBudget = budget;
    }

    /**
     * Sets the grade for the Bachelor's thesis
     * This grade is used exclusively in the evaluation of eligibility for a red diploma.
     *
     * @param thesisGrade the grade received for the thesis; may be {@code null}
     */
    public void setThesisGrade(GradeValue thesisGrade) {
        this.thesisGrade = thesisGrade;
    }

    /**
     * Calculates the current cumulative grade point average across all recorded grades.
     * The numeric values of grades are mapped as follows:
     * "excellent" → 5, "good" → 4, "satisfactory" → 3, "unsatisfactory" → 2.
     * If no grades are recorded, the method returns {@code 0.0}.
     *
     * @return the average grade as a {@code double}; {@code 0.0} if the grade book is empty
     */
    public double getCurrentAverageGrade() {
        return grades.stream()
                .mapToInt(g -> g.getGradeValue().getNumericValue())
                .average()
                .orElse(0.0);
    }

    /**
     * Determines whether the student is eligible to transfer from fee-paying to budget education
     * Requirements:
     *   The student must currently be on a fee-paying basis
     *   There must be no "satisfactory" grades on {@link ControlType#EXAM exams}
     *   during the last two examination sessions (i.e., the two most recent semesters)
     *   "Satisfactory" grades on differentiated tests or other non-exam assessments are allowed
     * If the student is already on a budget, the method returns {@code true}.
     *
     * @return {@code true} if transfer is possible, {@code false} otherwise
     */
    public boolean canTransferToBudget() {
        if (isBudget) return true;

        int lastSemester = grades.stream()
                .mapToInt(Grade::getSemesterNumber)
                .max()
                .orElse(0);

        List<Grade> lastTwoSemestersGrades = grades.stream()
                .filter(g -> g.getSemesterNumber() >= lastSemester - 1)
                .collect(Collectors.toList());

        return lastTwoSemestersGrades.stream()
                .filter(g -> g.getControlType() == ControlType.EXAM)
                .noneMatch(g -> g.getGradeValue() == GradeValue.SATISFACTORY);
    }

    /**
     * Determines whether the student is on track to receive an honors ("red") diploma.
     * Requirements:
     *   The Bachelor's thesis grade must be {@link GradeValue#EXCELLENT}
     *   No grade (exam, test, or coursework) may be "satisfactory" or lower
     *   At least 75% of all recorded grades must be "excellent"
     * This method can be called at any point during studies to assess current eligibility.
     *
     * @return {@code true} if the student currently meets all criteria for an honors diploma,
     *         {@code false} otherwise
     */
    public boolean canGetRedDiploma() {
        if (thesisGrade != GradeValue.EXCELLENT) {
            return false;
        }

        if (grades.stream()
                .anyMatch(g -> g.getGradeValue() == GradeValue.SATISFACTORY)) {
            return false;
        }

        long totalGrades = grades.size();
        if (totalGrades == 0) return false;

        long excellentCount = grades.stream()
                .filter(g -> g.getGradeValue() == GradeValue.EXCELLENT)
                .count();

        return (double) excellentCount / totalGrades >= 0.75;
    }

    /**
     * Determines whether the student qualifies for an increased academic scholarship
     * in the current semester
     * Requirements:
     *   The current semester must contain at least one grade
     *   No grade in the current semester may be "satisfactory"
     *   The average grade in the current semester must be strictly greater than 4.5
     * The current semester is defined as the one with the highest semester number
     *
     * @return {@code true} if the student qualifies for an increased scholarship,
     *         {@code false} otherwise
     */
    public boolean canGetIncreasedScholarship() {
        int currentSemester = grades.stream()
                .mapToInt(Grade::getSemesterNumber)
                .max()
                .orElse(0);

        List<Grade> currentSemesterGrades = grades.stream()
                .filter(g -> g.getSemesterNumber() == currentSemester)
                .toList();

        if (currentSemesterGrades.isEmpty()) return false;

        if (currentSemesterGrades.stream()
                .anyMatch(g -> g.getGradeValue() == GradeValue.SATISFACTORY)) {
            return false;
        }

        double avg = currentSemesterGrades.stream()
                .mapToInt(g -> g.getGradeValue().getNumericValue())
                .average()
                .orElse(0.0);

        return avg > 4.5;
    }

    /**
     * Prints all recorded grades to the standard output (for debugging purposes only)
     */
    public void printAllGrades() {
        grades.forEach(System.out::println);
    }
}
package org.example;


/**
 * Represents a student enrolled at the Faculty of Mechanics and Mathematics.
 * Each student has a name and an electronic grade book that records all academic performance
 * The class provides convenient access to key academic status checks and metrics
 * by delegating to the internal grade book
 */
public class Student {

    /**
     * The full name of the student
     */
    private final String name;

    /**
     * The student's electronic grade book, which stores all grades, thesis result,
     * and enrollment type, and implements academic eligibility logic
     */
    private final GradeBook gradeBook;

    /**
     * Constructs a new student with the given name.
     * A new, empty grade book is automatically created for the student
     *
     * @param name the student's full name; must not be null
     */
    public Student(String name) {
        this.name = name;
        this.gradeBook = new GradeBook();
    }

    /**
     * Returns the student's full name
     *
     * @return the name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the student's grade book
     * The grade book contains all recorded grades and provides methods for academic evaluation
     *
     * @return the grade book instance associated with this student
     */
    public GradeBook getGradeBook() {
        return gradeBook;
    }

    /**
     * Calculates the student's current cumulative grade point average
     * The average is computed from all grades in the grade book using standard numeric equivalents
     * (excellent = 5, good = 4, satisfactory = 3, unsatisfactory = 2)
     * Returns 0.0 if no grades have been recorded
     *
     * @return the average grade as a double value
     */
    public double getCurrentAverageGrade() {
        return gradeBook.getCurrentAverageGrade();
    }

    /**
     * Checks whether the student is eligible to transfer from fee-paying to state-funded education
     * Eligibility requires that the student is currently fee-paying and has no "satisfactory" grades
     * on exams during the last two semesters. Differentiated tests with "satisfactory" are allowed
     *
     * @return true if transfer is possible, false otherwise
     */
    public boolean canTransferToBudget() {
        return gradeBook.canTransferToBudget();
    }

    /**
     * Checks whether the student currently meets all requirements for a red diploma
     * Requirements include: an "excellent" grade for the bachelor's thesis, no "satisfactory" grades
     * in any subject, and at least 75% of all grades being "excellent"
     *
     * @return true if the student qualifies for a red diploma, false otherwise
     */
    public boolean canGetRedDiploma() {
        return gradeBook.canGetRedDiploma();
    }

    /**
     * Checks whether the student qualifies for an increased academic scholarship this semester
     * Qualification requires that the current semester contains no "satisfactory" grades
     * and that the semester's average grade is strictly greater than 4.5
     *
     * @return true if the student qualifies for an increased scholarship, false otherwise
     */
    public boolean canGetIncreasedScholarship() {
        return gradeBook.canGetIncreasedScholarship();
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a string containing the student's name
     */
    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + '}';
    }
}

package org.example;

import java.util.Objects;

/**
 * Represents a single academic grade recorded in a student's electronic grade book.
 */
public class Grade {

    /**
     * The name of the academic subject (e.g., "Mathematical Analysis").
     */
    private final String subject;

    /**
     * The type of academic assessment (e.g., exam, test, coursework).
     *
     * @see ControlType
     */
    private final ControlType controlType;

    /**
     * The actual grade received, represented as a standardized value.
     *
     * @see GradeValue
     */
    private final GradeValue gradeValue;

    /**
     * The semester number (1-based) when the grade was awarded.
     * Used to identify the current semester or the last two examination sessions.
     */
    private final int semesterNumber;

    /**
     * Constructs a new {@code Grade} instance with the specified attributes.
     *
     * @param subject         the name of the subject; must not be {@code null}
     * @param controlType     the type of assessment; must not be {@code null}
     * @param gradeValue      the grade received; must not be {@code null}
     * @param semesterNumber  the semester number (positive integer)
     * @throws NullPointerException if {@code subject}, {@code controlType}, or {@code gradeValue} is {@code null}
     */
    public Grade(String subject, ControlType controlType, GradeValue gradeValue, int semesterNumber) {
        this.subject = subject;
        this.controlType = controlType;
        this.gradeValue = gradeValue;
        this.semesterNumber = semesterNumber;
    }

    /**
     * Returns the name of the subject associated with this grade.
     *
     * @return the subject name (e.g., "Algebra")
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the type of academic assessment for this grade.
     *
     * @return the control type (e.g., {@link ControlType#EXAM})
     */
    public ControlType getControlType() {
        return controlType;
    }

    /**
     * Returns the grade value assigned for this assessment.
     *
     * @return the grade value (e.g., {@link GradeValue#EXCELLENT})
     */
    public GradeValue getGradeValue() {
        return gradeValue;
    }

    /**
     * Returns the semester number in which this grade was awarded.
     * Semester numbering starts at 1 (first semester of study).
     *
     * @return the semester number (a positive integer)
     */
    public int getSemesterNumber() {
        return semesterNumber;
    }

    /**
     * Compares this grade to another object for equality.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return semesterNumber == grade.semesterNumber &&
                Objects.equals(subject, grade.subject) &&
                controlType == grade.controlType &&
                gradeValue == grade.gradeValue;
    }

    /**
     * Returns a hash code value for this grade.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(subject, controlType, gradeValue, semesterNumber);
    }

    /**
     * Returns a string representation of this grade for debugging and logging.
     *
     * @return a string representation of this grade
     */
    @Override
    public String toString() {
        return "Grade{" +
                "subject='" + subject + '\'' +
                ", controlType=" + controlType +
                ", gradeValue=" + gradeValue +
                ", semesterNumber=" + semesterNumber +
                '}';
    }
}
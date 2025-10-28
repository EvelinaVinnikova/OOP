import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.GradeBook;
import org.example.ControlType;
import org.example.Grade;
import org.example.GradeValue;
import org.junit.jupiter.api.Test;


class GradeBookScholarshipTest {


    @Test
    void canGetIncreasedScholarship() {
        //returns True when Current Semester Has High Average And No Satisfactory
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.EXCELLENT, 3)); // 5
        book.addGrade(new Grade("Prog", ControlType.EXAM, GradeValue.EXCELLENT, 3)); // 5

        assertTrue(book.canGetIncreasedScholarship());
    }

    @Test
    void canGetIncreasedScholarship_returnsFalse_whenSatisfactoryInCurrentSemester() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.SATISFACTORY, 2));

        assertFalse(book.canGetIncreasedScholarship());
    }

    @Test
    void canGetIncreasedScholarship_returnsFalse_whenAverageIs4_5_orLess() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("A", ControlType.EXAM, GradeValue.GOOD, 2)); // 4
        book.addGrade(new Grade("B", ControlType.EXAM, GradeValue.GOOD, 2)); // 4 → avg = 4.0

        assertFalse(book.canGetIncreasedScholarship());
    }

    @Test
    void canGetIncreasedScholarship_ignoresPreviousSemesters() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Old", ControlType.EXAM, GradeValue.SATISFACTORY, 1));
        book.addGrade(new Grade("New", ControlType.EXAM, GradeValue.EXCELLENT, 2));

        assertTrue(book.canGetIncreasedScholarship());
    }

    @Test
    void canGetIncreasedScholarship_returnsFalse_whenNoGradesInCurrentSemester() {
        GradeBook book = new GradeBook();
        assertFalse(book.canGetIncreasedScholarship());
    }
}
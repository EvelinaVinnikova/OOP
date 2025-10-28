import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.GradeBook;
import org.example.GradeValue;
import org.example.Grade;
import org.example.ControlType;

import org.junit.jupiter.api.Test;


class GradeBookRedDiplomaTest {

    @Test
    void canGetRedDiploma_returnsTrue_whenAllConditionsMet() {
        GradeBook book = new GradeBook();
        book.setThesisGrade(GradeValue.EXCELLENT);

        book.addGrade(new Grade("A", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("B", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("C", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("D", ControlType.EXAM, GradeValue.GOOD, 1)); // 3/4 = 75%

        assertTrue(book.canGetRedDiploma());
    }

    @Test
    void canGetRedDiploma_returnsFalse_whenThesisNotExcellent() {
        GradeBook book = new GradeBook();
        book.setThesisGrade(GradeValue.GOOD);
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.EXCELLENT, 1));

        assertFalse(book.canGetRedDiploma());
    }

    @Test
    void canGetRedDiploma_returnsFalse_whenAnySatisfactoryExists() {
        GradeBook book = new GradeBook();
        book.setThesisGrade(GradeValue.EXCELLENT);
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.SATISFACTORY, 1));

        assertFalse(book.canGetRedDiploma());
    }

    @Test
    void canGetRedDiploma_returnsFalse_whenLessThan75PercentExcellent() {
        GradeBook book = new GradeBook();
        book.setThesisGrade(GradeValue.EXCELLENT);
        book.addGrade(new Grade("A", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("B", ControlType.EXAM, GradeValue.GOOD, 1));
        book.addGrade(new Grade("C", ControlType.EXAM, GradeValue.GOOD, 1)); // 1/3 ≈ 33%

        assertFalse(book.canGetRedDiploma());
    }

    @Test
    void canGetRedDiploma_returnsTrue_whenExactly75PercentExcellent() {
        GradeBook book = new GradeBook();
        book.setThesisGrade(GradeValue.EXCELLENT);
        book.addGrade(new Grade("1", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("2", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("3", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("4", ControlType.EXAM, GradeValue.GOOD, 1));

        assertTrue(book.canGetRedDiploma());
    }
}
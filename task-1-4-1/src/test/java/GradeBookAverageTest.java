import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.ControlType;
import org.example.Grade;
import org.example.GradeBook;
import org.example.GradeValue;
import org.junit.jupiter.api.Test;


class GradeBookAverageTest {

    @Test
    void getCurrentAverageGrade_returnsCorrectAverage_forMixedGrades() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.EXCELLENT, 1)); // 5
        book.addGrade(new Grade("Physics", ControlType.EXAM, GradeValue.GOOD, 1));   // 4
        book.addGrade(new Grade("History", ControlType.TEST, GradeValue.SATISFACTORY, 1)); // 3

        assertEquals(4.0, book.getCurrentAverageGrade(), 0.01);
    }

    @Test
    void getCurrentAverageGrade_returnsZero_whenNoGrades() {
        GradeBook book = new GradeBook();
        assertEquals(0.0, book.getCurrentAverageGrade(), 0.01);
    }

    @Test
    void getCurrentAverageGrade_handlesOnlyExcellentGrades() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("A", ControlType.EXAM, GradeValue.EXCELLENT, 1));
        book.addGrade(new Grade("B", ControlType.EXAM, GradeValue.EXCELLENT, 2));

        assertEquals(5.0, book.getCurrentAverageGrade(), 0.01);
    }
}
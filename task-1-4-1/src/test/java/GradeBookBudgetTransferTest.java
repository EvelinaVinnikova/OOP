import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.ControlType;
import org.example.Grade;
import org.example.GradeBook;
import org.example.GradeValue;
import org.junit.jupiter.api.Test;


class GradeBookBudgetTransferTest {

    @Test
    void canTransferToBudget_returnsTrue_whenNoSatisfactoryOnExamsInLastTwoSemesters() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Algebra", ControlType.EXAM, GradeValue.GOOD, 3));
        book.addGrade(new Grade("Analysis", ControlType.EXAM, GradeValue.EXCELLENT, 4));
        book.addGrade(new Grade("PE", ControlType.DIFF_TEST, GradeValue.SATISFACTORY, 4));

        assertTrue(book.canTransferToBudget());
    }

    @Test
    void canTransferToBudget_returnsFalse_whenSatisfactoryOnExamInLastTwoSemesters() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.SATISFACTORY, 4));

        assertFalse(book.canTransferToBudget());
    }

    @Test
    void canTransferToBudget_ignoresOldSatisfactoryExams() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Old Math", ControlType.EXAM, GradeValue.SATISFACTORY, 1));
        book.addGrade(new Grade("New Math", ControlType.EXAM, GradeValue.EXCELLENT, 4));
        book.addGrade(new Grade("Physics", ControlType.EXAM, GradeValue.GOOD, 3));

        assertTrue(book.canTransferToBudget());
    }

    @Test
    void canTransferToBudget_returnsTrue_whenOnlyOneSemesterExists() {
        GradeBook book = new GradeBook();
        book.addGrade(new Grade("Math", ControlType.EXAM, GradeValue.GOOD, 1));

        assertTrue(book.canTransferToBudget());
    }
}

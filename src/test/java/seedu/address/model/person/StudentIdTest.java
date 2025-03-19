package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidPhone));
    }

    @Test
    public void isValidStudentId() {
        // null student id
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid student id
        assertFalse(StudentId.isValidStudentId(""));
        assertFalse(StudentId.isValidStudentId(" "));
        assertFalse(StudentId.isValidStudentId("12345678"));
        assertFalse(StudentId.isValidStudentId("+12345678H"));
        assertFalse(StudentId.isValidStudentId("A12345678H")); // excess numbers
        assertFalse(StudentId.isValidStudentId("phone123s"));
        assertFalse(StudentId.isValidStudentId("a1234567B")); // starts with small letter a
        assertFalse(StudentId.isValidStudentId("A1234567b")); // ends with small letter
        assertFalse(StudentId.isValidStudentId("a1234567b")); // starts and ends with small letter
        assertFalse(StudentId.isValidStudentId("b1234567b")); // starts with wrong letter
        assertFalse(StudentId.isValidStudentId("A123 456H")); // spaces within digits

        // valid student id
        assertTrue(StudentId.isValidStudentId("A1234567B"));
        assertTrue(StudentId.isValidStudentId("A7654321F"));
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("A1234567B");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("A1234567B")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("A1234567C")));
    }
}

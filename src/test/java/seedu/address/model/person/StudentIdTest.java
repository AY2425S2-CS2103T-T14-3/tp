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
        // null phone number
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid phone numbers
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("91")); // less than 3 numbers
        assertFalse(StudentId.isValidStudentId("phone")); // non-numeric
        assertFalse(StudentId.isValidStudentId("9011p041")); // alphabets within digits
        assertFalse(StudentId.isValidStudentId("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(StudentId.isValidStudentId("911")); // exactly 3 numbers
        assertTrue(StudentId.isValidStudentId("93121534"));
        assertTrue(StudentId.isValidStudentId("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("999");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("999")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("995")));
    }
}

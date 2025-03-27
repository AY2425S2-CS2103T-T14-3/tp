package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassId(null));
    }

    @Test
    public void constructor_invalidClassId_throwsIllegalArgumentException() {
        String invalidClassId = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassId(invalidClassId));
    }

    @Test
    public void isValidClassId() {
        // null class id
        assertThrows(NullPointerException.class, () -> ClassId.isValidClassId(null));

        // invalid class ids
        assertFalse(ClassId.isValidClassId("")); // empty string
        assertFalse(ClassId.isValidClassId(" ")); // spaces only

        // valid class ids
        assertTrue(ClassId.isValidClassId("AB1234-56")); // 2 letters, 4 digits, dash, 2 digits
        assertTrue(ClassId.isValidClassId("ABC1234-56")); // 3 letters, 4 digits, dash, 2 digits
        assertTrue(ClassId.isValidClassId("AB1234A-56")); // with optional letter
        assertTrue(ClassId.isValidClassId("ABC1234Z-56")); // 3 letters with optional letter
        assertTrue(ClassId.isValidClassId("ab1234-56")); // lowercase letters
        assertTrue(ClassId.isValidClassId("Ab1234c-56")); // mixed case
    }

    @Test
    public void equals() {
        ClassId classId = new ClassId("AB1234-56");

        // same values -> returns true
        assertTrue(classId.equals(new ClassId("AB1234-56")));

        // same object -> returns true
        assertTrue(classId.equals(classId));

        // null -> returns false
        assertFalse(classId.equals(null));

        // different types -> returns false
        assertFalse(classId.equals(5.0f));

        // different values -> returns false
        assertFalse(classId.equals(new ClassId("CD5678-90")));
    }
}

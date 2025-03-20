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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassId(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> ClassId.isValidClassId(null));

        // invalid addresses
        assertFalse(ClassId.isValidClassId("")); // empty string
        assertFalse(ClassId.isValidClassId(" ")); // spaces only

        // valid addresses
        assertTrue(ClassId.isValidClassId("Blk 456, Den Road, #01-355"));
        assertTrue(ClassId.isValidClassId("-")); // one character
        assertTrue(ClassId.isValidClassId("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        ClassId classId = new ClassId("Valid Address");

        // same values -> returns true
        assertTrue(classId.equals(new ClassId("Valid Address")));

        // same object -> returns true
        assertTrue(classId.equals(classId));

        // null -> returns false
        assertFalse(classId.equals(null));

        // different types -> returns false
        assertFalse(classId.equals(5.0f));

        // different values -> returns false
        assertFalse(classId.equals(new ClassId("Other Valid Address")));
    }
}

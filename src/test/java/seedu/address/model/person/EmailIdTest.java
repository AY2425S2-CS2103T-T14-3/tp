package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmailId(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new EmailId(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> EmailId.isValidEmailId(null));

        // blank email
        assertFalse(EmailId.isValidEmailId("")); // empty string
        assertFalse(EmailId.isValidEmailId(" ")); // spaces only

        // missing parts
        assertFalse(EmailId.isValidEmailId("E")); // missing digits
        assertFalse(EmailId.isValidEmailId("1234567")); // missing 'E' symbol
        assertFalse(EmailId.isValidEmailId("E123")); // incorrect number of digits

        // invalid parts
        assertFalse(EmailId.isValidEmailId("E@234567")); // invalid character '@'
        assertFalse(EmailId.isValidEmailId("E1_34567")); // invalid character '_'
        assertFalse(EmailId.isValidEmailId("E123.567")); // invalid character '.'
        assertFalse(EmailId.isValidEmailId("E12345-7")); // invalid character "-"
        assertFalse(EmailId.isValidEmailId(" E1234567")); // leading space
        assertFalse(EmailId.isValidEmailId("E1234567 ")); // trailing space
        assertFalse(EmailId.isValidEmailId("1E234567")); // E is not the first character
        assertFalse(EmailId.isValidEmailId("E1234567@example.com")); // an actual email

        // valid email id
        assertTrue(EmailId.isValidEmailId("E1234567")); // E plus 7 digits
        assertTrue(EmailId.isValidEmailId("E2234567")); // E plus 7 digits
        assertTrue(EmailId.isValidEmailId("E0000000")); // E plus 7 digits
        assertTrue(EmailId.isValidEmailId("E1122475")); // E plus 7 digits
        assertTrue(EmailId.isValidEmailId("E5555555")); // E plus 7 digits
        assertTrue(EmailId.isValidEmailId("E9876543")); // E plus 7 digits
    }

    @Test
    public void equals() {
        EmailId emailId = new EmailId("E1234567");

        // same values -> returns true
        assertTrue(emailId.equals(new EmailId("E1234567")));

        // same object -> returns true
        assertTrue(emailId.equals(emailId));

        // null -> returns false
        assertFalse(emailId.equals(null));

        // different types -> returns false
        assertFalse(emailId.equals(5.0f));

        // different values -> returns false
        assertFalse(emailId.equals(new EmailId("E2234567")));
    }
}

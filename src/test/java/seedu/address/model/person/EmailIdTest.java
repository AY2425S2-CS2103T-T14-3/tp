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
        assertThrows(NullPointerException.class, () -> EmailId.isValidEmail(null));

        // blank email
        assertFalse(EmailId.isValidEmail("")); // empty string
        assertFalse(EmailId.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(EmailId.isValidEmail("@example.com")); // missing local part
        assertFalse(EmailId.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(EmailId.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(EmailId.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(EmailId.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(EmailId.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(EmailId.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(EmailId.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(EmailId.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(EmailId.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(EmailId.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(EmailId.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(EmailId.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(EmailId.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(EmailId.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(EmailId.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(EmailId.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(EmailId.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(EmailId.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(EmailId.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(EmailId.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(EmailId.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(EmailId.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(EmailId.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(EmailId.isValidEmail("a@bc")); // minimal
        assertTrue(EmailId.isValidEmail("test@localhost")); // alphabets only
        assertTrue(EmailId.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(EmailId.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(EmailId.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(EmailId.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(EmailId.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        EmailId emailId = new EmailId("valid@email");

        // same values -> returns true
        assertTrue(emailId.equals(new EmailId("valid@email")));

        // same object -> returns true
        assertTrue(emailId.equals(emailId));

        // null -> returns false
        assertFalse(emailId.equals(null));

        // different types -> returns false
        assertFalse(emailId.equals(5.0f));

        // different values -> returns false
        assertFalse(emailId.equals(new EmailId("other.valid@email")));
    }
}

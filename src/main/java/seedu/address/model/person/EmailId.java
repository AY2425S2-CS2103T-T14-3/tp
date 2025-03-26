package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's email id in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmailId(String)}
 */
public class EmailId {
    public static final String MESSAGE_CONSTRAINTS =
            "EmailId should follow the format: Exxxxxxx, with 7 x's, where each x is a number";
    public static final String VALIDATION_REGEX = "E\\d{7}";
    public static final String EMAIL_SUFFIX = "@u.nus.edu";
    public final String value;

    /**
     * Constructs an {@code EmailId}.
     *
     * @param emailId A valid email id.
     */
    public EmailId(String emailId) {
        requireNonNull(emailId);
        checkArgument(isValidEmailId(emailId), MESSAGE_CONSTRAINTS);
        value = emailId;
    }

    /**
     * Returns if a given string is a valid email.
     *
     * @param test The string to be checked for validity.
     */
    public static boolean isValidEmailId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value + EMAIL_SUFFIX;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailId)) {
            return false;
        }

        EmailId otherEmailId = (EmailId) other;
        return value.equals(otherEmailId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

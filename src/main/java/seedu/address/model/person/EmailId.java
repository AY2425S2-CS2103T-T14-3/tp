package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an NUS student's email ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmailId(String)}
 */
public class EmailId {
    public static final String MESSAGE_CONSTRAINTS = "Email IDs should be of the format /e E1136951 "
            + "and adhere to the following constraints:\n"
            + "1. The first letter must be a capital E. \n"
            + "2. This is followed by 7 consecutive digits that range from 0-9.";
    public static final String VALIDATION_REGEX = "E\\d{7}";
    public final String value;

    /**
     * Constructs an {@code EmailId}.
     *
     * @param emailId A valid email ID.
     */
    public EmailId(String emailId) {
        requireNonNull(emailId);
        checkArgument(isValidEmailId(emailId), MESSAGE_CONSTRAINTS);
        value = emailId;
    }

    /**
     * Returns if a given string is a valid emailId.
     */
    public static boolean isValidEmailId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
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

        EmailId otherEmail = (EmailId) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

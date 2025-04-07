package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's class id in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassId(String)}
 */
public class ClassId {

public static final String MESSAGE_CONSTRAINTS = "ClassId does not accept empty inputs,"
            + " and is maximally 16 characters long ";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{0,15}";

    public final String value;

    /**
     * Constructs a {@code ClassId}.
     *
     * @param classId A valid class id.
     */
    public ClassId(String classId) {
        requireNonNull(classId);
        checkArgument(isValidClassId(classId), MESSAGE_CONSTRAINTS);
        value = classId;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidClassId(String test) {
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
        if (!(other instanceof ClassId)) {
            return false;
        }

        ClassId otherClassId = (ClassId) other;
        return value.equals(otherClassId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

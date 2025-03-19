package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's student id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    // todo: deal with test cases

    public static final String MESSAGE_CONSTRAINTS =
            "StudentId should follow the format: AxxxxxxxX, where x is a number and X is a capital letter.";
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";
    public final String value;


    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid studentId number.
     */
    public StudentId(String studentId) {
        studentId.trim();
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidStudentId(String test) {
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
        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherStudentId = (StudentId) other;
        return value.equals(otherStudentId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's student id in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid command format! \n"
                    + "edit: Edits the details of the person identified by the the student's student ID. "
                    + "Existing values will be overwritten by the input values.\n"
                    + "Parameters: Student ID [n/NAME] [i/STUDENT ID] [e/EMAIL ID] [c/CLASS ID] [t/TAG]...\n"
                    + "Example: edit 1 i/91234567 e/johndoe@example.com";
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";
    public final String value;


    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid studentId number.
     */
    public StudentId(String studentId) {
        String trimmedStudentId = studentId.trim();
        requireNonNull(trimmedStudentId);
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

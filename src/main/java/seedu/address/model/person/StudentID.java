package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * This class represents the student id of a number, which starts with 'A'.
 * E.g. 'A1234123B' is a possible student number.
 */
public class StudentID {

    private String id;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param id A valid student ID.
     */
    public StudentID(String id) {
        requireNonNull(id);
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

}

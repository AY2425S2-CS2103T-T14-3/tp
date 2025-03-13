package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a class a student is in. A class is identified by an integer id.
 */
public class ClassID {
    private String classID;

    /**
     * Constructs a {@code ClassID}.
     *
     * @param classID A valid classID string
     */
    public ClassID(String classID) {
        requireNonNull(classID);
        this.classID = classID;
    }

    @Override
    public String toString() {
        return classID;
    }

}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a class a student is in. A class is identified by an integer id.
 */
public class ClassID {
    String classID;

    public ClassID(String classID) {
        requireNonNull(classID);
        this.classID = classID;
    }

    @Override
    public String toString() {
        return classID;
    }
}
package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code ClassId} matches the given class id.
 */
public class StudentHasSameIdPredicate implements Predicate<Person> {
    private final String classId;

    public StudentHasSameIdPredicate(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean test(Person person) {
        String idToTest = person.getAddress().value.toLowerCase();
        String classIdCopy = classId.toLowerCase();
        return classIdCopy.equals(idToTest);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentHasSameIdPredicate)) {
            return false;
        }

        StudentHasSameIdPredicate otherStudentHasSameIdPredicate = (StudentHasSameIdPredicate) other;
        return classId.equals(otherStudentHasSameIdPredicate.classId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("classId", classId).toString();
    }
}

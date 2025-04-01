package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code ClassId} matches the given class id.
 */
public class StudentHasSameClassIdPredicate implements Predicate<Person> {
    private final String classId;

    public StudentHasSameClassIdPredicate(String classId) {
        this.classId = classId.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String idToTest = person.getClassId().value.toLowerCase();
        String classIdCopy = classId.toLowerCase();
        return classIdCopy.equals(idToTest);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentHasSameClassIdPredicate)) {
            return false;
        }

        StudentHasSameClassIdPredicate otherStudentHasSameClassIdPredicate = (StudentHasSameClassIdPredicate) other;
        return classId.equals(otherStudentHasSameClassIdPredicate.classId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("classId", classId).toString();
    }
}

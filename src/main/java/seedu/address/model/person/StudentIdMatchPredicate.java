package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class StudentIdMatchPredicate implements Predicate<Person> {
    private final StudentId studentId;

    public StudentIdMatchPredicate(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean test(Person person) {
        return person.getStudentId().equals(studentId);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentIdMatchPredicate)) {
            return false;
        }

        StudentIdMatchPredicate otherStudentIdMatchPredicate = (StudentIdMatchPredicate) other;
        return studentId.equals(otherStudentIdMatchPredicate.studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("studentId", studentId).toString();
    }
}

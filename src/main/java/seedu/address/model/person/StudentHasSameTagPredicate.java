package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code ClassId} matches the given class id.
 */
public class StudentHasSameTagPredicate implements Predicate<Person> {
    private final String tag;

    public StudentHasSameTagPredicate(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String tagToTest = person.getClassId().value.toLowerCase();
        String tagCopy = tag.toLowerCase();
        return tagCopy.equals(tagToTest);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentHasSameTagPredicate)) {
            return false;
        }

        StudentHasSameTagPredicate otherStudentHasSameTagPredicate = (StudentHasSameTagPredicate) other;
        return tag.equals(otherStudentHasSameTagPredicate.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tag", tag).toString();
    }
}

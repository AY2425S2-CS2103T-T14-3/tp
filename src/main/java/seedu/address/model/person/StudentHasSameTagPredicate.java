package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the given class id.
 */
public class StudentHasSameTagPredicate implements Predicate<Person> {
    private final Tag tag;

    public StudentHasSameTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        String searchTag = tag.toString().toLowerCase();
        return person.getTags().stream()
                .anyMatch(t -> t.toString().toLowerCase().equals(searchTag));
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

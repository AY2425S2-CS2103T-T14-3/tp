package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class StudentHasSameTagPredicateTest {

    @Test
    public void equals() {
        // same tag -> returns true
        Tag testTag = new Tag("NeedHelp");
        StudentHasSameTagPredicate firstPredicate = new StudentHasSameTagPredicate(testTag);
        StudentHasSameTagPredicate secondPredicate = new StudentHasSameTagPredicate(testTag);
        assertTrue(firstPredicate.equals(secondPredicate));

        // different tags -> returns false
        Tag secondTestTag = new Tag("NoSubmission");
        StudentHasSameTagPredicate thirdPredicate = new StudentHasSameTagPredicate(secondTestTag);
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // Null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_tagMatches_returnsTrue() {
        // matching tags
        Tag firstTestTag = new Tag("LateSubmission");
        StudentHasSameTagPredicate predicate = new StudentHasSameTagPredicate(firstTestTag);
        assertTrue(predicate.test(new PersonBuilder().withTags("LateSubmission").build()));

        // matching tags but with different case
        Tag secondTestTag = new Tag("latesubmission");
        predicate = new StudentHasSameTagPredicate(secondTestTag);
        assertTrue(predicate.test(new PersonBuilder().withTags("LateSubmission").build()));
    }

    @Test
    public void test_tagDoesNotMatch_returnsFalse() {
        // non-matching tags
        Tag firstTestTag = new Tag("NeedHelp");
        StudentHasSameTagPredicate predicate = new StudentHasSameTagPredicate(firstTestTag);
        assertFalse(predicate.test(new PersonBuilder().withTags("NoSubmission").build()));

        // non-matching with different case
        assertFalse(predicate.test(new PersonBuilder().withTags("nosubmission").build()));
    }

    @Test
    public void toStringMethod() {
        Tag firstTestTag = new Tag("NeedHelp");
        StudentHasSameTagPredicate predicate = new StudentHasSameTagPredicate(firstTestTag);
        String expected = StudentHasSameTagPredicate.class.getCanonicalName() + "{tag=[NeedHelp]}";
        assertEquals(expected, predicate.toString());
    }
}

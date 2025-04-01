package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentHasSameClassIdPredicateTest {

    @Test
    public void equals() {
        // Same class id -> returns true
        StudentHasSameClassIdPredicate firstPredicate = new StudentHasSameClassIdPredicate("CS323019");
        StudentHasSameClassIdPredicate secondPredicate = new StudentHasSameClassIdPredicate("CS323019");
        assertTrue(firstPredicate.equals(secondPredicate));

        // different class ids -> returns false
        StudentHasSameClassIdPredicate thirdPredicate = new StudentHasSameClassIdPredicate("CS1101S");
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // Null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_classIdMatches_returnsTrue() {
        // matching class ids
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate("CS323019");
        assertTrue(predicate.test(new PersonBuilder().withClassId("CS323019").build()));

        // matching class ids but with different case
        predicate = new StudentHasSameClassIdPredicate("cs323019");
        assertTrue(predicate.test(new PersonBuilder().withClassId("CS323019").build()));
    }

    @Test
    public void test_classIdDoesNotMatch_returnsFalse() {
        // non-matching class ids
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate("CS323019");
        assertFalse(predicate.test(new PersonBuilder().withClassId("CS1101S").build()));

        // non-matching with different case
        predicate = new StudentHasSameClassIdPredicate("CS323019");
        assertFalse(predicate.test(new PersonBuilder().withClassId("cs1101s").build()));
    }

    @Test
    public void toStringMethod() {
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate("CS323019");
        String expected = StudentHasSameClassIdPredicate.class.getCanonicalName() + "{classId=cs323019}";
        assertEquals(expected, predicate.toString());
    }
}

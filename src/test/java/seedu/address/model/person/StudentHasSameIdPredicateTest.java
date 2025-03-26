package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentHasSameIdPredicateTest {

    @Test
    public void equals() {
        // Same class id -> returns true
        StudentHasSameIdPredicate firstPredicate = new StudentHasSameIdPredicate("CS323019");
        StudentHasSameIdPredicate secondPredicate = new StudentHasSameIdPredicate("CS323019");
        assertTrue(firstPredicate.equals(secondPredicate));

        // different class ids -> returns false
        StudentHasSameIdPredicate thirdPredicate = new StudentHasSameIdPredicate("CS1101S");
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // Null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_classIdMatches_returnsTrue() {
        // matching class ids
        StudentHasSameIdPredicate predicate = new StudentHasSameIdPredicate("CS323019");
        assertTrue(predicate.test(new PersonBuilder().withAddress("CS323019").build()));

        // matching class ids but with different case
        predicate = new StudentHasSameIdPredicate("cs323019");
        assertTrue(predicate.test(new PersonBuilder().withAddress("CS323019").build()));
    }

    @Test
    public void test_classIdDoesNotMatch_returnsFalse() {
        // non-matching class ids
        StudentHasSameIdPredicate predicate = new StudentHasSameIdPredicate("CS323019");
        assertFalse(predicate.test(new PersonBuilder().withAddress("CS1101S").build()));

        // non-matching with different case
        predicate = new StudentHasSameIdPredicate("CS323019");
        assertFalse(predicate.test(new PersonBuilder().withAddress("cs1101s").build()));
    }

    @Test
    public void toStringMethod() {
        StudentHasSameIdPredicate predicate = new StudentHasSameIdPredicate("CS323019");
        String expected = StudentHasSameIdPredicate.class.getCanonicalName() + "{classId=cs323019}";
        assertEquals(expected, predicate.toString());
    }
}

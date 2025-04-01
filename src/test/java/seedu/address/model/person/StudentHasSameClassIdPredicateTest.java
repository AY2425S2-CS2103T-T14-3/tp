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
        ClassId testId = new ClassId("CS323019");
        StudentHasSameClassIdPredicate firstPredicate = new StudentHasSameClassIdPredicate(testId);
        StudentHasSameClassIdPredicate secondPredicate = new StudentHasSameClassIdPredicate(testId);
        assertTrue(firstPredicate.equals(secondPredicate));

        // different class ids -> returns false
        ClassId secondTestId = new ClassId("CS1101S");
        StudentHasSameClassIdPredicate thirdPredicate = new StudentHasSameClassIdPredicate(secondTestId);
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // Null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_classIdMatches_returnsTrue() {
        // matching class ids
        ClassId firstTestId = new ClassId("CS210205");
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate(firstTestId);
        assertTrue(predicate.test(new PersonBuilder().withClassId("CS210205").build()));

        // matching class ids but with different case
        ClassId secondTestId = new ClassId("cs210205");
        predicate = new StudentHasSameClassIdPredicate(secondTestId);
        assertTrue(predicate.test(new PersonBuilder().withClassId("CS210205").build()));
    }

    @Test
    public void test_classIdDoesNotMatch_returnsFalse() {
        // non-matching class ids
        ClassId firstTestId = new ClassId("CS210509");
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate(firstTestId);
        assertFalse(predicate.test(new PersonBuilder().withClassId("CS323010").build()));

        // non-matching with different case
        assertFalse(predicate.test(new PersonBuilder().withClassId("cs323010").build()));
    }

    @Test
    public void toStringMethod() {
        ClassId firstTestId = new ClassId("CS2030S");
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate(firstTestId);
        String expected = StudentHasSameClassIdPredicate.class.getCanonicalName() + "{classId=CS2030S}";
        assertEquals(expected, predicate.toString());
    }
}

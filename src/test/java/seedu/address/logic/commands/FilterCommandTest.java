package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalWhoDat;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentHasSameClassIdPredicate;
import seedu.address.model.person.StudentHasSameTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalWhoDat(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWhoDat(), new UserPrefs());

    @Test
    public void equals_classIdPredicate() {
        ClassId firstClassIdPredicate = new ClassId("CS1101S03");
        ClassId secondClassIdPredicate = new ClassId("CS2040S");
        StudentHasSameClassIdPredicate firstPredicate = new StudentHasSameClassIdPredicate(firstClassIdPredicate);
        StudentHasSameClassIdPredicate secondPredicate = new StudentHasSameClassIdPredicate(secondClassIdPredicate);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different class id's-> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_emptyId_noIdFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        // a class id that doesn't exist
        ClassId wrongClassId = new ClassId("noexist");
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate(wrongClassId);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneId_twoPersonsFound() {
        // both people share same class id
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        Person james = new PersonBuilder().withName("James").withStudentId("A7777777F")
                .withEmailId("E1230495").withClassId("CS210501").build();
        Person raj = new PersonBuilder().withName("Rajaratnam").withStudentId("A4444444G")
                .withEmailId("E5938475").withClassId("CS210501").build();
        model.addPerson(james);
        model.addPerson(raj);
        expectedModel.addPerson(james);
        expectedModel.addPerson(raj);
        ClassId testPredicate = new ClassId("CS210501");
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate(testPredicate);
        FilterCommand command = new FilterCommand(predicate);
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void toString_classId() {
        ClassId testClassId = new ClassId("CS210605");
        StudentHasSameClassIdPredicate predicate = new StudentHasSameClassIdPredicate((testClassId));
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    @Test
    public void toString_tag() {
        Tag testTag = new Tag("NeedHelp");
        StudentHasSameTagPredicate predicate = new StudentHasSameTagPredicate(testTag);
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    @Test
    public void equals_tagPredicate() {
        Tag firstTag = new Tag("Friend");
        Tag secondTag = new Tag("Colleague");
        StudentHasSameTagPredicate firstPredicate = new StudentHasSameTagPredicate(firstTag);
        StudentHasSameTagPredicate secondPredicate = new StudentHasSameTagPredicate(secondTag);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different tags -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_invalidTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        // tag doesn't exist
        Tag emptyTag = new Tag("noexist");
        StudentHasSameTagPredicate predicate = new StudentHasSameTagPredicate(emptyTag);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_tagFiltering_twoPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        // same tag but different cases
        Person choony = new PersonBuilder().withName("Benny").withStudentId("A7777777F")
                .withEmailId("E1230495").withClassId("CS210501").withTags("Friend").build();
        Person benny = new PersonBuilder().withName("Choony").withStudentId("A4444444G")
                .withEmailId("E5938475").withClassId("CS210501").withTags("fRieND").build();
        model.addPerson(choony);
        model.addPerson(benny);
        expectedModel.addPerson(choony);
        expectedModel.addPerson(benny);
        Tag filterTag = new Tag("friend");
        StudentHasSameTagPredicate predicate = new StudentHasSameTagPredicate(filterTag);
        FilterCommand command = new FilterCommand(predicate);
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}

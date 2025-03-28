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
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentHasSameIdPredicate;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalWhoDat(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWhoDat(), new UserPrefs());

    @Test
    public void equals() {
        StudentHasSameIdPredicate firstPredicate = new StudentHasSameIdPredicate("CS1101S03");
        StudentHasSameIdPredicate secondPredicate = new StudentHasSameIdPredicate("CS2040S");

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
        // A class id that doesn't exist
        StudentHasSameIdPredicate predicate = new StudentHasSameIdPredicate("");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneId_twoPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        StudentHasSameIdPredicate predicate = new StudentHasSameIdPredicate("CS210501");
        Person james = new PersonBuilder().withName("James").withStudentId("A7777777F")
                .withEmailId("E1230495").withClassId("CS210501").build();
        Person raj = new PersonBuilder().withName("Rajaratnam").withStudentId("A4444444G")
                .withEmailId("E5938475").withClassId("CS210501").build();
        expectedModel.updateFilteredPersonList(predicate);
        model.addPerson(james);
        model.addPerson(raj);
        expectedModel.addPerson(james);
        expectedModel.addPerson(raj);
        FilterCommand command = new FilterCommand(predicate);
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        StudentHasSameIdPredicate predicate = new StudentHasSameIdPredicate(("CS210605"));
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}

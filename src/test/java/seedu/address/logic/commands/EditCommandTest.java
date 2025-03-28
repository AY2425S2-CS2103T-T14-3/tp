package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalPersons.getTypicalWhoDat;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.WhoDat;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalWhoDat(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(STUDENT_ID_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new WhoDat(model.getWhoDat()), new UserPrefs());
        Person firstPerson = EditCommand.findPersonByStudentId(model.getFilteredPersonList(),
                STUDENT_ID_FIRST_PERSON.value);
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Get the last person in the unfiltered list
        Person lastPerson = model.getFilteredPersonList().get(model.getFilteredPersonList().size() - 1);
        StudentId lastPersonStudentId = lastPerson.getStudentId();

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_STUDENT_ID_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(lastPersonStudentId, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new WhoDat(model.getWhoDat()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(STUDENT_ID_FIRST_PERSON, new EditPersonDescriptor());
        Person personToEdit = EditCommand.findPersonByStudentId(model.getFilteredPersonList(),
                STUDENT_ID_FIRST_PERSON.value);
        Person editedPerson = personToEdit; // No fields edited, so it's the same person

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new WhoDat(model.getWhoDat()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, Index.fromOneBased(1)); // Show first person

        Person personInFilteredList = model.getFilteredPersonList().get(0);
        StudentId firstStudentId = personInFilteredList.getStudentId();

        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(firstStudentId,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new WhoDat(model.getWhoDat()), new UserPrefs());
        expectedModel.setPerson(personInFilteredList, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(0);
        Person secondPerson = model.getFilteredPersonList().get(1);

        // Editing second person to be identical to first person should fail
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(secondPerson.getStudentId(), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidStudentIdUnfilteredList_failure() {
        // Use a properly formatted but non-existent student ID
        StudentId nonExistentStudentId = new StudentId("A1234567Z");
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(nonExistentStudentId, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_STUDENTID);
    }

    /**
     * Edit where student ID doesn't exist in filtered list but exists in contact list
     */
    @Test
    public void execute_invalidStudentIdFilteredList_failure() {
        showPersonAtIndex(model, Index.fromOneBased(1));

        // Find a student ID that doesn't exist in the filtered list but exists in the contact list
        String studentIdOutsideFilteredList = model.getWhoDat().getPersonList().get(1).getStudentId().value;
        StudentId studentIdNotInFilteredList = new StudentId(studentIdOutsideFilteredList);

        EditCommand editCommand = new EditCommand(studentIdNotInFilteredList,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        // Should fail because we're trying to edit a person that isn't in the filtered list
        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_STUDENTID);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(STUDENT_ID_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(STUDENT_ID_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different student ID -> returns false
        assertFalse(standardCommand.equals(new EditCommand(STUDENT_ID_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(STUDENT_ID_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(STUDENT_ID_FIRST_PERSON, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{student ID="
                + STUDENT_ID_FIRST_PERSON.value + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}

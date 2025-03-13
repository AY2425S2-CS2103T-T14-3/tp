package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ID;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the student id used in the displayed person list.\n"
            + "Parameters: ID (must be in the format AxxxxxxxX, where x is a number and X is a capital letter)\n"
            + "Example: " + COMMAND_WORD + " A0123456X";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final ID targetID;

    public DeleteCommand(ID targetID) {
        this.targetID = targetID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        /* don't need to check whether index is larger than the largest current index
        if (targetID.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        */

        //adding a hashmap of people and id?
        //Person personToDelete = personIdMap.get(targetID);
        //original: Person personToDelete = lastShownList.get(targetID.);
        Person personToDelete = lastShownList.stream()
                        .filter(person -> person.getId().equals(targetID))
                                .findFirst()
                                        .orElse(null);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetID.equals(otherDeleteCommand.targetID);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetID)
                .toString();
    }
}

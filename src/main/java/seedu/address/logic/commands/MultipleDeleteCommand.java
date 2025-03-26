package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.StudentId;

/**
 * Deletes multiple students using their student ids at the same time.
 */
public class MultipleDeleteCommand extends Command {

    // need to add tests as well

    public static final String COMMAND_WORD = "m_delete";

    // need to change message
    public static final String MESSAGE_SUCCESS = "All persons deleted successfully.";

    private StudentId[] studentIdsToRemove;

    public MultipleDeleteCommand(StudentId[] studentIdArray) {
        this.studentIdsToRemove = studentIdArray;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // need to polish this
        int numStudentIdsToRemove = studentIdsToRemove.length;
        DeleteCommand[] deleteCommandsToExecute = new DeleteCommand[numStudentIdsToRemove];
        for (int i = 0; i < numStudentIdsToRemove; i++) {
            StudentId studentIdToRemove = studentIdsToRemove[i];
            deleteCommandsToExecute[i] = new DeleteCommand(studentIdToRemove);
        }

        for (DeleteCommand deleteCommandToExecute : deleteCommandsToExecute) {
            deleteCommandToExecute.execute(model);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

}

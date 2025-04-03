package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.StudentId;

/**
 * Deletes multiple students using their student ids at the same time.
 */
public class MultipleDeleteCommand extends Command {

    public static final String COMMAND_WORD = "m_delete";
    public static final String MESSAGE_USAGE = "Check the following:\n"
            + "1. All student ids are comma-separated. \n"
            + "2. Student ids follow the format: AxxxxxxxX, where x is a number and X is a capitalised letter.";

    private static final String SUCCESSFULLY_DELETED_STUDENTS_PREFIX = "Deleted Students(Student ID): ";
    private static final String MISSING_STUDENTS_PREFIX = "Students not found(Student ID): ";
    private static final String INVALID_STUDENTS_PREFIX = "Invalid Student ID(s): ";

    private static Logger logger = Logger.getLogger("Foo");

    private StudentId[] validStudentIdsToRemove;
    private List<String> invalidStudentIdStrings;
    private List<String> missingStudentStringsList = new LinkedList<>();
    private List<String> successfullyDeletedStudentStringsList = new LinkedList<>();

    private DeleteCommand[] deleteCommandsToExecute;

    /**
     * Creates MultipleDeleteCommandObject.
     */
    public MultipleDeleteCommand(StudentId[] studentIdArray, List<String> invalidStudentIdStrings) {
        this.validStudentIdsToRemove = studentIdArray;
        this.invalidStudentIdStrings = invalidStudentIdStrings;

        int numStudentIdsToRemove = validStudentIdsToRemove.length;
        deleteCommandsToExecute = new DeleteCommand[numStudentIdsToRemove];
        for (int i = 0; i < numStudentIdsToRemove; i++) {
            StudentId studentIdToRemove = validStudentIdsToRemove[i];
            deleteCommandsToExecute[i] = new DeleteCommand(studentIdToRemove);
        }
    }

    public DeleteCommand[] getDeleteCommandsToExecute() {
        return deleteCommandsToExecute;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        for (DeleteCommand deleteCommandToExecute : deleteCommandsToExecute) {
            handleDeleteCommandExecution(deleteCommandToExecute, model);
        }

        String successfullyDeletedStudentsString =
                getCombinedStringFromListOfString(successfullyDeletedStudentStringsList);
        String missingStudentsString = getCombinedStringFromListOfString(missingStudentStringsList);
        String invalidStudentString = getCombinedStringFromListOfString(invalidStudentIdStrings);

        String message = SUCCESSFULLY_DELETED_STUDENTS_PREFIX + successfullyDeletedStudentsString + "\n"
                + MISSING_STUDENTS_PREFIX + missingStudentsString + "\n"
                + INVALID_STUDENTS_PREFIX + invalidStudentString;

        if (!invalidStudentString.isEmpty()) {
            message += "\n\n" + MESSAGE_USAGE;
        }

        return new CommandResult(message);
    }

    public static String getCombinedStringFromListOfString(List<String> stringList) {
        requireNonNull(stringList);
        String resultingString = "";
        int numStrings = stringList.size();
        for (int i = 0; i < numStrings; i++) {
            String s = stringList.get(i);
            if (i == numStrings - 1) {
                resultingString += s;
            } else {
                resultingString += (s + ", ");
            }
        }
        return resultingString;
    }

    /**
     * Executes delete command. If the delete command fails, it is handled here as well.
     */
    public void handleDeleteCommandExecution(DeleteCommand deleteCommand, Model model) {
        try {
            deleteCommand.execute(model);
            StudentId successfullyDeletedStudent = deleteCommand.getStudentIdToDelete();
            String successfullyDeletedStudentString = successfullyDeletedStudent.toString();
            String loggerMessage = "Deleted: " + successfullyDeletedStudentString;
            logger.log(Level.INFO, loggerMessage);
            successfullyDeletedStudentStringsList.add(successfullyDeletedStudentString);
        } catch (CommandException ce) {
            StudentId missingStudentId = deleteCommand.getStudentIdToDelete();
            String missingStudentIdString = missingStudentId.toString();
            missingStudentStringsList.add(missingStudentIdString);
        }
    }

}

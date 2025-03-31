package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.StudentId;

import java.util.LinkedList;
import java.util.List;

/**
 * Deletes multiple students using their student ids at the same time.
 */
public class MultipleDeleteCommand extends Command {

    public static final String COMMAND_WORD = "m_delete";

    public static final String SUCCESSFULLY_DELETED_STUDENTS_PREFIX = "Deleted Students(Student ID):";
    public static final String MISSING_STUDENTS_PREFIX = "Students not found(Student ID):";
    public static final String INVALID_STUDENTS_PREFIX = "Invalid Student ID(s):";

    private StudentId[] validStudentIdsToRemove;
    private List<String> invalidStudentIdStrings;
    private List<String> missingStudentStringsList = new LinkedList<>();
    private List<String> successfullyDeletedStudentStringsList = new LinkedList<>();

    public MultipleDeleteCommand(StudentId[] studentIdArray, List<String> invalidStudentIdStrings) {
        this.validStudentIdsToRemove = studentIdArray;
        this.invalidStudentIdStrings = invalidStudentIdStrings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int numStudentIdsToRemove = validStudentIdsToRemove.length;
        DeleteCommand[] deleteCommandsToExecute = new DeleteCommand[numStudentIdsToRemove];
        for (int i = 0; i < numStudentIdsToRemove; i++) {
            StudentId studentIdToRemove = validStudentIdsToRemove[i];
            deleteCommandsToExecute[i] = new DeleteCommand(studentIdToRemove);
        }

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

        return new CommandResult(message);
    }

    public String getCombinedStringFromListOfString(List<String> stringList) {
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

    public void handleDeleteCommandExecution(DeleteCommand deleteCommand, Model model) {
        try {
            deleteCommand.execute(model);
            StudentId successfullyDeletedStudent = deleteCommand.getStudentIdToDelete();
            String successfullyDeletedStudentString = successfullyDeletedStudent.toString();
            successfullyDeletedStudentStringsList.add(successfullyDeletedStudentString);
        } catch (CommandException ce) {
            StudentId missingStudentId = deleteCommand.getStudentIdToDelete();
            String missingStudentIdString = missingStudentId.toString();
            missingStudentStringsList.add(missingStudentIdString);
        }
    }

}

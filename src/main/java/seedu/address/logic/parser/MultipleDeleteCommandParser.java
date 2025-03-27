package seedu.address.logic.parser;

import seedu.address.logic.commands.MultipleDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses arguments and creates MultipleDeleteCommand object.
 */
public class MultipleDeleteCommandParser implements Parser<MultipleDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MultipleDeleteCommand
     * and returns a MultipleDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MultipleDeleteCommand parse(String userInput) throws ParseException {
        StudentId[] studentIdsToRemove = ParserUtil.parseMultipleStudentIds(userInput);
        return new MultipleDeleteCommand(studentIdsToRemove);
    }

}

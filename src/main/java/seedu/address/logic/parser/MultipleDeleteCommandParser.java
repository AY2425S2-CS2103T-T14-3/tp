package seedu.address.logic.parser;

import seedu.address.logic.commands.MultipleDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Parses arguments and creates MultipleDeleteCommand object.
 */
public class MultipleDeleteCommandParser implements Parser<MultipleDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MultipleDeleteCommand
     * and returns a MultipleDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MultipleDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedUserInput = args.trim();
        if (trimmedUserInput.isEmpty()) {
            throw new ParseException("There needs to be at least one student id.");
        }
        List<String> invalidStudentIdStrings = getInvalidStudentIds(trimmedUserInput);
        StudentId[] studentIdsToRemove = ParserUtil.parseMultipleStudentIds(args);
        return new MultipleDeleteCommand(studentIdsToRemove, invalidStudentIdStrings);
    }

    public List<String> getInvalidStudentIds(String input) {
        assert !input.isEmpty() : "An error would have been thrown by MultipleDeleteCommand: parse";
        String[] studentIdStrings = ParserUtil.separateStringByComma(input);
        List<String> invalidStudentIdStrings = new LinkedList<>();
        for (String studentIdString : studentIdStrings) {
            if (!StudentId.isValidStudentId(studentIdString)) {
                invalidStudentIdStrings.add(studentIdString);
            }
        }
        return invalidStudentIdStrings;
    }

}

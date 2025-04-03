package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdMatchPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] trimmedArgsSplitted = trimmedArgs.split("\\s+");
        Pattern pattern = Pattern.compile("A\\d{7}[A-Z]", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(trimmedArgsSplitted[0]).matches()) {
            if (trimmedArgsSplitted.length > 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            StudentId studentId = new StudentId(trimmedArgs.toUpperCase());
            return new FindCommand(new StudentIdMatchPredicate(studentId));
        } else {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(trimmedArgsSplitted)));
        }
    }
}

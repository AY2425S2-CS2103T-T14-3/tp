package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.StudentHasSameClassIdPredicate;
import seedu.address.model.person.StudentHasSameTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    public static final String SUFFIX_FILTER_BY_CLASS = "byclass";
    public static final String SUFFIX_FILTER_BY_TAG = "bytag";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        try {
            if (args.endsWith(SUFFIX_FILTER_BY_CLASS)) {
                String trimmedArgs = args.substring(0, args.length() - SUFFIX_FILTER_BY_CLASS.length()).trim();
                ClassId classId = ParserUtil.parseClassId(trimmedArgs);
                return new FilterCommand(new StudentHasSameClassIdPredicate(classId));
            } else if (args.endsWith(SUFFIX_FILTER_BY_TAG)) {
                String trimmedArgs = args.substring(0, args.length() - SUFFIX_FILTER_BY_TAG.length()).trim();
                Tag tag = ParserUtil.parseTag(trimmedArgs);
                return new FilterCommand(new StudentHasSameTagPredicate(tag));
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }
}

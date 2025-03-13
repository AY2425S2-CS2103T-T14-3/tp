package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassID;
import seedu.address.model.person.EmailID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentID;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        // todo: implement error catching
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_EMAIL_ID, PREFIX_CLASS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_EMAIL_ID, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID, PREFIX_EMAIL_ID, PREFIX_CLASS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        StudentID studentID = ParserUtil.parseStudentID(argMultimap.getValue(PREFIX_ID).get());
        EmailID emailID = ParserUtil.parseEmailID(argMultimap.getValue(PREFIX_EMAIL_ID).get());
        ClassID classID = ParserUtil.parseClassID(argMultimap.getValue(PREFIX_CLASS).get());

        Person person = new Person(name, studentID, emailID, classID);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

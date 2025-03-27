package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.StudentHasSameIdPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        String validClassId = "CS323019";
        FilterCommand expectedFilterCommand =
                new FilterCommand(new StudentHasSameIdPredicate(validClassId));
        // class id's match
        assertParseSuccess(parser, "CS323019", expectedFilterCommand);

        // class id with leading and trailing whitespaces
        assertParseSuccess(parser, "  CS323019  ", expectedFilterCommand);

        // case insensitive
        assertParseSuccess(parser, "cs323019", expectedFilterCommand);
    }

}

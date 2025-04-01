package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FilterCommandParser.SUFFIX_FILTER_BY_CLASS;
import static seedu.address.logic.parser.FilterCommandParser.SUFFIX_FILTER_BY_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.StudentHasSameClassIdPredicate;
import seedu.address.model.person.StudentHasSameTagPredicate;
import seedu.address.model.tag.Tag;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        ClassId testId = new ClassId("CS2109S04");
        FilterCommand expectedCommandForId =
                new FilterCommand(new StudentHasSameClassIdPredicate(testId));
        Tag testTag = new Tag("NeedHelp");
        FilterCommand expectedCommandForTag = new FilterCommand(new StudentHasSameTagPredicate(testTag));

        // class ids match
        assertParseSuccess(parser, "CS2109S04" + SUFFIX_FILTER_BY_CLASS, expectedCommandForId);

        // class id with leading and trailing whitespaces
        assertParseSuccess(parser, "  CS2109S04  " + SUFFIX_FILTER_BY_CLASS, expectedCommandForId);

        // tags match
        assertParseSuccess(parser, "NeedHelp" + SUFFIX_FILTER_BY_TAG, expectedCommandForTag);

        // tags with leading and trailing whitespaces
        assertParseSuccess(parser, "    NeedHelp " + SUFFIX_FILTER_BY_TAG, expectedCommandForTag);
    }

}

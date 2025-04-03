package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.MultipleDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

public class MultipleDeleteCommandParserTest {

    private MultipleDeleteCommandParser parser = new MultipleDeleteCommandParser();

    @Test
    public void parse_emptyString_parseExceptionThrown() {
        String emptyString = "";
        assertThrows(ParseException.class, () -> parser.parse(emptyString));
    }

    @Test
    public void parse_null_nullPointerException() {
        String input = null;
        assertThrows(NullPointerException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_validArgs_returnsMultipleDeleteCommand() {
        // Note that multiple-delete consists of multiple delete commands executing
        String validStudentIdString1 = "A1234567H";
        StudentId validStudentId1 = new StudentId(validStudentIdString1);
        String validStudentIdString2 = "A2234567H";
        StudentId validStudentId2 = new StudentId(validStudentIdString2);

        String invalidStudentIdString1 = "A123456G";
        String invalidStudentIdString2 = "12345";

        // all valid
        String input = " " + validStudentIdString1 + "," + validStudentIdString2;
        DeleteCommand[] expected = new DeleteCommand[]{new DeleteCommand(validStudentId1),
            new DeleteCommand(validStudentId2)};
        assertValidArgs(expected, input);

        input = " " + validStudentIdString1 + ",";
        expected = new DeleteCommand[]{new DeleteCommand(validStudentId1)};
        assertValidArgs(expected, input);

        // half valid, half invalid
        input = " " + validStudentIdString1 + "," + invalidStudentIdString1;
        expected = new DeleteCommand[]{new DeleteCommand(validStudentId1)};
        assertValidArgs(expected, input);

        // all invalid
        input = invalidStudentIdString1 + "," + invalidStudentIdString2;
        expected = new DeleteCommand[]{};
        assertValidArgs(expected, input);
    }

    private void assertValidArgs(DeleteCommand[] expected, String testInput) {
        try {
            MultipleDeleteCommand actualMultipleDeleteCommandOutput = parser.parse(testInput);
            DeleteCommand[] deleteCommands = actualMultipleDeleteCommandOutput.getDeleteCommandsToExecute();
            assertTrue(deleteCommands.length == expected.length);
            for (int i = 0; i < deleteCommands.length; i++) {
                assertTrue(expected[i].equals(deleteCommands[i]));
            }
        } catch (ParseException pe) {
            fail();
        }
    }

    private void assertInvalidStudentIds(String input, String... expectedInvalidIds) {
        List<String> expected = Arrays.asList(expectedInvalidIds);
        assertEquals(expected, MultipleDeleteCommandParser.getInvalidStudentIds(input));
    }

    @Test
    void test_getInvalidStudentIds() {

        String validStudentIdString1 = "A1234567H";
        String validStudentIdString2 = "A2234567H";

        String invalidStudentIdString1 = "A123456G";
        String invalidStudentIdString2 = "12345";

        // all valid (with spacing)
        assertInvalidStudentIds(validStudentIdString1);
        assertInvalidStudentIds(validStudentIdString1 + " "); // with additional spacing
        assertInvalidStudentIds(validStudentIdString1 + ", " + validStudentIdString2);

        // all invalid
        assertInvalidStudentIds(invalidStudentIdString1, invalidStudentIdString1);
        assertInvalidStudentIds(invalidStudentIdString1 + "," + invalidStudentIdString2,
                invalidStudentIdString1, invalidStudentIdString2);

        // mix of valid and invalid
        assertInvalidStudentIds(invalidStudentIdString1 + "," + validStudentIdString1, invalidStudentIdString1);
    }

}

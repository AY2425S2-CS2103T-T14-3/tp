package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudentIds.STUDENT_ID_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.EmailId;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no student ID specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value, EditCommand.MESSAGE_NOT_EDITED);

        // no student ID and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid student ID
        assertParseFailure(parser, "X12345" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "A1234 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "A1234 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_STUDENT_ID_DESC,
                StudentId.MESSAGE_CONSTRAINTS); // invalid student ID
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_EMAIL_ID_DESC,
                EmailId.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_ADDRESS_DESC,
                ClassId.MESSAGE_CONSTRAINTS); // invalid class ID
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid student ID followed by valid email
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_STUDENT_ID_DESC + EMAIL_ID_DESC_AMY,
                StudentId.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value
                + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value
                + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, STUDENT_ID_FIRST_PERSON.value + INVALID_NAME_DESC
                + INVALID_EMAIL_ID_DESC + VALID_CLASS_ID_AMY + VALID_STUDENT_ID_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = STUDENT_ID_SECOND_PERSON.value + STUDENT_ID_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_ID_DESC_AMY + CLASS_ID_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_ID_AMY).withClassId(VALID_CLASS_ID_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(STUDENT_ID_SECOND_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = STUDENT_ID_FIRST_PERSON.value + STUDENT_ID_DESC_BOB + EMAIL_ID_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withStudentId(VALID_STUDENT_ID_BOB)
                .withEmail(VALID_EMAIL_ID_AMY).build();
        EditCommand expectedCommand = new EditCommand(STUDENT_ID_FIRST_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = STUDENT_ID_THIRD_PERSON.value + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(STUDENT_ID_THIRD_PERSON, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student ID
        userInput = STUDENT_ID_THIRD_PERSON.value + STUDENT_ID_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY).build();
        expectedCommand = new EditCommand(STUDENT_ID_THIRD_PERSON, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = STUDENT_ID_THIRD_PERSON.value + EMAIL_ID_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_ID_AMY).build();
        expectedCommand = new EditCommand(STUDENT_ID_THIRD_PERSON, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // class ID
        userInput = STUDENT_ID_THIRD_PERSON.value + CLASS_ID_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withClassId(VALID_CLASS_ID_AMY).build();
        expectedCommand = new EditCommand(STUDENT_ID_THIRD_PERSON, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = STUDENT_ID_THIRD_PERSON.value + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(STUDENT_ID_THIRD_PERSON, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        String userInput = STUDENT_ID_FIRST_PERSON.value + INVALID_STUDENT_ID_DESC + STUDENT_ID_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // invalid followed by valid
        userInput = STUDENT_ID_FIRST_PERSON.value + STUDENT_ID_DESC_BOB + INVALID_STUDENT_ID_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // multiple valid fields repeated
        userInput = STUDENT_ID_FIRST_PERSON.value + STUDENT_ID_DESC_AMY + CLASS_ID_DESC_AMY + EMAIL_ID_DESC_AMY
                + TAG_DESC_FRIEND + STUDENT_ID_DESC_AMY + CLASS_ID_DESC_AMY + EMAIL_ID_DESC_AMY + TAG_DESC_FRIEND
                + STUDENT_ID_DESC_BOB + CLASS_ID_DESC_BOB + EMAIL_ID_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID, PREFIX_EMAIL_ID, PREFIX_CLASS_ID));

        // multiple invalid values
        userInput = STUDENT_ID_FIRST_PERSON.value + INVALID_STUDENT_ID_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_ID_DESC
                + INVALID_STUDENT_ID_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_ID_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID, PREFIX_EMAIL_ID, PREFIX_CLASS_ID));
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = STUDENT_ID_THIRD_PERSON.value + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(STUDENT_ID_THIRD_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

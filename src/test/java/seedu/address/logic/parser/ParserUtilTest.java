package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.parseMultipleStudentIds;
import static seedu.address.logic.parser.ParserUtil.separateStringByComma;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.EmailId;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "232342";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_STUDENT_ID = "A1234567B";
    private static final String VALID_CLASSID = "cs2101-01";
    private static final String VALID_EMAIL_ID = "E1123857";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseStudentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId((String) null));
    }

    @Test
    public void parseStudentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithoutWhitespace_returnsStudentId() throws Exception {
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String studentIdWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(studentIdWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClassId((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClassId(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        ClassId expectedClassId = new ClassId(VALID_CLASSID);
        assertEquals(expectedClassId, ParserUtil.parseClassId(VALID_CLASSID));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_CLASSID + WHITESPACE;
        ClassId expectedClassId = new ClassId(VALID_CLASSID);
        assertEquals(expectedClassId, ParserUtil.parseClassId(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        EmailId expectedEmailId = new EmailId(VALID_EMAIL_ID);
        assertEquals(expectedEmailId, ParserUtil.parseEmail(VALID_EMAIL_ID));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL_ID + WHITESPACE;
        EmailId expectedEmailId = new EmailId(VALID_EMAIL_ID);
        assertEquals(expectedEmailId, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void test_splitStringByComma() {
        String nullInput = null;
        String[] expectedResult = null;
        assertEquals(expectedResult, separateStringByComma(nullInput));

        String emptyString = "";
        expectedResult = new String[]{""};
        assertArrayEquals(expectedResult, separateStringByComma(emptyString));

        String stringWithNoComma = "hello";
        expectedResult = new String[]{"hello"};
        assertArrayEquals(expectedResult, separateStringByComma(stringWithNoComma));

        String stringWithCommaOnly = "hello,helloAgain";
        expectedResult = new String[]{"hello", "helloAgain"};
        assertArrayEquals(expectedResult, separateStringByComma(stringWithCommaOnly));

        String stringWithCommaAndSpacing = "hello, hello Hello";
        expectedResult = new String[]{"hello", " hello Hello"};
        assertArrayEquals(expectedResult, separateStringByComma(stringWithCommaAndSpacing));
    }

    @Test
    public void test_parseMultipleStudentIds_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseMultipleStudentIds(null));
    }

    @Test
    public void test_parseMultipleStudentIds_returnsArrayOfStudentIds() throws ParseException {
        String studentIdString1 = "A1234567H";
        StudentId studentId1 = new StudentId(studentIdString1);
        String studentIdString2 = "A7654321G";
        StudentId studentId2 = new StudentId(studentIdString2);

        // only one correct studentId, no spacing
        StudentId[] actualResults = parseMultipleStudentIds(studentIdString1);
        assertTrue(studentId1.equals(actualResults[0]));

        // only one correct studentId, with spacing
        String inputStudentIds = studentIdString1 + " ";
        actualResults = parseMultipleStudentIds(inputStudentIds);
        assertTrue(studentId1.equals(actualResults[0]));

        // two correct inputStudentIds, without spacing, with comma
        inputStudentIds = studentIdString1 + "," + studentIdString2;
        StudentId[] expectedResults = new StudentId[]{studentId1, studentId2};
        int numStudents = 2;
        actualResults = parseMultipleStudentIds(inputStudentIds);
        for (int i = 0; i < numStudents; i++) {
            assertTrue(expectedResults[i].equals(actualResults[i]));
        }

        // two correct inputStudentIds, with spacing, with comma
        inputStudentIds = studentIdString1 + " , " + studentIdString2;
        actualResults = parseMultipleStudentIds(inputStudentIds);
        for (int i = 0; i < numStudents; i++) {
            assertTrue(expectedResults[i].equals(actualResults[i]));
        }
    }

    @Test
    public void test_parseMultipleStudentIds_throwsParseException() {
        String incorrectStudentId = "A12345X";
        String correctStudentId = "A1234567H";

        // only one incorrect student id
        String input = incorrectStudentId;
        try {
            parseMultipleStudentIds(input);
        } catch (ParseException pe) {
            String expectedErrorMessage = StudentId.MESSAGE_CONSTRAINTS;
            String actualErrorMessage = pe.getMessage();
            assertTrue(expectedErrorMessage.equals(actualErrorMessage));
        }

        // one incorrect and one correct student id
        input = incorrectStudentId + "," + correctStudentId;
        try {
            parseMultipleStudentIds(input);
        } catch (ParseException pe) {
            String expectedErrorMessage = StudentId.MESSAGE_CONSTRAINTS;
            String actualErrorMessage = pe.getMessage();
            assertTrue(expectedErrorMessage.equals(actualErrorMessage));
        }

        // empty string
        input = "";
        try {
            parseMultipleStudentIds(input);
        } catch (ParseException pe) {
            String expectedErrorMessage = StudentId.MESSAGE_CONSTRAINTS;
            String actualErrorMessage = pe.getMessage();
            assertTrue(expectedErrorMessage.equals(actualErrorMessage));
        }

    }
}

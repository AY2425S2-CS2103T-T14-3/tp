package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.EmailId;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }



    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String classId} into a {@code ClassId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code classId} is invalid.
     */
    public static ClassId parseClassId(String classId) throws ParseException {
        requireNonNull(classId);
        String trimmedClassId = classId.trim();
        if (!ClassId.isValidClassId(trimmedClassId)) {
            throw new ParseException(ClassId.MESSAGE_CONSTRAINTS);
        }
        return new ClassId(trimmedClassId);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static EmailId parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!EmailId.isValidEmailId(trimmedEmail)) {
            throw new ParseException(EmailId.MESSAGE_CONSTRAINTS);
        }
        return new EmailId(trimmedEmail);
    }

    /**
     * Parses a {@code String studentIdString} into an {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentIdsString} is invalid.
     */
    public static StudentId parseStudentId(String studentIdString) throws ParseException {
        requireNonNull(studentIdString);
        String trimmedStudentId = studentIdString.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String studentIdsString} into a {@code StudentId[]}.
     * The array returned contains only valid,non-null and distinct {@code StudentId} objects.
     */
    public static StudentId[] parseMultipleStudentIds(String studentIdsString) {
        requireNonNull(studentIdsString);
        String[] studentIdStringsArray = separateStringByComma(studentIdsString);
        int numStudentId = studentIdStringsArray.length;
        StudentId[] studentIdsArray = new StudentId[numStudentId];
        for (int i = 0; i < numStudentId; i++) {
            updateStudentIdArray(studentIdsArray, studentIdStringsArray[i], i);
        }
        StudentId[] studentIdsArrayWithoutNulls = removeNullEntriesFromStudentIdArray(studentIdsArray);
        StudentId[] studentIdsArrayWithoutNullsAndDuplicates =
                removeDuplicatesFromStudentIdArray(studentIdsArrayWithoutNulls);
        return studentIdsArrayWithoutNullsAndDuplicates;
    }

    /**
     * Takes a {@code StudentId[]} without nulls and returns a new copy without duplicates.
     */
    public static StudentId[] removeDuplicatesFromStudentIdArray(StudentId[] studentIds) {
        assert studentIds != null : "The input array cannot be null.";
        List<Integer> indicesOfDistinctStudentIds = new LinkedList<>();
        Set<String> distinctStudentIds = new HashSet<>();
        int numStudentIds = studentIds.length;

        for (int i = 0; i < numStudentIds; i++) {
            StudentId currStudentId = studentIds[i];
            assert currStudentId != null : "No null student id entries can be present.";
            String studentIdString = currStudentId.toString();
            if (!distinctStudentIds.contains(studentIdString)) {
                distinctStudentIds.add(studentIdString);
                indicesOfDistinctStudentIds.add(i);
            }
        }

        int numUniqueStudentIds = distinctStudentIds.size();
        StudentId[] studentIdsWithoutDuplicates = new StudentId[numUniqueStudentIds];
        for (int i = 0; i < numUniqueStudentIds; i++) {
            Integer indexOfUniqueStudentId = indicesOfDistinctStudentIds.get(i);
            StudentId uniqueStudentId = studentIds[indexOfUniqueStudentId];
            studentIdsWithoutDuplicates[i] = uniqueStudentId;
        }
        return studentIdsWithoutDuplicates;
    }

    /**
     * Removes null entries from the {@code StudentId[]}.
     */
    public static StudentId[] removeNullEntriesFromStudentIdArray(StudentId[] arr) {
        requireNonNull(arr);
        int numNonNullEntries = 0;
        int arrayLength = arr.length;
        for (int i = 0; i < arrayLength; i++) {
            StudentId studentIdEntry = arr[i];
            if (studentIdEntry != null) {
                numNonNullEntries++;
            }
        }

        StudentId[] studentIdArrayWithoutNull = new StudentId[numNonNullEntries];
        int pointer = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (arr[i] != null) {
                studentIdArrayWithoutNull[pointer] = arr[i];
                pointer++;
            }
        }
        return studentIdArrayWithoutNull;
    }

    /**
     * Creates a {@code StudentId} object and adds it into the specified index in the array.
     * If the student id is not valid, a null is added instead.
     */
    public static void updateStudentIdArray(StudentId[] arr, String studentIdString, int i) {
        int numStudents = arr.length;
        assert i < numStudents && i >= 0
                : "Since the function call originates from the loop inside the function body of "
                + "parseMultipleStudentIds, where i is a loop parameter, i is always within the array bounds.";
        try {
            // may throw null pointer exception if studentIdString is null
            arr[i] = parseStudentId(studentIdString);
        } catch (ParseException | NullPointerException exception) {
            arr[i] = null;
        }
    }

    /**
     * Takes a comma-separated string and breaks it up into a {@code String[]}.
     */
    public static String[] separateStringByComma(String s) {
        if (s == null) {
            return null;
        }

        String[] stringParts = s.split(",");
        return stringParts;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}

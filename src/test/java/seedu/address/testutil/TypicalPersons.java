package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withClassId("cs1101-01")
            .withEmailId("E0123456")
            .withStudentId("A1234567H")
            .withTags("NeedHelp").build();
    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withClassId("cs1101-01")
            .withEmailId("E1234568")
            .withStudentId("A1234567G")
            .withTags("LateSubmission", "NeedHelp").build();
    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withStudentId("A1234567F")
            .withEmailId("E2234567")
            .withClassId("cs1101-01").build();
    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withStudentId("A1234567D")
            .withEmailId("E3456789")
            .withClassId("cs1101-01")
            .withTags("NeedHelp").build();

    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withStudentId("A1234567V")
            .withEmailId("E1111111")
            .withClassId("cs1101-01").build();
    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withStudentId("A1234567R")
            .withEmailId("E9248571")
            .withClassId("cs1101-01").build();
    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withStudentId("A1234567S")
            .withEmailId("E2845729")
            .withClassId("cs1101-01").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withStudentId("A4444444G")
            .withEmailId("E5938475").withClassId("cs1101-08").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withStudentId("A7777777F")
            .withEmailId("E1230495").withClassId("cs1101t-01").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
            .withEmailId(VALID_EMAIL_ID_AMY).withClassId(VALID_CLASS_ID_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
            .withEmailId(VALID_EMAIL_ID_BOB).withClassId(VALID_CLASS_ID_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

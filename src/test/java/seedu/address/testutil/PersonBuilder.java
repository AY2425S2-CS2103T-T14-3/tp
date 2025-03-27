package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.ClassId;
import seedu.address.model.person.EmailId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A2222222K";
    public static final String DEFAULT_EMAIL = "E1234567";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private StudentId studentId;
    private EmailId emailId;
    private ClassId classId;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        emailId = new EmailId(DEFAULT_EMAIL);
        classId = new ClassId(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        studentId = personToCopy.getStudentId();
        emailId = personToCopy.getEmail();
        classId = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code classId} of the {@code Person} that we are building.
     */
    public PersonBuilder withClassId(String classId) {
        this.classId = new ClassId(classId);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmailId(String email) {
        this.emailId = new EmailId(email);
        return this;
    }

    public Person build() {
        return new Person(name, studentId, emailId, classId, tags);
    }

}

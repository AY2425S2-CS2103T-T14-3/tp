package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ClassId;
import seedu.address.model.person.EmailId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new StudentId("A1234567B"), new EmailId("E1234567"),
                    new ClassId("123456"),
                    getTagSet("NeedHelp")),
            new Person(new Name("Bernice Yu"), new StudentId("A2345678C"), new EmailId("E1234567"),
                    new ClassId("234567"),
                    getTagSet("NeedHelp", "LateSubmission")),
            new Person(new Name("Charlotte Oliveiro"), new StudentId("A3456789D"), new EmailId("E1234567"),
                    new ClassId("345678"),
                    getTagSet("NoSubmission")),
            new Person(new Name("David Li"), new StudentId("A4567890E"), new EmailId("E1234567"),
                    new ClassId("456789"),
                    getTagSet("PendingConsultation")),
            new Person(new Name("Irfan Ibrahim"), new StudentId("A5678901F"), new EmailId("E1234567"),
                    new ClassId("567890"),
                    getTagSet("PendingResponse")),
            new Person(new Name("Roy Balakrishnan"), new StudentId("A6789012G"), new EmailId("E1234567"),
                    new ClassId("678901"),
                    getTagSet("LateSubmission")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

package seedu.address.testutil;

import seedu.address.model.WhoDat;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private WhoDat whoDat;

    public AddressBookBuilder() {
        whoDat = new WhoDat();
    }

    public AddressBookBuilder(WhoDat whoDat) {
        this.whoDat = whoDat;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        whoDat.addPerson(person);
        return this;
    }

    public WhoDat build() {
        return whoDat;
    }
}

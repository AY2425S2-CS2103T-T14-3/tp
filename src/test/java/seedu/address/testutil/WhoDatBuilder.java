package seedu.address.testutil;

import seedu.address.model.WhoDat;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building WhoDat objects.
 * Example usage: <br>
 *     {@code WhoDat wd = new WhoDatBuilder().withPerson("John", "Doe").build();}
 */
public class WhoDatBuilder {

    private WhoDat whoDat;

    public WhoDatBuilder() {
        whoDat = new WhoDat();
    }

    public WhoDatBuilder(WhoDat whoDat) {
        this.whoDat = whoDat;
    }

    /**
     * Adds a new {@code Person} to the {@code WhoDat} that we are building.
     */
    public WhoDatBuilder withPerson(Person person) {
        whoDat.addPerson(person);
        return this;
    }

    public WhoDat build() {
        return whoDat;
    }
}

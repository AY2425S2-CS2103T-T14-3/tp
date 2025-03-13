package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private Name name;
    private StudentID id = new StudentID("0");
    private EmailID emailId = new EmailID("0");
    private ClassID classId = new ClassID("0");
    private Set<Tag> tags = new HashSet<>();
    private Phone phone = new Phone("000");
    private Email email = new Email("hello@edu.com.sg");

    // Data fields
    private Address address = new Address("0000");

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }


    public Person(Name name, StudentID id, EmailID emailId, ClassID classId) {
        requireAllNonNull(name, id, emailId, classId);
        this.name = name;
        this.id = id;
        this.emailId = emailId;
        this.classId = classId;
    }

    public Name getName() {
        return name;
    }

    public StudentID getId() {
        return id;
    }

    public EmailID getEmailId() {
        return emailId;
    }

    public ClassID getClassId() {
        return classId;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, emailId, classId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("id", id)
                .add("emailId", emailId)
                .add("classId", classId)
                .toString();
    }

}

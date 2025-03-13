package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * This class represents the email id of a student, with starts with 'E'.
 * E.g. 'E1234567' is a possible email id.
 */
public class EmailID {
    String emailID;

    public EmailID(String emailID) {
        requireNonNull(emailID);
        this.emailID = emailID;
    }

    @Override
    public String toString() {
        return emailID;
    }

}

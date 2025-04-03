package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdMatchPredicate;

import java.util.Objects;

/**
 * Finds and lists all persons in contact list whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive)"
            + "or match the given student number"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate namePredicate;

    private final StudentIdMatchPredicate idPredicate;

    /**
     * @param namePredicate predicate regarding the keywords in a student's name the user wants to find
     * @param idPredicate predicate regarding the studentId the user wants to find
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate, StudentIdMatchPredicate idPredicate) {
        if (namePredicate == null && idPredicate == null) {
            throw new IllegalArgumentException("At least one predicate must be provided.");
        }
        this.namePredicate = namePredicate;
        this.idPredicate = idPredicate;
    }

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this(predicate, null);
    }

    public FindCommand(StudentIdMatchPredicate predicate) {
        this(null, predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (idPredicate == null) {
            model.updateFilteredPersonList(namePredicate);
        } else {
            model.updateFilteredPersonList(idPredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return Objects.equals(namePredicate, otherFindCommand.namePredicate)
                && Objects.equals(idPredicate, otherFindCommand.idPredicate);

    }

    @Override
    public String toString() {
        if (idPredicate == null) {
            return new ToStringBuilder(this)
                    .add("name predicate", namePredicate)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("id predicate", idPredicate)
                    .toString();
        }
    }
}

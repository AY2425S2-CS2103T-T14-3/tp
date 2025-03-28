package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdMatchPredicate;

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

    private final NameContainsKeywordsPredicate name_predicate;

    private final StudentIdMatchPredicate id_predicate;

    /**
     * @param name_predicate predicate regarding the keywords in a student's name the user wants to find
     * @param id_predicate predicate regarding the studentId the user wants to find
     */
    public FindCommand(NameContainsKeywordsPredicate name_predicate, StudentIdMatchPredicate id_predicate) {
        if (name_predicate == null && id_predicate == null) {
            throw new IllegalArgumentException("At least one predicate must be provided.");
        }
        this.name_predicate = name_predicate;
        this.id_predicate = id_predicate;
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
        if (id_predicate == null) {
            model.updateFilteredPersonList(name_predicate);
        } else {
            model.updateFilteredPersonList(id_predicate);
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
        if (name_predicate == null) {
            return otherFindCommand.name_predicate == null && otherFindCommand.id_predicate.equals(id_predicate);
        } else {
            return otherFindCommand.id_predicate == null && name_predicate.equals(otherFindCommand.name_predicate);
        }

    }

    @Override
    public String toString() {
        if (id_predicate == null) {
            return new ToStringBuilder(this)
                    .add("name predicate", name_predicate)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("id predicate", id_predicate)
                    .toString();
        }
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.StudentHasSameClassIdPredicate;
import seedu.address.model.person.StudentHasSameTagPredicate;

/**
 * Filters the students based on their class id.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD_CLASS = "filter_c";
    public static final String COMMAND_WORD_TAG = "filter_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD_CLASS + " / " + COMMAND_WORD_TAG
            + ": Filters the students based on class id OR tag\n"
            + "Example: " + COMMAND_WORD_CLASS + " CS1101S03 OR "
            + "Example: " + COMMAND_WORD_TAG + " NeedHelp\n";

    private StudentHasSameClassIdPredicate idPredicate = null;
    private StudentHasSameTagPredicate tagPredicate = null;

    /**
     * Constructs a {@code FilterCommand}.
     *
     * @param idPredicate The predicate matching the id keyed in by the user.
     */
    public FilterCommand(StudentHasSameClassIdPredicate idPredicate) {
        requireNonNull(idPredicate);
        this.idPredicate = idPredicate;
    }

    /**
     * Constructs a {@code FilterCommand}.
     *
     * @param tagPredicate The predicate matching the tag keyed in by the user.
     */
    public FilterCommand(StudentHasSameTagPredicate tagPredicate) {
        requireNonNull(tagPredicate);
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (tagPredicate == null) {
            model.updateFilteredPersonList(idPredicate);
        } else {
            model.updateFilteredPersonList(tagPredicate);
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
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        if (tagPredicate == null) {
            return otherFilterCommand.tagPredicate == null && idPredicate.equals(otherFilterCommand.idPredicate);
        } else {
            return otherFilterCommand.idPredicate == null && tagPredicate.equals(otherFilterCommand.tagPredicate);
        }
    }

    @Override
    public String toString() {
        if (tagPredicate == null) {
            return new ToStringBuilder(this)
                    .add("predicate", idPredicate)
                    .toString();
        }
        return new ToStringBuilder(this)
                .add("predicate", tagPredicate)
                .toString();
    }
}

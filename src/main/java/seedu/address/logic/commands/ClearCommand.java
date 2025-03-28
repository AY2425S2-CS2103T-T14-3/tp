package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.WhoDat;
import seedu.address.model.Model;

/**
 * Clears the contact list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Contact list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setWhoDat(new WhoDat());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

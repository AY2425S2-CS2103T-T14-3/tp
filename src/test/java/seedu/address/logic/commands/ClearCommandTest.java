package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalWhoDat;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WhoDat;

public class ClearCommandTest {

    @Test
    public void execute_emptyWhoDat_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyWhoDat_success() {
        Model model = new ModelManager(getTypicalWhoDat(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWhoDat(), new UserPrefs());
        expectedModel.setWhoDat(new WhoDat());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

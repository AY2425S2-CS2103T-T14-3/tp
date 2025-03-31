package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWhoDat;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends WhoDatStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getWhoDatFilePath();

    @Override
    Optional<ReadOnlyWhoDat> readWhoDat() throws DataLoadingException;

    @Override
    void saveWhoDat(ReadOnlyWhoDat whoDat) throws IOException;

}

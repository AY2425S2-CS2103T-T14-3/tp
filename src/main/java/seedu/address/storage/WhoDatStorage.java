package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyWhoDat;
import seedu.address.model.WhoDat;

/**
 * Represents a storage for {@link WhoDat}.
 */
public interface WhoDatStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWhoDatFilePath();

    /**
     * Returns WhoDat data as a {@link ReadOnlyWhoDat}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyWhoDat> readWhoDat() throws DataLoadingException;

    /**
     * @see #getWhoDatFilePath()
     */
    Optional<ReadOnlyWhoDat> readWhoDat(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyWhoDat} to the storage.
     * @param whoDat cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWhoDat(ReadOnlyWhoDat whoDat) throws IOException;

    /**
     * @see #saveWhoDat(ReadOnlyWhoDat)
     */
    void saveWhoDat(ReadOnlyWhoDat whoDat, Path filePath) throws IOException;

}

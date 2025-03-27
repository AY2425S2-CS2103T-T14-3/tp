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
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyWhoDat}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyWhoDat> readAddressBook() throws DataLoadingException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyWhoDat> readAddressBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyWhoDat} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyWhoDat addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyWhoDat)
     */
    void saveAddressBook(ReadOnlyWhoDat addressBook, Path filePath) throws IOException;

}

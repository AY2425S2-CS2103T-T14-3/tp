package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyWhoDat;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WhoDatStorage whoDatStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WhoDatStorage whoDatStorage, UserPrefsStorage userPrefsStorage) {
        this.whoDatStorage = whoDatStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return whoDatStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyWhoDat> readAddressBook() throws DataLoadingException {
        return readAddressBook(whoDatStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyWhoDat> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return whoDatStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyWhoDat addressBook) throws IOException {
        saveAddressBook(addressBook, whoDatStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyWhoDat addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        whoDatStorage.saveAddressBook(addressBook, filePath);
    }

}

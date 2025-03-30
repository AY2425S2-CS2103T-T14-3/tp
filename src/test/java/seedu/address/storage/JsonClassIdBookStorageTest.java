package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalWhoDat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyWhoDat;
import seedu.address.model.WhoDat;

public class JsonClassIdBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWhoDatStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWhoDat_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWhoDat(null));
    }

    private java.util.Optional<ReadOnlyWhoDat> readWhoDat(String filePath) throws Exception {
        return new JsonWhoDatStorage(Paths.get(filePath)).readWhoDat(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWhoDat("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readWhoDat("notJsonFormatWhoDat.json"));
    }

    @Test
    public void readWhoDat_invalidPersonWhoDat_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readWhoDat("invalidPersonWhoDat.json"));
    }

    @Test
    public void readWhoDat_invalidAndValidPersonWhoDat_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readWhoDat("invalidAndValidPersonWhoDat.json"));
    }

    @Test
    public void readAndSaveWhoDat_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWhoDat.json");
        WhoDat original = getTypicalWhoDat();
        JsonWhoDatStorage jsonWhoDatStorage = new JsonWhoDatStorage(filePath);

        // Save in new file and read back
        jsonWhoDatStorage.saveWhoDat(original, filePath);
        ReadOnlyWhoDat readBack = jsonWhoDatStorage.readWhoDat(filePath).get();
        assertEquals(original, new WhoDat(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonWhoDatStorage.saveWhoDat(original, filePath);
        readBack = jsonWhoDatStorage.readWhoDat(filePath).get();
        assertEquals(original, new WhoDat(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonWhoDatStorage.saveWhoDat(original); // file path not specified
        readBack = jsonWhoDatStorage.readWhoDat().get(); // file path not specified
        assertEquals(original, new WhoDat(readBack));

    }

    @Test
    public void saveWhoDat_nullWhoDat_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWhoDat(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specif
     * ied {@code filePath}.
     */
    private void saveWhoDat(ReadOnlyWhoDat addressBook, String filePath) {
        try {
            new JsonWhoDatStorage(Paths.get(filePath))
                    .saveWhoDat(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWhoDat_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWhoDat(new WhoDat(), null));
    }
}

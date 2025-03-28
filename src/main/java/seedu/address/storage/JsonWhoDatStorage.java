package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyWhoDat;

/**
 * A class to access WhoDat data stored as a json file on the hard disk.
 */
public class JsonWhoDatStorage implements WhoDatStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWhoDatStorage.class);

    private Path filePath;

    public JsonWhoDatStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWhoDatFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWhoDat> readWhoDat() throws DataLoadingException {
        return readWhoDat(filePath);
    }

    /**
     * Similar to {@link #readWhoDat()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyWhoDat> readWhoDat(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableWhoDat> jsonWhoDat = JsonUtil.readJsonFile(
                filePath, JsonSerializableWhoDat.class);
        if (!jsonWhoDat.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWhoDat.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveWhoDat(ReadOnlyWhoDat whoDat) throws IOException {
        saveWhoDat(whoDat, filePath);
    }

    /**
     * Similar to {@link #saveWhoDat(ReadOnlyWhoDat)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWhoDat(ReadOnlyWhoDat whoDat, Path filePath) throws IOException {
        requireNonNull(whoDat);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWhoDat(whoDat), filePath);
    }

}

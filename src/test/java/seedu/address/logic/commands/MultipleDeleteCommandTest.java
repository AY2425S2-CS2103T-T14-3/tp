package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MultipleDeleteCommandTest {

    @Test
    public void test_getCombinedStringFromListOfString_getCombinedString() {
        List<String> input = new LinkedList<>();
        String expected = "";
        assertEquals(expected, MultipleDeleteCommand.getCombinedStringFromListOfString(input));

        input.add("Hello");
        input.add("Hello1");
        input.add("Hello2");
        expected = "Hello, Hello1, Hello2";
        assertEquals(expected, MultipleDeleteCommand.getCombinedStringFromListOfString(input));
    }
}

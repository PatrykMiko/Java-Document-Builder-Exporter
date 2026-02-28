package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for single list item rendering (<li> tags).
 */
class ListItemTest {

    @Test
    public void writeHTML() throws Exception {
        String content = "Aikido";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new ListItem(content).writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<li>"));
        assertTrue(result.contains(content));
        assertTrue(result.contains("</li>"));
    }
}
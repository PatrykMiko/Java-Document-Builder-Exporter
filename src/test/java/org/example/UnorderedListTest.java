package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the UnorderedList component (<ul> tags).
 */
class UnorderedListTest {

    @Test
    public void writeHTML() throws Exception {
        UnorderedList unorderedList = new UnorderedList()
                .addItem(new ListItem("Python"))
                .addItem(new ListItem("Java"))
                .addItem(new ListItem("SQL"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        unorderedList.writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li>Python</li>"));
        assertTrue(result.contains("<li>Java</li>"));
        assertTrue(result.contains("<li>SQL</li>"));
        assertTrue(result.contains("</ul>"));
    }
}
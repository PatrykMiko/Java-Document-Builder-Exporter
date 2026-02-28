package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the polymorphic ParagraphWithList class.
 * Verifies that it renders both the paragraph text and the nested <ul> list.
 */
class ParagraphWithListTest {

    @Test
    public void writeHTML() throws Exception {
        ParagraphWithList paragraphWithList = new ParagraphWithList("Umiejętności")
                .addListItem("Python")
                .addListItem("Java")
                .addListItem("SQL");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        paragraphWithList.writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<p>Umiejętności</p>"));
        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("<li>Python</li>"));
        assertTrue(result.contains("<li>Java</li>"));
        assertTrue(result.contains("<li>SQL</li>"));
        assertTrue(result.contains("</ul>"));
    }
}
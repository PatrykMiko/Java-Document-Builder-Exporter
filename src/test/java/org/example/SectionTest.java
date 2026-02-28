package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Section component.
 * Ensures sections correctly render their <h2> titles and child paragraphs.
 */
class SectionTest {

    @Test
    public void writeHTML() throws Exception {
        String title = "Umiejętności";
        Section section = new Section(title)
                .addParagraph("Python")
                .addParagraph("Java")
                .addParagraph("SQL");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        section.writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<h2>"));
        assertTrue(result.contains(title));
        assertTrue(result.contains("</h2>"));
        assertTrue(result.contains("<p>Python</p>"));
        assertTrue(result.contains("<p>Java</p>"));
        assertTrue(result.contains("<p>SQL</p>"));
    }
}
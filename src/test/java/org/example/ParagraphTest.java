package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for basic Paragraph rendering (<p> tags).
 */
class ParagraphTest {

    @Test
    public void writeHTML() throws Exception {
        String content = "Aikido";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Paragraph(content).writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<p>"));
        assertTrue(result.contains(content));
        assertTrue(result.contains("</p>"));
    }
}
package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the main Document class.
 * Verifies the overall HTML structure generation, including headers, body, and sections.
 */
class DocumentTest {

    /**
     * Tests standard HTML generation for a document containing a photo and multiple sections.
     */
    @Test
    public void writeHTML() throws Exception {
        String title = "CV";
        Document document = new Document(title)
                .addSection(new Section("Wykształcenie"))
                .addSection(new Section("Umiejętności"))
                .setPhoto("jan-kowalski.png");

        // Intercepting PrintStream output to a byte array for assertion
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        document.writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Assertions verifying the presence of essential HTML tags
        assertTrue(result.contains("<html><head><title>" + title + "</title></head><body>"));
        assertTrue(result.contains("<h1>" + title + "</h1>"));
        assertTrue(result.contains("<img src=\"jan-kowalski.png\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>"));
        assertTrue(result.contains("<h2>Wykształcenie</h2>"));
        assertTrue(result.contains("<h2>Umiejętności</h2>"));
        assertTrue(result.contains("</body></html>"));
    }

    /**
     * Tests HTML generation when the document lacks a photo element.
     * Ensures no empty <img> tags are rendered.
     */
    @Test
    @DisplayName("CV without photo")
    public void writeHTMLWithoutPhoto() throws Exception {
        Document document = new Document("CV bez zdjęcia")
                .addSection(new Section("Doświadczenie"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        document.writeHTML(ps);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<h2>Doświadczenie</h2>"));
        assertFalse(result.contains("<img")); // Verify image tag is absent
    }
}
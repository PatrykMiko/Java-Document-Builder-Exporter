package org.example;

import java.io.PrintStream;

/**
 * Base class representing a simple text paragraph.
 * Renders as a <p> tag in HTML.
 */
public class Paragraph {
    String content;

    Paragraph(String content){
        this.content = content;
    }

    Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n", content);
    }
}

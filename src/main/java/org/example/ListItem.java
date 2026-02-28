package org.example;

import java.io.PrintStream;

/**
 * Represents a single item within an unordered list (<li>).
 */
public class ListItem {

    String content;

    ListItem(String content){
        this.content = content;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>\n", content);
    }
}
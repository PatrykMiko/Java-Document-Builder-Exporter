package org.example;

import java.io.PrintStream;

/**
 * Represents an image element within the document.
 */
public class Photo {

    String url;

    Photo(String url){
        this.url = url;
    }

    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n", url);
    }
}
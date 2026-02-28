package org.example;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an HTML unordered list (<ul>).
 */
public class UnorderedList {

    List<ListItem> items = new ArrayList<>();

    UnorderedList addItem(ListItem item){
        items.add(item);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.println("<ul>");
        for (ListItem item : items)
            item.writeHTML(out);
        out.println("</ul>");
    }
}

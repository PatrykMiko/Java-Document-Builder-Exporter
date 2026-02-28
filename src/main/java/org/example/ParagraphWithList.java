package org.example;

import java.io.PrintStream;

/**
 * An extension of the standard Paragraph that also contains an unordered list.
 * Demonstrates inheritance and method overriding.
 */
class ParagraphWithList extends Paragraph {

    UnorderedList list = new UnorderedList();

    ParagraphWithList() {
        super("");
    }

    ParagraphWithList(String content) {
        super(content);
    }

    @Override
    ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }

    /**
     * Adds a new item to the underlying unordered list.
     */
    ParagraphWithList addListItem(String item) {
        list.addItem(new ListItem(item));
        return this;
    }

    /**
     * Overrides the base method to render both the paragraph text and the attached list.
     */
    @Override
    void writeHTML(PrintStream out) {
        super.writeHTML(out); // Render the text content first
        list.writeHTML(out);  // Then render the list items
    }
}

package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The main container for the document structure.
 * Implements the Builder pattern (Fluent API) to allow method chaining
 * for clean and readable document construction.
 */
public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(String title) {
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this; // Returning 'this' enables method chaining
    }

    Document setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
        return this;
    }

    /**
     * Creates a new Section, adds it to the document, and returns it.
     * This allows adding paragraphs directly to the newly created section.
     */
    Section addSection(String sectionTitle){
        Section section = new Section(sectionTitle);
        sections.add(section);
        return section;
    }

    Document addSection(Section s){
        sections.add(s);
        return this;
    }

    /**
     * Renders the entire document structure into a basic HTML format.
     * @param out The PrintStream to write the HTML output to (e.g., System.out or a File).
     */
    void writeHTML(PrintStream out){
        out.println("<html><head><title>" + title + "</title></head><body>");
        out.printf("<h1>%s</h1>\n", title);
        if (photo != null) {
            photo.writeHTML(out);
        }
        for(Section section : sections){
            section.writeHTML(out);
        }
        out.println("</body></html>");
    }

    /**
     * Serializes the Document object into a formatted JSON string.
     * Uses Gson and a custom RuntimeTypeAdapterFactory to handle polymorphic serialization
     * (distinguishing between Paragraph and ParagraphWithList).
     */
    String toJson(){
        Section.RuntimeTypeAdapterFactory<Paragraph> adapter =
                Section.RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.toJson(this);
    }

    /**
     * Deserializes a JSON string back into a Document object.
     */
    Document fromJson(String jsonString){
        Section.RuntimeTypeAdapterFactory<Paragraph> adapter =
                Section.RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
        return gson.fromJson(jsonString, Document.class);
    }
}

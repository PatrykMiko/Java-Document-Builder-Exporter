package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Demonstration class for the Document Builder.
 * Generates a sample CV, outputs it to an HTML file, and serializes it to JSON.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Building the document structure using the Fluent API
        Document cv = new Document("Patryk Mikos - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");

        cv.addSection("Wykształcenie")
                .addParagraph("2020-2024 IV LO im. Stanisława Staszica klasa: mat-fiz-inf dwujęzyczny")
                .addParagraph("2024-2028 AGH kierunek: Informatyka i Systemy Inteligentne")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Google AI w biznesie")
                                .addListItem("Eyetracking, AI, EEG w neurobadaniach")
                                .addListItem("Aikido 1 DAN")
                );

        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("Python")
                                .addListItem("Java")
                                .addListItem("SQL")
                );

        cv.addSection("Organizacje")
                .addParagraph("URSS: Organizacja wydarzeń m.in. Juwenaliów Krakoskich 2025, Mecz Charytatywny UEKxAGH, AGH Freshmen. Członek Kapituły Lauru Dydaktyka 2025. Koordynator główny Silent Disco na lodzie.")
                .addParagraph("MechaniCAD Project Hydrive: Badanie nad napędem wodorowym wykorzystywanym w bolidzie. Wykorzystanie metod uczenia maszynowego w symulacji badań.")
                .addParagraph("Glider: Automatyczna detekcja chorób skórnych.");

        // Export to HTML file
        cv.writeHTML(new PrintStream("cv.html", "UTF-8"));

        // Print JSON representation to console and export to JSON file
        System.out.println(cv.toJson());
        PrintStream to_json = new PrintStream(new File("cv.json"), "UTF-8");
        to_json.append(cv.toJson());
    }
}
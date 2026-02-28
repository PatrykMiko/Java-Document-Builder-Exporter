# Java Document Builder & Exporter

An object-oriented Java library designed for dynamically constructing structured documents (such as Resumes, Reports, or Articles) and exporting them into multiple formats.



This project heavily utilizes the **Builder Pattern (Fluent API)** to provide a clean, readable syntax for nested document creation, and the **Composite Pattern** to manage the hierarchy of document elements (Sections, Paragraphs, Lists).

## 🚀 Key Features

* **Fluent API:** Construct complex documents via an intuitive method-chaining interface.
* **HTML Export:** Render the entire object tree into well-formed HTML tags (`<h1>`, `<h2>`, `<p>`, `<ul>`, `<img>`).
* **JSON Serialization & Deserialization:** Convert documents to and from JSON.
* **Polymorphic Type Handling:** Integrates Google's `gson-extras` (`RuntimeTypeAdapterFactory`) to properly serialize and deserialize polymorphic classes (differentiating between a standard `Paragraph` and a `ParagraphWithList`).
* **Modular Structure:** Easily extensible to add new document elements (e.g., Tables, Links) by extending the base components.

## 🛠️ Technology Stack
* **Java 17+** (Compatible with earlier versions)
* **Gson (Google)** for JSON processing
* **JUnit 5 (Jupiter)** for comprehensive unit testing

## 💻 Code Examples

### 1. Building and Exporting a Document to HTML
The Fluent API allows you to build the document tree smoothly without cluttering the code with multiple variable declarations.

```java
Document cv = new Document("John Doe - CV")
    .setPhoto("profile-pic.png");

// Creating a section with standard paragraphs
cv.addSection("Education")
    .addParagraph("2020-2024 High School of Science")
    .addParagraph("2024-2028 University of Technology, Computer Science");

// Mixing paragraphs with lists using Polymorphism
cv.addSection("Skills")
    .addParagraph(
        new ParagraphWithList().setContent("Programming Languages")
            .addListItem("Java")
            .addListItem("Python")
            .addListItem("SQL")
    );

// Export to file
cv.writeHTML(new PrintStream("cv.html", "UTF-8"));

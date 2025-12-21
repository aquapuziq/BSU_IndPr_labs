package org.example.Base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books  = new ArrayList<>();

    public boolean initLibrary(String xmlFile, String xsdFile){
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(new File(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(xmlFile));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("book");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    Book book = new Book(
                            Integer.parseInt(e.getAttribute("id")),
                            e.getElementsByTagName("title").item(0).getTextContent(),
                            e.getElementsByTagName("author").item(0).getTextContent(),
                            Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent()),
                            Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent()),
                            Integer.parseInt(e.getElementsByTagName("quantity").item(0).getTextContent()),
                            Integer.parseInt(e.getElementsByTagName("instock").item(0).getTextContent()),
                            e.getElementsByTagName("category").item(0).getTextContent()
                    );
                    books.add(book);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Book> getBooks() { return books; }

    public void addBook(Book book) { books.add(book); }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) if (b.getAuthor().equalsIgnoreCase(author)) result.add(b);
        return result;
    }

    public List<Book> searchByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) if (b.getYear() == year) result.add(b);
        return result;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) if (b.getCategory().equalsIgnoreCase(category)) result.add(b);
        return result;
    }

    public void changePrice(int id, double newPrice) {
        for (Book b : books) if (b.getId() == id) b.setPrice(newPrice);
    }

    public boolean issueBook(int id) {
        for (Book b : books) {
            if (b.getId() == id && b.getInStock() > 0) {
                b.setInStock(b.getInStock() - 1);
                return true;
            }
        }
        return false;
    }
}

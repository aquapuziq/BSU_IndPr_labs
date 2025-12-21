package org.example.Service;

import org.example.Base.Book;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    private final File xml = new File("src/main/resources/xml/library.xml");

    private Document load() throws Exception {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
    }

    private void save(Document doc) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(doc), new StreamResult(xml));
    }

    public List<Book> getAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        Document doc = load();

        NodeList list = doc.getElementsByTagName("book");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);
            books.add(parse(e));
        }
        return books;
    }

    public void addBook(Book b) throws Exception {
        Document doc = load();
        Element root = doc.getDocumentElement();

        Element book = doc.createElement("book");
        book.setAttribute("id", String.valueOf(b.getId()));

        create(doc, book, "title", b.getTitle());
        create(doc, book, "author", b.getAuthor());
        create(doc, book, "year", String.valueOf(b.getYear()));
        create(doc, book, "price", String.valueOf(b.getPrice()));
        create(doc, book, "quantity", String.valueOf(b.getQuantity()));
        create(doc, book, "instock", String.valueOf(b.getInStock()));
        create(doc, book, "category", b.getCategory());

        root.appendChild(book);
        save(doc);
    }

    public void updatePrice(int id, double price) throws Exception {
        Document doc = load();
        XPath xp = XPathFactory.newInstance().newXPath();

        Node node = (Node) xp.evaluate(
                "//book[@id='" + id + "']/price",
                doc, XPathConstants.NODE);

        node.setTextContent(String.valueOf(price));
        save(doc);
    }

    public void decrementStock(int id) throws Exception {
        Document doc = load();
        XPath xp = XPathFactory.newInstance().newXPath();

        Node node = (Node) xp.evaluate(
                "//book[@id='" + id + "']/instock",
                doc, XPathConstants.NODE);

        int value = Integer.parseInt(node.getTextContent());
        node.setTextContent(String.valueOf(value - 1));
        save(doc);
    }

    public List<Book> search(String xpathExpr) throws Exception {
        List<Book> res = new ArrayList<>();
        Document doc = load();
        XPath xp = XPathFactory.newInstance().newXPath();

        NodeList nodes = (NodeList) xp.evaluate(
                xpathExpr, doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            res.add(parse((Element) nodes.item(i)));
        }
        return res;
    }

    private Book parse(Element e) {
        return new Book(
                Integer.parseInt(e.getAttribute("id")),
                e.getElementsByTagName("title").item(0).getTextContent(),
                e.getElementsByTagName("author").item(0).getTextContent(),
                Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent()),
                Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("quantity").item(0).getTextContent()),
                Integer.parseInt(e.getElementsByTagName("instock").item(0).getTextContent()),
                e.getElementsByTagName("category").item(0).getTextContent()
        );
    }

    private void create(Document d, Element p, String n, String v) {
        Element e = d.createElement(n);
        e.setTextContent(v);
        p.appendChild(e);
    }
}


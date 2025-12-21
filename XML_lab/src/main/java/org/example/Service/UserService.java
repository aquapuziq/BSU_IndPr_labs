package org.example.Service;

import org.example.Base.User;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

@Service
public class UserService {

    private final File xml = new File("src/main/resources/xml/users.xml");

    private Document load() throws Exception {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
    }

    private void save(Document doc) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(doc), new StreamResult(xml));
    }

    public User authenticate(String login, String password) throws Exception {
        Document doc = load();
        NodeList users = doc.getElementsByTagName("user");

        for (int i = 0; i < users.getLength(); i++) {
            Element user = (Element) users.item(i);
            if (user.getElementsByTagName("login").item(0).getTextContent().equals(login)
                    && user.getElementsByTagName("password").item(0).getTextContent().equals(password)) {

                return new User(
                        Integer.parseInt(user.getAttribute("id")),
                        login,
                        password,
                        user.getElementsByTagName("role").item(0).getTextContent()
                );
            }
        }
        return null;
    }

    public void addIssuedBook(int userId, int bookId) throws Exception {
        Document doc = load();
        NodeList users = doc.getElementsByTagName("user");

        for (int i = 0; i < users.getLength(); i++) {
            Element u = (Element) users.item(i);
            if (Integer.parseInt(u.getAttribute("id")) == userId) {
                Element issued = (Element) u.getElementsByTagName("issuedBooks").item(0);
                Element b = doc.createElement("bookId");
                b.setTextContent(String.valueOf(bookId));
                issued.appendChild(b);
            }
        }
        save(doc);
    }
}

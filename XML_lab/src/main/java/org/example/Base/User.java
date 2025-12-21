package org.example.Base;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String login;
    private String password;
    private String role;
    private List<Integer> issuedIds = new ArrayList<>();

    public User(int id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public List<Integer> getIssuedIds() { return issuedIds; }

    public void addBook(int bookId) {
        issuedIds.add(bookId);
    }
}

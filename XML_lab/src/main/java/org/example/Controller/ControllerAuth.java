package org.example.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.Base.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerAuth {

    @Autowired
    private UserService users;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String auth(@RequestParam String login, @RequestParam String password, HttpSession s) throws Exception {

        User u = users.authenticate(login, password);
        if (u == null) return "login";

        s.setAttribute("user", u);
        return u.getRole().equals("LIBRARIAN")
                ? "redirect:/librarian" : "redirect:/reader";
    }
}


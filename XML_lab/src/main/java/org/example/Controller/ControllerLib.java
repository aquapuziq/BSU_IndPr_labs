package org.example.Controller;
import org.example.Service.LibraryService;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/librarian")
public class ControllerLib {

    @Autowired
    private LibraryService lib;
    @Autowired
    private UserService users;

    @GetMapping
    public String home(Model m) throws Exception {
        m.addAttribute("books", lib.getAllBooks());
        return "librarian";
    }

    @PostMapping("/price")
    public String price(@RequestParam int id, @RequestParam double price) throws Exception {

        lib.updatePrice(id, price);
        return "redirect:/librarian";
    }

    @PostMapping("/issue")
    public String issue(@RequestParam int bookId, @RequestParam int userId) throws Exception {

        lib.decrementStock(bookId);
        users.addIssuedBook(userId, bookId);
        return "redirect:/librarian";
    }
}

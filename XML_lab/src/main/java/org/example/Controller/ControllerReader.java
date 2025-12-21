package org.example.Controller;

import org.example.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reader")
public class ControllerReader {

    @Autowired
    private LibraryService lib;

    @GetMapping
    public String home(Model m) throws Exception {
        m.addAttribute("books", lib.getAllBooks());
        return "reader";
    }

    @GetMapping("/search")
    public String search(@RequestParam String expr, Model m) throws Exception {
        m.addAttribute("books", lib.search(expr));
        return "reader";
    }
}

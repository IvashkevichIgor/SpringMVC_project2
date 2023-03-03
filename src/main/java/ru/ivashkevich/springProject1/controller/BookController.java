package ru.ivashkevich.springProject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivashkevich.springProject1.dao.BookDAO;
import ru.ivashkevich.springProject1.model.Book;
import ru.ivashkevich.springProject1.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("books", bookDAO.getAllBooks());

        return "books/all-books";
    }

    @GetMapping("/{id}")
    public String showBookById(@PathVariable int id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));

        return "books/book";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }
}

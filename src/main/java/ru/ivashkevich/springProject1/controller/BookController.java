package ru.ivashkevich.springProject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivashkevich.springProject1.dao.BookDAO;
import ru.ivashkevich.springProject1.dao.PersonDAO;
import ru.ivashkevich.springProject1.model.Book;
import ru.ivashkevich.springProject1.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("books", bookDAO.getAllBooks());

        return "books/all-books";
    }

    @GetMapping("/{id}")
    public String showBookById(@ModelAttribute("newOwner") Person person, @PathVariable int id, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("people", personDAO.getAllPeople());
        model.addAttribute("owner", bookDAO.getBookOwner(id));

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

        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setOwner")
    public String setOwner(@ModelAttribute("book") Book book, @ModelAttribute("owner") Person person, @PathVariable("id") int id){
        bookDAO.setPersonId(id, person);

        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/deleteOwner")
    public String deleteOwner(@ModelAttribute("book") Book book, @PathVariable("id") int id){
        bookDAO.deletePersonId(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}

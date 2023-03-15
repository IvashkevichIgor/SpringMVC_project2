package ru.ivashkevich.spring_project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivashkevich.spring_project2.models.Book;
import ru.ivashkevich.spring_project2.models.Person;
import ru.ivashkevich.spring_project2.services.BooksService;
import ru.ivashkevich.spring_project2.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("books", booksService.getAllBooks());

        return "books/all-books";
    }

    @GetMapping("/{id}")
    public String showBookById(@ModelAttribute("person") Person person, @PathVariable int id, Model model){
        Book book = booksService.getBookById(id);
        model.addAttribute("book", book);

        if (book.getOwner() != null){
            model.addAttribute("owner", book.getOwner());
        }
        else {
            model.addAttribute("people", peopleService.getAllPeople());
        }

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

        booksService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksService.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String setOwner(@ModelAttribute("owner") Person person, @PathVariable("id") int id){
        booksService.assignOwner(id, person);

        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/release")
    public String deleteOwner(@PathVariable("id") int id){
        booksService.releaseBookFromOwner(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }
}

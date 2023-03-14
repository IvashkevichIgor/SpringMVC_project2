package ru.ivashkevich.spring_project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivashkevich.spring_project2.dao.PersonDAO;
import ru.ivashkevich.spring_project2.model.Person;
import ru.ivashkevich.spring_project2.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String showAllPeople(Model model){
        model.addAttribute("people", personDAO.getAllPeople());

        return "people/all-people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable int id, Model model){
        model.addAttribute("person", personDAO.getPersonById(id));
        model.addAttribute("books", personDAO.getPersonBookList(id));

        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.create(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
package ru.ivashkevich.spring_project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ivashkevich.spring_project2.models.Person;
import ru.ivashkevich.spring_project2.services.PeopleService;
import ru.ivashkevich.spring_project2.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PeopleService peopleService;

    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String showAllPeople(Model model){
        model.addAttribute("people", peopleService.getAllPeople());

        return "people/all-people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable int id, Model model){
        model.addAttribute("person", peopleService.getPersonById(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));

        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.getPersonById(id));
        return "people/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.create(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
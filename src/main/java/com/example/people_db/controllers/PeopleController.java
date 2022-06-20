package com.example.people_db.controllers;

import com.example.people_db.models.Person;
import com.example.people_db.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    //Это означает, что для получения bean-компонента с именем peopleRepository,
    // который автоматически генерируется Spring, мы будем использовать его для обработки данных.
    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping()//список людей
    public String homePage(Model model){
        Iterable<Person> people = peopleRepository.findAll();
        model.addAttribute("people", people);
        return "people/home";
    }

    @GetMapping("/{id}")//обзор человека
    public String personView(@PathVariable("id") Integer id, Model model){
        model.addAttribute("person", peopleRepository.findById(id).orElseThrow());
        return "people/view";
    }

    @GetMapping("/new")//создание человека
    public String peopleNew(@ModelAttribute("person") Person person){
        return"people/new";
    }

    @PostMapping() //отправка формы
    public String createPeople(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "people/new";
        peopleRepository.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")//изменение пользователя
    public String personEdit(Model model, @PathVariable("id") Integer id){
        model.addAttribute("person", peopleRepository.findById(id).orElseThrow());
        return "people/edit";
    }

    @PatchMapping("/{id}")//редактирование людей
    public String personUpdate(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                               @PathVariable("id") Integer id){
        if(bindingResult.hasErrors()) return "people/edit";
        Person updatePerson = peopleRepository.findById(id).orElseThrow();
        updatePerson.setName(person.getName());
        updatePerson.setAge(person.getAge());
        updatePerson.setEmail(person.getEmail());
        peopleRepository.save(updatePerson);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")//удаление по id
    public String personDelete(@PathVariable("id") Integer id){
        peopleRepository.deleteById(id);
        return "redirect:/people";
    }

    @PostMapping("/search")//поиск по имени
    public String search(@RequestParam (value = "name", required = false) String name, Model model){
        model.addAttribute("people", peopleRepository.findByName(name));
        return "people/home";
    }
}

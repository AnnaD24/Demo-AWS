package com.example.demo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@EnableSwagger2
public class DemoController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping(value = "/getallpeople")
    @ResponseBody
    public ResponseEntity<Collection<Person>> getAllPeople()
    {
        return new ResponseEntity<>(peopleService.getAllPeople(), HttpStatus.OK);
    }

    @GetMapping(value = "/getperson/{id}")
    @ResponseBody
    public ResponseEntity getPerson(@PathVariable Integer id)
    {
        if(peopleService.getPerson(id).isPresent())
            return new ResponseEntity<>(peopleService.getPerson(id).get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Person with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/addperson")
    @ResponseBody
    public ResponseEntity<String> addPerson(@RequestBody Person person)
    {
        return new ResponseEntity<>(peopleService.addPerson(person).toString()+" was added", HttpStatus.CREATED);
    }

    @PutMapping(value = "/modifyperson")
    @ResponseBody
    public ResponseEntity<String> modifyPerson(@RequestBody Person person)
    {
        return new ResponseEntity<>(peopleService.modifyPerson(person).toString() + " was modified", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteperson")
    @ResponseBody
    public ResponseEntity<String> deletePerson(@RequestBody Person person)
    {
        if(peopleService.findPerson(person).isPresent()) {
            peopleService.deletePerson(person);
            return new ResponseEntity<>("person with id " + person.getId() + " was removed", HttpStatus.OK);
        }
        return new ResponseEntity<>( person.toString() + " not found", HttpStatus.NOT_FOUND);
    }

    //TODO
    //1. Adauga o noua persoana
    //2. Verif ca s-a adaugat cu getall
    //3. Verif ca s-a adaugat cu getperson
    //4. Update persoana
    //5. Verif ca s-a updatat cu getall
    //6. Verif ca s-a updatat cu getperson
    //7. Sterg persoana
    //8. verif
    //9. verif
}

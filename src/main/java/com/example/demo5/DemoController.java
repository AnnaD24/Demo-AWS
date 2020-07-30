package com.example.demo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DemoController {
    @Autowired
    private PeopleRepository peopleRepository;
    private static List<Person> people = new ArrayList<>();
//    private  List<Person> people1 = Arrays.asList(new Person("a","b",18,"111"));
    static {
        people.add(new Person("a","b",18,111));
        people.add(new Person("c","d",20,222));
        people.add(new Person("e","f",22,333));
        people.add(new Person("g","h",10,444));
    }

    @GetMapping(value = "/demo")
    @ResponseBody
    public ResponseEntity<String> getDemo()
    {
        return new ResponseEntity<String>("mesaj", HttpStatus.OK);
    }


    @GetMapping(value = "/demoint")
    @ResponseBody
    public ResponseEntity<Integer> getDemoInt()
    {
        return new ResponseEntity<Integer>(1234, HttpStatus.OK);
    }

    @GetMapping(value = "/demoperson")
    @ResponseBody
    public ResponseEntity<Person> getDemoPerson()
    {
        return new ResponseEntity<Person>(new Person("ANA","DORA",21,299), HttpStatus.OK);
    }

    @GetMapping(value = "/getallpeople")
    @ResponseBody
    public ResponseEntity<Collection<Person>> getAllPeople()
    {
        return new ResponseEntity<Collection<Person>>(peopleRepository.findAllActiveUsersNative(), HttpStatus.OK);
    }

    @GetMapping(value = "/demopeople")
    @ResponseBody
    public ResponseEntity<List<Person>> getDemoPeople(@RequestParam (value = "age", required = false) Integer age ){

        if(age!=null)
            return new ResponseEntity<>(people.stream().filter(x -> x.getAge()>age).collect(Collectors.toList()), HttpStatus.OK);
        else
            return new ResponseEntity<>(people, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/addperson")
    @ResponseBody
    public ResponseEntity<String> addPerson(@RequestBody Person person)
    {
        if(person!=null){
            people.add(person);
            return new ResponseEntity<>("created", HttpStatus.CREATED);}
        return new ResponseEntity<>("not created", HttpStatus.BAD_REQUEST);
    }


    //TODO
    //update record endpoint
    //remove one record, optional param in fctie de nume/age/etc
    //using docker find a way to run mysql
    //install mySql workbench
    //connection from mysql wb to mysql from docker

    @PutMapping(value = "/modifyperson")
    @ResponseBody
    public ResponseEntity<String> modifyPerson(@RequestBody Person person)
    {
        //considering cnp as unique id
        if(person==null)
            return new ResponseEntity<>("person expected", HttpStatus.BAD_REQUEST);

        int index=people.indexOf(person);
        if(index==-1)
            return new ResponseEntity<>("person not found", HttpStatus.NOT_FOUND);

        people.get(index).updatePerson(person);
        return new ResponseEntity<>("person updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteperson")
    @ResponseBody
    public ResponseEntity<String> deletePerson(@RequestParam (value="cnp", required = false) Integer cnp)
    {
        //is a body needed?
//        if(cnp==null)
//            return new ResponseEntity<>("cnp expected", HttpStatus.BAD_REQUEST);
//
//        int index=people.stream()
//                .filter(x->x.getCnp().equals(cnp))
//                .map(y->people.indexOf(y))
//                .findFirst()
//                .orElse(-1);
//
//        if(index==-1)
//            return new ResponseEntity<>("person not found", HttpStatus.NOT_FOUND);
//
//        people.remove(index);
//        return new ResponseEntity<>("person removed", HttpStatus.OK);
        peopleRepository.deleteById(cnp);
        return new ResponseEntity<>("person removed", HttpStatus.OK);
    }
}

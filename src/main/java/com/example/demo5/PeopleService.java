package com.example.demo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;
    public Collection<Person> getAllPeople(){
        return peopleRepository.findAll();
    }

    public Person addPerson(Person person){
        return peopleRepository.save(person);
    }

    public Person modifyPerson(Person person) {
        return peopleRepository.save(person);
    }

    public void deletePerson(Person person) {
        peopleRepository.delete(person);
    }

    public Optional<Person> findPerson(Person person){
        return peopleRepository.findById(person.getId());
    }

    public Optional<Person> getPerson(Integer id) {
        return peopleRepository.findById(id);
    }
}

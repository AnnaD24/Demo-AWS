package com.example.demo5;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name = "People", schema = "mySchema")
public class Person {
    @Id
    @Column(name="CNP", nullable = false)
    private Integer CNP;
    @Column(name="FirstName", nullable = false)
    private String firstname;
    @Column(name="LastName", nullable = false)
    private String lastname;
    @Column(name="Age", nullable = false)
    private int age;

    public Person(String firstname, String lastname, int age, Integer CNP) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.CNP = CNP;
    }

    public Person() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getCNP() {
        return CNP;
    }

    public void setCNP(Integer cnp) {
        this.CNP = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(CNP, person.CNP);
    }

    public void updatePerson(Person p){
        this.age=p.age;
        this.firstname=p.firstname;
        this.lastname=p.lastname;
    }
}

package com.example.demo5;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "people", schema = "mySchema")
public class Person {
    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return this.id;
    }
}

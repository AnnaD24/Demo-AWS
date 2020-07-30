package com.example.demo5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PeopleRepository extends JpaRepository<Person, Integer>{
    @Query(
            value = "SELECT * FROM mySchema.People",
            nativeQuery = true)
    Collection<Person> findAllActiveUsersNative();
}

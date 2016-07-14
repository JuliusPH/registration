package com.exist.dao;

import com.exist.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>{
    Person findOne(Long id);

    List<Person> findAll();

    Person save(Person person);

    void delete(Person person);
}

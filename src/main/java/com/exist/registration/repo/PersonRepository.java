package com.exist.registration.repo;

import com.exist.registration.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    Person findOne(Long id);

    List<Person> findAll();

    Person save(Person person);

    void delete(Person person);
}

package com.example.people_db.repo;

import com.example.people_db.models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepository extends CrudRepository<Person,Integer> {

    List<Person> findByName(String name);
}

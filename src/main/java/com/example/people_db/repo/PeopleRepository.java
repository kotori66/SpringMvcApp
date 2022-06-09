package com.example.people_db.repo;

import com.example.people_db.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Person,Integer> {

}

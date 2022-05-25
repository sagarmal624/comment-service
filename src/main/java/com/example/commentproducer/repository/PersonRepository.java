package com.example.commentproducer.repository;

import com.example.commentproducer.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    public List<Person> findAllByIdIn(List<String> personIds);
}

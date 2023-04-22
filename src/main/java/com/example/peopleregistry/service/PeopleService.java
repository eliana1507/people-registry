package com.example.peopleregistry.service;

import com.example.peopleregistry.model.Person;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class PeopleService {

    Map<String,Person> personMap = new HashMap<>();

    public Person savePerson(Person person) {
        personMap.put(person.getPnr(), person);

        person.getChildren().forEach(child -> personMap.put(child.getPnr(),
                new Person(
                        child.getPnr(), child.getName(), child.getSpouseName(), child.getAge(), saveChildren(child.getChildren())
                )
        ));
        return person;
    }

    // save children recursively
    private List<Person> saveChildren(List<Person> children) {
        children.forEach(child ->
                personMap.put(child.getPnr(),
                new Person(
                        child.getPnr(), child.getName(), child.getSpouseName(), child.getAge(),
                        saveChildren(child.getChildren())
                ))
        );
        return children;
    }

    public Optional<Person> getPersonByPnr(String pnr) {
        return Optional.ofNullable(personMap.get(pnr));
    }

    public String getOldestChild(String pnr) {
        Person person = personMap.get(pnr);
        Optional<Person> oldest = person.getChildren().stream().max(Comparator.comparing(Person::getAge));
        String nameOldestChild = "";
        if (oldest.isPresent()) {
            nameOldestChild = oldest.get().getName();
        }
        return nameOldestChild;
    }
}

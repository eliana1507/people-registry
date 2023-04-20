package com.example.peopleregistry.service;

import com.example.peopleregistry.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PeopleServiceTest {
    PeopleService service;

    @BeforeEach
    void setUp(){
        service = new PeopleService();
    }

    @Test
    void savePerson() {
        List<Person> children = new ArrayList<>();
        children.add(new Person("198707152837", "Alessio Bianchi", "", 35, new ArrayList<>()));
        children.add(new Person("199004172938", "Carlo Bianchi", "", 33, new ArrayList<>()));

        Person p1 = new Person("195710137865", "Pippo Bianchi",
                "Teresa Bianchi", 65, children);

        Person saved = service.savePerson(p1);

        assertEquals(saved, service.personMap.get(p1.getPnr()));
        assertNotNull(service.getPersonByPnr("198707152837"));
    }

    @Test
    void saveExistingPerson() {
        Person p1 = new Person("196712137165", "Pippo Bianchi",
                "Teresa Bianchi", 65, new ArrayList<>());

        service.savePerson(p1);
        p1.setAge(55);
        service.savePerson(p1);
        assertEquals(p1.getAge(), service.personMap.get(p1.getPnr()).getAge());
    }

    @Test
    void savePersonWithChildrenAndGrandChildren() {
        List<Person> granChildren = new ArrayList<>();
        granChildren.add(new Person("202207152837", "Martina Bianchi", "", 1, new ArrayList<>()));
        granChildren.add(new Person("202004172938", "Domenico Bianchi", "", 3, new ArrayList<>()));
        List<Person> children = new ArrayList<>();
        children.add(new Person("198707152837", "Alessio Bianchi", "", 35, granChildren));
        children.add(new Person("199004172938", "Carlo Bianchi", "", 33, new ArrayList<>()));

        Person p1 = new Person("195710137865", "Pippo Bianchi",
                "Teresa Bianchi", 65, children);

        Person saved = service.savePerson(p1);

        assertTrue(service.personMap.containsKey("202207152837"));
        assertNotNull(service.getPersonByPnr("202004172938"));
    }

    @Test
    void getPersonByPnr() {
        Person marioRossi = new Person("201408093645", "Mario Rossi", "", 9, new ArrayList<>());
        Person ilariaRossi = new Person("201512063645", "Ilaria Rossi", "", 8, new ArrayList<>());

        List<Person> children = new ArrayList<>();
        children.add(marioRossi);
        children.add(ilariaRossi);

        service.personMap.put("199007017865", new Person("199007017865", "Piero Rossi",
                "Alessandra Rossi", 33, children));

        service.personMap.put("201408093645", marioRossi);
        service.personMap.put("201512063645", ilariaRossi);
        Optional<Person> get = service.getPersonByPnr("199007017865");

        assertTrue(get.isPresent());
        assertEquals("Piero Rossi", get.get().getName());

        Optional<Person> child = service.getPersonByPnr("201512063645");
        assertTrue(child.isPresent());
        assertEquals("Ilaria Rossi", child.get().getName());
    }

    @Test
    void personNotFound() {
        Optional<Person> notAPerson = service.getPersonByPnr("notAPnr");
        assertTrue(notAPerson.isEmpty());
    }

    @Test
    void getOldestChild() {
        List<Person> children = new ArrayList<>();
        children.add(new Person("199004172938", "Carlo Bianchi", "", 33, new ArrayList<>()));
        children.add(new Person("201512068645", "Ilaria Bianchi", "", 8, new ArrayList<>()));
        children.add(new Person("198707152837", "Alessio Bianchi", "", 35, new ArrayList<>()));

        Person p1 = new Person("195710137865", "Pippo Bianchi",
                "Teresa Bianchi", 65, children);

        service.savePerson(p1);

        String oldestChild = service.getOldestChild("195710137865");
        assertEquals("Alessio Bianchi", oldestChild);
    }
}

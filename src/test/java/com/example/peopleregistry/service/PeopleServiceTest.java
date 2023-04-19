package com.example.peopleregistry.service;

import com.example.peopleregistry.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeopleServiceTest {
    public static final String PNR = "199007017865";
    PeopleService service = new PeopleService();

    @BeforeEach
    void setUp(){
        Person marioRossi = new Person("201408093645", "Mario Rossi", "", 9, new ArrayList<>());
        Person ilariaRossi = new Person("201512063645", "Ilaria Rossi", "", 8, new ArrayList<>());

        List<Person> children = new ArrayList<>();
        children.add(marioRossi);
        children.add(ilariaRossi);

        service.personMap.put(PNR, new Person("199007017865", "Piero Rossi",
                "Alessandra Rossi", 33, children));

        service.personMap.put("201408093645", marioRossi);
        service.personMap.put("201512063645", ilariaRossi);
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
    void getPersonByPnr() {
        Person get = service.getPersonByPnr(PNR);

        assertNotNull(get);
        assertEquals("Piero Rossi", get.getName());

        Person child = service.getPersonByPnr("201512063645");
        assertEquals("Ilaria Rossi", child.getName());
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

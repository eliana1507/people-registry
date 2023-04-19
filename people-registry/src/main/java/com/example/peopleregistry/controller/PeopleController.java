package com.example.peopleregistry.controller;

import com.example.peopleregistry.model.OldestChildDto;
import com.example.peopleregistry.model.Person;
import com.example.peopleregistry.service.PeopleService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/people")
public class PeopleController {
    PeopleService peopleService = new PeopleService();

    /**
     *
     * @param pnr personnummer of the person of which we want to retrieve the info
     * @return Person object
     */
    @GET
    @Produces("application/json")
    @Path("/{pnr}")
    public Person getPerson(@PathParam("pnr") String pnr) {
        return peopleService.getPersonByPnr(pnr);
    }

    /**
     *
     * @param person person to save
     * @return person saved
     */
    @POST
    @Produces("application/json")
    public Person save(Person person) {
        return peopleService.savePerson(person);
    }

    /**
     *
     * @param pnr the personnummer of the parent
     * @return the personnummer received and the name of the oldest child
     */
    @GET
    @Produces("application/json")
    @Path("/{pnr}/oldestChild")
    public OldestChildDto getOldestChild(@PathParam("pnr") String pnr) {
        return new OldestChildDto(pnr, peopleService.getOldestChild(pnr));
    }
}
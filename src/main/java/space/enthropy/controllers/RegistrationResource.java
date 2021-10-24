package space.enthropy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import space.enthropy.models.Patient;
import space.enthropy.repositories.PatientRepository;
import space.enthropy.services.PatientService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static space.enthropy.models.SaveData.list;
import static space.enthropy.models.SaveData.map;

@ApplicationScoped
@Path("/patient")
public class RegistrationResource {


    @Inject
    PatientService patientService;
    @Inject
    PatientRepository patientRepository;

    @GET
    @Path("/get/{id}")
    public Patient getSingle(@PathParam("id") Long id) {
        return Patient.findById(id);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> get() {
        return Patient.listAll(Sort.by("first_name"));
    }

    @GET
    @Path("/reg")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response reg(@QueryParam("first_name") String first_name,
                        @QueryParam("last_name") String last_name,
                        @QueryParam("gender") String gender,
                        @QueryParam("age") int age,
                        @QueryParam("cancer_type") String cancer_type,
                        @QueryParam("stage") int stage,
                        @QueryParam("radiation_therapy") boolean radiation_therapy,
                        @QueryParam("height") double height,
                        @QueryParam("weight") double weight) {
        Patient p = new Patient(first_name, last_name, gender, age, cancer_type, stage, radiation_therapy, height, weight);
        Log.info(p);
        p.persist();
        return Response.seeOther(URI.create("/login.html")).build();
    }

    @GET
    @Path("/parse1/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String parse1(@PathParam("id") String login) throws JsonProcessingException {
        System.out.println("STARTED");
        Patient p = patientRepository.findByLogin(login);
        list = new ArrayList<>(List.of(p.getCancer_type(), "cancer", "follow-up"));
        String result = patientService.response(list, 20); // показываю врачу ([tags...])
        Log.info("Result it here " + result);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, new TypeReference<>() {
        });
        System.out.println("Map here " + map);
        list.addAll(map.get("keywords").subList(0, 2));
        System.out.println("List is here " + list);
        return map.toString();
    }

    @GET
    @Path("/parse2/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String parse2(@PathParam("id") String login) throws JsonProcessingException {
        System.out.println("STARTED");
        Patient p = patientRepository.findByLogin(login);
        System.out.println("List2 " + list);
        System.out.println("Map2 " + map);
        String result = patientService.response(list, 10); // показываю врачу ([tags...])
        Log.info("Result it here2 " + result);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(result, new TypeReference<>() {
        });
        System.out.println("Map here2 " + map);
        System.out.println("Keywords");
        System.out.println(map.get("keywords"));
        System.out.println("Links");
        System.out.println(map.get("links"));
        return map.toString();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Patient entity = Patient.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }


}

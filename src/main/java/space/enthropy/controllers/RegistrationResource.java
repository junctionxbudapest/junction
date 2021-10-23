package space.enthropy.controllers;

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
import java.util.List;

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
                        @QueryParam("radiation_therapy") boolean radiation_therapy,
                        @QueryParam("height") double height,
                        @QueryParam("weight") double weight) {
        Patient p = new Patient(first_name, last_name, gender, age, cancer_type, radiation_therapy, height, weight);
        Log.info(patientService.response()); // показываю врачу ([tags...])
        patientRepository.updateStage(1);
        Log.info(p);
        p.persist();
        return Response.created(URI.create("/patient/" + p.id)).build();
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

    @PUT
    @Path("/{id}")
    @Transactional
    public Patient update(@PathParam("id") Long id, Patient p) {
        Patient entity = Patient.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity = p;

        return entity;
    }


}

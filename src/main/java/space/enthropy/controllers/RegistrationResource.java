package space.enthropy.controllers;

import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import space.enthropy.models.Patient;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
@Path("/patient")
public class RegistrationResource {

    @GET
    @Path("/get/{id}")
    public Patient getSingle(@PathParam("id") Long id) {
        return Patient.findById(id);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Patient> get() {
        return Patient.listAll(Sort.by("first_name"));
    }

    @GET
    @Path("/reg")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response reg(@QueryParam("first_name") String first_name,
                        @QueryParam("last_name") String last_name,
                        @QueryParam("age") int age) {
        Patient p = new Patient(first_name, last_name, age);
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

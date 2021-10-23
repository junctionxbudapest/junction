package space.enthropy.controllers;

import io.quarkus.logging.Log;
import space.enthropy.models.Form;
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

@ApplicationScoped
@Path("/form")
public class FormResource {
    @Inject
    PatientRepository patientRepository;
    @Inject
    PatientService patientService;

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response add(@QueryParam("mood") int mood,
                        @QueryParam("sleep") String sleep,
                        @QueryParam("weight") double weight,
                        @QueryParam("blood_pressure") double blood_pressure,
                        @QueryParam("pulse") double pulse,
                        @QueryParam("skin_change") int skin_change,
                        @QueryParam("hair_loss") boolean hair_loss,
                        @QueryParam("pain_chest") boolean pain_chest,
                        @QueryParam("custom_symptoms") String custom_symptoms,
                        @QueryParam("id") Long id) {
        Patient p = patientRepository.findByIdOptional(id).orElseThrow();
        Form form = new Form(mood, sleep, weight, blood_pressure, pulse, skin_change, hair_loss, pain_chest, custom_symptoms, p);
        Log.info(form);
        form.persist();
        // Log.info(patientService.response(p.getCancer_type(), 20));
        return Response.created(URI.create("/form/" + form.id)).build();
    }

    @GET
    @Path("/stage")
    public Response stage(@QueryParam("id") Long id) {
        Patient p = Patient.findById(id);
        if (p.getStage() >= 3) {
            p.setStage(1);
        } else {
            p.setStage(p.getStage() + 1);
        }
        update(p.id);
        return Response.ok().build();
    }

    public Patient update(Long id) {
        Patient entity = Patient.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.setStage(entity.getStage() + 1);
        Patient.persist(entity);
        return entity;
    }
}

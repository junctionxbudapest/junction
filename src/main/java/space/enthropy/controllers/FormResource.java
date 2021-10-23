package space.enthropy.controllers;

import io.quarkus.logging.Log;
import space.enthropy.models.Form;
import space.enthropy.models.Patient;
import space.enthropy.repositories.PatientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@ApplicationScoped
@Path("/form")
public class FormResource {
    @Inject
    PatientRepository patientRepository;

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response add(@QueryParam("rateOfCondition") int rateOfCondition,
                        @QueryParam("rateOfDeceaseIncreasing") int rateOfDeceaseIncreasing,
                        @QueryParam("rateOfMedicalCure") int rateOfMedicalCure,
                        @QueryParam("description") String description,
                        @QueryParam("id") Long id) {
        Patient p = patientRepository.findByIdOptional(id).orElseThrow();
        Form form = new Form(rateOfCondition, rateOfCondition, rateOfMedicalCure, description, p);
        Log.info(form);
        form.persist();
        return Response.created(URI.create("/form/" + form.id)).build();

    }
}

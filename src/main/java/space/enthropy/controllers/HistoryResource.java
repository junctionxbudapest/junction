package space.enthropy.controllers;

import io.quarkus.logging.Log;
import space.enthropy.models.History;
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
@Path("/history")
public class HistoryResource {
    @Inject
    PatientRepository patientRepository;

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public Response add(@QueryParam("historyOfDecease") String historyOfDecease,
                        @QueryParam("id") Long id) {
        if (History.find("patient_id", id).count() >= 1) {
            return Response.serverError().build();
        }
        Patient p = patientRepository.findByIdOptional(id).orElseThrow();
        History history = new History(historyOfDecease, p);
        Log.info(history);
        history.persist();
        return Response.created(URI.create("/history/" + history.id)).build();

    }

}

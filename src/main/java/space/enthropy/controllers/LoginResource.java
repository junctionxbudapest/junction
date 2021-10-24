package space.enthropy.controllers;

import space.enthropy.models.Patient;
import space.enthropy.repositories.PatientRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;

@ApplicationScoped
@Path("/login")
public class LoginResource {
    @Inject
    PatientRepository patientRepository;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response login(@QueryParam("login") String login) {
        System.out.println(login);
        Patient p = patientRepository.findByLogin(login);
        System.out.println(p);
        if (p == null) {
            System.out.println("First got");
            return Response.seeOther(URI.create("/login.html")).build();
        }
        if (login.equals(p.getLogin())) {
            NewCookie cookie1 = new NewCookie("login", login);
            NewCookie cookie2 = new NewCookie("id", p.id.toString());
            return Response.seeOther(URI.create("/follow.html")).cookie(cookie1, cookie2).build();
        }
        System.out.println("Nothing got");
        return Response.seeOther(URI.create("/login.html")).build();
    }
}

package space.enthropy.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PatientService {

    @SneakyThrows
    public String response(ArrayList<String> types, int keywoard) {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://20.71.140.143:6666/get_search_keywords/");
        HashMap<String, Object> data = new HashMap<>();


        data.put("prev_keywords", types);
        data.put("n_keywords", keywoard);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        System.out.println(json);
//        Response response = target.request().post(Entity.json(json));
        CompletionStage<Response> r = target.request().rx().post(Entity.json(json));
        Response response = r.toCompletableFuture().get();
        // CompletionStage<Response> r = target.request().rx().get();
        return response.readEntity(String.class);
    }
}

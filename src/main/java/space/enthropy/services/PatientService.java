package space.enthropy.services;

import lombok.SneakyThrows;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PatientService {

    @SneakyThrows
    public String response() {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target("http://20.71.140.143:6666/top_texts");
//        Map<String, String> data = new HashMap<>();
//        data.put("clientId", "6d3a8dc7af90872a6679e1a18f9aab09");
//        data.put("clientSecret", "5b89a484a36234c500360f91eb03c6c149b4e23bce2a213c2c91ace83b836c6f");
//        Response r = target.request().post(Entity.entity(data, MediaType.APPLICATION_JSON));
        CompletionStage<Response> r = target.request().rx().get();
        return r.toCompletableFuture().get().readEntity(String.class);
    }
}

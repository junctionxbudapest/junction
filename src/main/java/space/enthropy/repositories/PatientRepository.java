package space.enthropy.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import space.enthropy.models.Patient;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {

    public List<Patient> findByFirstName(String first_name) {
        return list("first_name", first_name);
    }

    public List<Patient> findByAge(int age) {
        return list("age", age);
    }

    public List<Patient> findByLastName(String last_name) {
        return list("last_name", last_name);
    }

    public boolean hasLogin(String login) {
        return find("login", login).count() >= 1;
    }
}

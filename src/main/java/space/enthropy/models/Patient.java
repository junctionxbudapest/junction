package space.enthropy.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Patient extends PanacheEntity {
    private String login;
    private String first_name;
    private String last_name;
    private int age;
    private final String cancer_type = CancerType.LUNGS.name().toLowerCase();

    public Patient(String first_name, String last_name, int age) {
        if (age < 1 || age > 100) { // check front-end
            throw new NotFoundException();
        }
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public Patient() {

    }

    @Override
    public String toString() {
        return "Patient{" +
                "login='" + login + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", cancer_type='" + cancer_type + '\'' +
                '}';
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient_form", cascade = CascadeType.ALL)
    private final List<Form> forms = new ArrayList<>();

    @OneToOne(mappedBy = "patient_history", cascade = CascadeType.ALL)
    private History history;

    public History getHistory() {
        return history;
    }

    public List<Form> getForms() {
        return forms;
    }


    @PrePersist
    private void ensureId() {
        this.login = UUID.randomUUID().toString().substring(0, 6);
    }

}

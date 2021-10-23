package space.enthropy.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Patient extends PanacheEntity {
    private String login;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private String cancer_type;
    private boolean radiation_therapy;
    private double height;
    private double weight;
    @Setter
    private int stage = 1;


    public Patient(String first_name, String last_name, String gender, int age, String cancer_type, boolean radiation_therapy, double height, double weight) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender.toLowerCase();
        this.age = age;
        this.cancer_type = cancer_type.toLowerCase();
        this.radiation_therapy = radiation_therapy;
        this.height = height;
        this.weight = weight;
    }

    public Patient() {

    }

    @Override
    public String toString() {
        return "Patient{" +
                "login='" + login + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", cancer_type='" + cancer_type + '\'' +
                ", radiation_therapy=" + radiation_therapy +
                ", height=" + height +
                ", weight=" + weight +
                ", stage=" + stage +
                ", forms=" + forms +
                '}';
    }

    @OneToMany(mappedBy = "patient_form", cascade = CascadeType.ALL)
    private final List<Form> forms = new ArrayList<>();

//    @OneToOne(mappedBy = "patient_history", cascade = CascadeType.ALL)
//    private History history;
//
//    public History getHistory() {
//        return history;
//    }

    public List<Form> getForms() {
        return forms;
    }


    @PrePersist
    private void ensureId() {
        this.login = UUID.randomUUID().toString().substring(0, 8);
        if (find("login", this.login).count() >= 1) {
            this.login = UUID.randomUUID().toString().substring(0, 8);
        }
    }

}

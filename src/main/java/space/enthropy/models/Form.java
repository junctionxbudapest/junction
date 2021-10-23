package space.enthropy.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.NotFoundException;


@Entity
@Table(name = "form")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Form extends PanacheEntity {
    // Врач создает аккаунт пациенту:
    // Имя пациента
    // Фамилия пациента
    // Возраст
    // Extra Info
    //

    int rateOfCondition; // 0-10
    int rateOfDeceaseIncreasing; // 0-10
    int rateOfMedicalCure; // 0-10
    String description; //

    @ManyToOne(cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "patient_id")
    private Patient patient_form;

    public Form() {

    }

    public Patient getPatient_form() {
        return patient_form;
    }

    public Form(int rateOfCondition, int rateOfDeceaseIncreasing, int rateOfMedicalCure, String description, Patient patient_form) {
        if ((rateOfCondition < 1 || rateOfCondition > 10)
                || (rateOfDeceaseIncreasing < 1 || rateOfDeceaseIncreasing > 10)
                || (rateOfMedicalCure < 1 || rateOfMedicalCure > 10)) {
            throw new NotFoundException();
        }
        this.rateOfCondition = rateOfCondition;
        this.rateOfDeceaseIncreasing = rateOfDeceaseIncreasing;
        this.rateOfMedicalCure = rateOfMedicalCure;
        this.description = description;
        this.patient_form = patient_form;
    }

    @Override
    public String toString() {
        return "Form{" +
                "rateOfCondition=" + rateOfCondition +
                ", rateOfDeceaseIncreasing=" + rateOfDeceaseIncreasing +
                ", rateOfMedicalCure=" + rateOfMedicalCure +
                ", description='" + description + '\'' +
                '}';
    }
}

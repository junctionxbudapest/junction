package space.enthropy.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.NotFoundException;
import java.util.Date;


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


    private Date dateTime;
    private int mood;
    private String sleep;
    private double weight;
    private double blood_pressure;
    private double pulse;
    private double bmi;
    private int skin_change;
    private boolean hair_loss;
    private boolean pain_chest;
    private String custom_symptoms;

    @ManyToOne(fetch = javax.persistence.FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient_form;

    public Form() {

    }

    public Form(int mood, String sleep, double weight, double blood_pressure, double pulse, int skin_change, boolean hair_loss, boolean pain_chest, String custom_symptoms, Patient patient_form) {
        this.dateTime = LocalDate.now().toDate();
        this.patient_form = patient_form;
        setMood(mood);
        this.sleep = sleep;
        this.weight = weight;
        this.blood_pressure = blood_pressure;
        this.pulse = pulse;
        setSkin_change(skin_change);
        this.hair_loss = hair_loss;
        this.pain_chest = pain_chest;
        this.custom_symptoms = custom_symptoms;
        this.bmi = (weight / patient_form.getHeight() / patient_form.getHeight()) * 703;
    }

    public void setSkin_change(int skin_change) {
        if (skin_change < 1 || skin_change > 5) {
            throw new NotFoundException();
        }
        this.skin_change = skin_change;
    }

    public void setMood(int mood) {
        if (mood < 1 || mood > 5) {
            throw new NotFoundException();
        }
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Form{" +
                "dateTime=" + dateTime +
                ", mood=" + mood +
                ", sleep='" + sleep + '\'' +
                ", weight=" + weight +
                ", blood_pressure=" + blood_pressure +
                ", pulse=" + pulse +
                ", bmi=" + bmi +
                ", skin_change=" + skin_change +
                ", hair_loss=" + hair_loss +
                ", pain_chest=" + pain_chest +
                ", custom_symptoms='" + custom_symptoms + '\'' +
                ", patient_form=" + patient_form +
                '}';
    }

    public Patient getPatient_form() {
        return patient_form;
    }


}

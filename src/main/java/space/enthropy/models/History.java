package space.enthropy.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class History extends PanacheEntity {
    private String historyOfDecease;

    @OneToOne(cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "patient_id")
    private Patient patient_history;

    public History() {

    }

    public History(String historyOfDecease, Patient patient_history) {
        this.historyOfDecease = historyOfDecease;
        this.patient_history = patient_history;
    }

    public Patient getPatient_history() {
        return patient_history;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyOfDecease='" + historyOfDecease + '\'' +
                '}';
    }
}

package ma.enset.iidbcc.tpapplicationmvc.entites;

import java.sql.Date;

public class Consultation {
    private long id;
    private Date date;
    private String description;
    private Patient patient;

    public Consultation() {
    }

    public Consultation(Date date, String description, Patient patient) {
        this.date = date;
        this.description = description;
        this.patient = patient;
    }

    public Consultation(long id, Date date, String description, Patient patient) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.patient = patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "{id=" + id +", date=" + date +", description='" + description  + ", patient=" + patient.toString() + "}";
    }
}

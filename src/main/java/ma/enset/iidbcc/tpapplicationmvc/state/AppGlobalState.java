package ma.enset.iidbcc.tpapplicationmvc.state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ma.enset.iidbcc.tpapplicationmvc.dao.IConsultationDao;
import ma.enset.iidbcc.tpapplicationmvc.dao.IPatientDao;
import ma.enset.iidbcc.tpapplicationmvc.dao.impl.ConsultationDaoImpl;
import ma.enset.iidbcc.tpapplicationmvc.dao.impl.PatientDaoImpl;
import ma.enset.iidbcc.tpapplicationmvc.entites.Consultation;
import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;
import ma.enset.iidbcc.tpapplicationmvc.services.IConsultationService;
import ma.enset.iidbcc.tpapplicationmvc.services.IPatientService;
import ma.enset.iidbcc.tpapplicationmvc.services.impl.ConsultationServiceImpl;
import ma.enset.iidbcc.tpapplicationmvc.services.impl.PatientServiceImpl;

import java.util.List;

public class AppGlobalState {

    private static final IConsultationDao consultationDao;
    private static final IPatientDao patientDao;
    private static final IConsultationService consultationService;
    private static final IPatientService patientService;
    private static final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private static final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    static {
        consultationDao = new ConsultationDaoImpl();
        patientDao = new PatientDaoImpl();
        consultationService = new ConsultationServiceImpl(consultationDao);
        patientService = new PatientServiceImpl(patientDao);
    }

    public static IPatientService getPatientService() {
        return patientService;
    }

    public static IConsultationService getConsultationService() {
        return consultationService;
    }

    public static IPatientDao getPatientDao() {
        return patientDao;
    }

    public static IConsultationDao getConsultationDao() {
        return consultationDao;
    }

    public static void init() {
        setPatients(patientService.findAll());
        setConsultations(consultationService.findAll());
    }

    public static ObservableList<Patient> getPatients() {
        return patients;
    }
    public static void setPatients(List<Patient> items) {
        patients.setAll(items);
    }
    public static void addPatient(Patient patient) {
        patients.add(patient);
    }
    public static void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public static ObservableList<Consultation> getConsultations() {
        return consultations;
    }
    public static void setConsultations(List<Consultation> items) {
        consultations.setAll(items);
    }
    public static void addConsultation(Consultation consultation) {
        AppGlobalState.consultations.add(consultation);
    }
    public static void removeConsultation(Consultation consultation) {
        AppGlobalState.consultations.remove(consultation);
    }
}

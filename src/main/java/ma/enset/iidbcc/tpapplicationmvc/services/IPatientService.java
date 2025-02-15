package ma.enset.iidbcc.tpapplicationmvc.services;

import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;

import java.util.List;

public interface IPatientService extends IBaseService<Patient, Long> {
    Patient findByEmail(String email);
    List<Patient> findByQuery(String query);
}

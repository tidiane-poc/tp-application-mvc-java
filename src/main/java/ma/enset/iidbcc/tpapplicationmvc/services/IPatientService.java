package ma.enset.iidbcc.tpapplicationmvc.services;

import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;

public interface IPatientService extends IBaseService<Patient, Long> {
    Patient findByEmail(String email);
}

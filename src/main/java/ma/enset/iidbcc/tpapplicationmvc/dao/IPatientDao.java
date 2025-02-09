package ma.enset.iidbcc.tpapplicationmvc.dao;

import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;

import java.util.Optional;

public interface IPatientDao extends Dao<Patient, Long> {
    Optional<Patient> findByEmail(String email);
}

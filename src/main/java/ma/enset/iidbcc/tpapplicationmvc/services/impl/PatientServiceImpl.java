package ma.enset.iidbcc.tpapplicationmvc.services.impl;

import ma.enset.iidbcc.tpapplicationmvc.dao.IPatientDao;
import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;
import ma.enset.iidbcc.tpapplicationmvc.services.IPatientService;

public class PatientServiceImpl extends BaseServiceImpl<Patient, Long> implements IPatientService {
    private final IPatientDao dao;

    public PatientServiceImpl(IPatientDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    protected Patient beforeSave(Patient entity) {
        return entity;
    }

    @Override
    protected void afterSave(Patient entity) {

    }

    @Override
    protected Patient beforeUpdate(Patient entity) {
        return entity;
    }

    @Override
    protected void afterUpdate(Patient entity) {

    }

    @Override
    public Patient findByEmail(String email) {
        return dao.findByEmail(email).orElse(null);
    }
}

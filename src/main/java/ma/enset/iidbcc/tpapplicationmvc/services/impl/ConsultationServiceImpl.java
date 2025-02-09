package ma.enset.iidbcc.tpapplicationmvc.services.impl;

import ma.enset.iidbcc.tpapplicationmvc.dao.IConsultationDao;
import ma.enset.iidbcc.tpapplicationmvc.entites.Consultation;
import ma.enset.iidbcc.tpapplicationmvc.services.IConsultationService;

import java.util.Date;
import java.util.List;

public class ConsultationServiceImpl extends BaseServiceImpl<Consultation, Long> implements IConsultationService {
    private final IConsultationDao dao;

    public ConsultationServiceImpl(IConsultationDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    protected Consultation beforeSave(Consultation entity) {
        return entity;
    }

    @Override
    protected void afterSave(Consultation entity) {

    }

    @Override
    protected Consultation beforeUpdate(Consultation entity) {
        return entity;
    }

    @Override
    protected void afterUpdate(Consultation entity) {

    }

    @Override
    public List<Consultation> findByPatientId(Long id) {
        return dao.findByPatientId(id);
    }

    @Override
    public List<Consultation> findByDate(Date date) {
        return dao.findByDate(date);
    }

    @Override
    public List<Consultation> findByDateBetween(Date start, Date end) {
        return dao.findByDateBetween(start, end);
    }

    @Override
    public List<Consultation> findByPatientIdAndDate(Long patientId, Date date) {
        return dao.findByPatientIdAndDate(patientId, date);
    }

    @Override
    public List<Consultation> findByPatientIdAndDateBetween(Long patientId, Date start, Date end) {
        return dao.findByPatientIdAndDateBetween(patientId, start, end);
    }
}

package ma.enset.iidbcc.tpapplicationmvc.services;

import ma.enset.iidbcc.tpapplicationmvc.entites.Consultation;

import java.util.Date;
import java.util.List;

public interface IConsultationService extends IBaseService<Consultation, Long> {
    List<Consultation> findByPatientId(Long id);
    List<Consultation> findByDate(Date date);
    List<Consultation> findByDateBetween(Date start, Date end);
    List<Consultation> findByPatientIdAndDate(Long patientId, Date date);
    List<Consultation> findByPatientIdAndDateBetween(Long patientId, Date start, Date end);
}

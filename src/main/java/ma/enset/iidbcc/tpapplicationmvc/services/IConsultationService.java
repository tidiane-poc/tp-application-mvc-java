package ma.enset.iidbcc.tpapplicationmvc.services;

import ma.enset.iidbcc.tpapplicationmvc.entites.Consultation;

import java.util.List;

public interface IConsultationService extends IBaseService<Consultation, Long> {
    List<Consultation> findAllByQuery(String query);
}

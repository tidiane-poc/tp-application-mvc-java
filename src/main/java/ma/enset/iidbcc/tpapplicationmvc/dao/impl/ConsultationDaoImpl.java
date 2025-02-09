package ma.enset.iidbcc.tpapplicationmvc.dao.impl;

import ma.enset.iidbcc.tpapplicationmvc.dao.IConsultationDao;
import ma.enset.iidbcc.tpapplicationmvc.dao.IPatientDao;
import ma.enset.iidbcc.tpapplicationmvc.entites.Consultation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ConsultationDaoImpl extends DaoDefaultImpl<Consultation, Long> implements IConsultationDao {

    private static final String FIND_BY_DATE_BETWEEN = "SELECT * FROM CONSULTATIONS WHERE DATE(CONSULTATION_DATE) BETWEEN ? AND ?";
    private static final String FIND_BY_PATIENT_ID_AND_DATE = "SELECT * FROM CONSULTATIONS WHERE PATIENT_ID=? AND DATE(CONSULTATION_DATE)=?";
    private static final String FIND_BY_PATIENT_ID_AND_DATE_BETWEEN = "SELECT * FROM CONSULTATIONS WHERE PATIENT_ID=? AND DATE(CONSULTATION_DATE) BETWEEN ? AND ?";
    private static final String FIND_BY_PATIENT_ID = "SELECT * FROM CONSULTATIONS WHERE PATIENT_ID=?";
    private static final String FIND_BY_DATE = "SELECT * FROM CONSULTATIONS WHERE DATE(CONSULTATION_DATE)=?";

    private static final String ID_ATTRIBUTE = "ID";
    private static final String CONSULTATION_DATE_ATTRIBUTE = "CONSULTATION_DATE";
    private static final String DESCRIPTION_ATTRIBUTE = "DESCRIPTION";
    private static final String PATIENT_ID_ATTRIBUTE = "PATIENT_ID";

    private static final IPatientDao patientDao = new PatientDaoImpl();

    @Override
    protected PreparedStatement insertStatement(Consultation entity) {
        return prepareStatement(
                String.format("INSERT INTO CONSULTATIONS(%s, %s, %s) VALUES(?, ?)", CONSULTATION_DATE_ATTRIBUTE, DESCRIPTION_ATTRIBUTE, PATIENT_ID_ATTRIBUTE),
                entity.getDate(), entity.getDescription(), entity.getPatient().getId()
        );
    }

    @Override
    protected PreparedStatement updateStatement(Consultation entity) {
        return prepareStatement(
                String.format("UPDATE CONSULTATIONS SET %s=?, %s=?, %s WHERE %s=?", CONSULTATION_DATE_ATTRIBUTE, DESCRIPTION_ATTRIBUTE, PATIENT_ID_ATTRIBUTE, ID_ATTRIBUTE),
                entity.getDate(), entity.getDescription(), entity.getPatient().getId(), entity.getId()
        );
    }

    @Override
    protected PreparedStatement deleteStatement(Long id) {
        return prepareStatement(
                String.format("DELETE FROM CONSULTATIONS WHERE %s=?", ID_ATTRIBUTE),
                id
        );
    }

    @Override
    protected PreparedStatement findOneStatement(Long id) {
        return prepareStatement(
                String.format("SELECT * FROM CONSULTATIONS WHERE %s=?", ID_ATTRIBUTE),
                id
        );
    }

    @Override
    protected PreparedStatement findAllStatement() {
        return prepareStatement("SELECT * FROM CONSULTATIONS");
    }

    @Override
    protected Consultation toEntity(ResultSet resultSet) throws SQLException {
        Consultation consultation = new Consultation();
        consultation.setId(resultSet.getLong(ID_ATTRIBUTE));
        consultation.setDate(resultSet.getDate(CONSULTATION_DATE_ATTRIBUTE));
        consultation.setDescription(resultSet.getString(DESCRIPTION_ATTRIBUTE));
        long patientId = resultSet.getLong(PATIENT_ID_ATTRIBUTE);
        var patient = patientDao.findOne(patientId).orElse(null);
        consultation.setPatient(patient);
        return consultation;
    }

    @Override
    public List<Consultation> findByDateBetween(Date start, Date end) {
        var preparedStatement = prepareStatement(FIND_BY_DATE_BETWEEN, start, end);
        return executeQueryStatement(preparedStatement);
    }

    @Override
    public List<Consultation> findByPatientIdAndDate(Long patientId, Date date) {
        var preparedStatement = prepareStatement(FIND_BY_PATIENT_ID_AND_DATE, patientId, date);
        return executeQueryStatement(preparedStatement);
    }

    @Override
    public List<Consultation> findByPatientIdAndDateBetween(Long patientId, Date start, Date end) {
        var preparedStatement = prepareStatement(FIND_BY_PATIENT_ID_AND_DATE_BETWEEN, patientId, start, end);
        return executeQueryStatement(preparedStatement);
    }

    @Override
    public List<Consultation> findByPatientId(Long id) {
        var preparedStatement = prepareStatement(FIND_BY_PATIENT_ID, id);
        return executeQueryStatement(preparedStatement);
    }

    @Override
    public List<Consultation> findByDate(Date date) {
        var preparedStatement = prepareStatement(FIND_BY_DATE, date);
        return executeQueryStatement(preparedStatement);
    }

}

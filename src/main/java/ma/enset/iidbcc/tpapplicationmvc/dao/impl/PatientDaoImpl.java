package ma.enset.iidbcc.tpapplicationmvc.dao.impl;


import ma.enset.iidbcc.tpapplicationmvc.dao.IPatientDao;
import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PatientDaoImpl extends DaoDefaultImpl<Patient, Long> implements IPatientDao {

    private static final String P_ATTR_ID = "ID";
    private static final String P_ATTR_FIRST_NAME = "NOM";
    private static final String P_ATTR_LAST_NAME = "PRENOM";
    private static final String P_ATTR_TEL = "TEL";
    private static final String P_ATTR_EMAIL = "EMAIL";

    @Override
    protected PreparedStatement insertStatement(Patient entity) {
        return prepareStatement(
                String.format("INSERT INTO PATIENTS(%s, %s, %s, %s) VALUES(?, ?, ?, ?)", P_ATTR_FIRST_NAME, P_ATTR_LAST_NAME, P_ATTR_TEL, P_ATTR_EMAIL),
                entity.getNom(), entity.getPrenom(), entity.getTel(), entity.getEmail()
        );
    }

    @Override
    protected PreparedStatement updateStatement(Patient entity) {
        return prepareStatement(
                String.format("UPDATE PATIENTS SET %s=?, %s=?, %s=?, %s=? WHERE %s=?", P_ATTR_FIRST_NAME, P_ATTR_LAST_NAME, P_ATTR_TEL, P_ATTR_EMAIL, P_ATTR_ID),
                entity.getNom(), entity.getPrenom(), entity.getTel(), entity.getEmail(), entity.getId()
        );
    }

    @Override
    protected PreparedStatement deleteStatement(Long id) {
        return prepareStatement(
                String.format("DELETE FROM PATIENTS WHERE %s=?", P_ATTR_ID),
                id
        );
    }

    @Override
    protected PreparedStatement findOneStatement(Long id) {
        return prepareStatement(
                String.format("SELECT * FROM PATIENTS WHERE %s=?", P_ATTR_ID),
                id
        );
    }

    @Override
    protected PreparedStatement findAllStatement() {
        return prepareStatement("SELECT * FROM PATIENTS");
    }

    @Override
    protected Patient toEntity(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong(P_ATTR_ID));
        patient.setNom(resultSet.getString(P_ATTR_FIRST_NAME));
        patient.setPrenom(resultSet.getString(P_ATTR_LAST_NAME));
        patient.setTel(resultSet.getString(P_ATTR_TEL));
        patient.setEmail(resultSet.getString(P_ATTR_EMAIL));
        return patient;
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        var statement = prepareStatement(
                String.format("SELECT * FROM PATIENTS WHERE %s=?", P_ATTR_EMAIL),
                email
        );
        var items = executeQueryStatement(statement);
        return items.stream().findFirst();
    }
}

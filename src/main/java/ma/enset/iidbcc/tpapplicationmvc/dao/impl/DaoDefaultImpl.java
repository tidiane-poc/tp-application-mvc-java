package ma.enset.iidbcc.tpapplicationmvc.dao.impl;

import ma.enset.iidbcc.tpapplicationmvc.dao.Dao;
import ma.enset.iidbcc.tpapplicationmvc.dao.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DaoDefaultImpl<E, U> implements Dao<E, U> {
    protected static final Connection connection = JdbcConnection.getInstance();

    protected abstract PreparedStatement insertStatement(E entity);
    protected abstract PreparedStatement updateStatement(E entity);
    protected abstract PreparedStatement deleteStatement(U id);
    protected abstract PreparedStatement findOneStatement(U id);
    protected abstract PreparedStatement findAllStatement();
    protected abstract E toEntity(ResultSet resultSet) throws SQLException;

    @Override
    public void save(E entity) {
        var stm = insertStatement(entity);
        executeUpdateStatement(stm);
    }

    @Override
    public void update(E entity) {
        var stm = updateStatement(entity);
        executeUpdateStatement(stm);
    }

    @Override
    public void delete(U id) {
        var stm = deleteStatement(id);
        executeUpdateStatement(stm);
    }

    @Override
    public Optional<E> findOne(U id) {
        var stm = findOneStatement(id);
        return executeQueryStatement(stm).stream().findFirst();
    }

    @Override
    public List<E> findAll() {
        var stm = findAllStatement();
        return executeQueryStatement(stm);
    }

    protected PreparedStatement prepareStatement(String sql, Object... params) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stm.setObject(i + 1, params[i]);
            }
        } catch (SQLException e) {
            log(e.getMessage());
        }
        return stm;
    }

    private void executeUpdateStatement(PreparedStatement stm) {
        try {
            stm.executeUpdate();
        } catch (SQLException e) {
            log(e.getMessage());
        }
    }

    protected List<E> executeQueryStatement(PreparedStatement stm) {
        List<E> entities = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                entities.add(toEntity(rs));
            }
        } catch (SQLException e) {
            log(e.getMessage());
        }

        return entities;
    }

    private void log(String message) {
        System.out.println(message);
    }
}

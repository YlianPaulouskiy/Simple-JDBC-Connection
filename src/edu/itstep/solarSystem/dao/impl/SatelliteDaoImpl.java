package edu.itstep.solarSystem.dao.impl;

import edu.itstep.solarSystem.dao.SatelliteDao;
import edu.itstep.solarSystem.model.Satellite;
import edu.itstep.solarSystem.singleton.ConnectionSingleton;
import edu.itstep.solarSystem.singleton.QuerySingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SatelliteDaoImpl implements SatelliteDao {

    private final Optional<Connection> connection;
    private QuerySingleton query;

    public SatelliteDaoImpl() {
        connection = ConnectionSingleton.instance(null).connection();
        query = QuerySingleton.instance(null);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Satellite> findOne(Long id) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query.getQuery("satellite_findOne"))) {
                //устанавливаем в запрос вместо ? поле id
                preparedStatement.setLong(1, id);
                return Optional.of(getResult(preparedStatement.executeQuery()).get(0));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Satellite> findAll() {
        if (connection.isPresent()) {
            try (Statement statement = connection.get().createStatement()) {
                return getResult(statement.executeQuery(query.getQuery("satellite_findAll")));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Satellite> findAllBySunId(Long sunId) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query.getQuery("satellite_findAllBySunId"))) {
                preparedStatement.setLong(1, sunId);
                return getResult(preparedStatement.executeQuery());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<Satellite> findAllByPlanetId(Long planetId) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query.getQuery("satellite_findAllByPlanetId"))) {
                preparedStatement.setLong(1, planetId);
                return getResult(preparedStatement.executeQuery());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Satellite> create(Satellite model) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get()
                    .prepareStatement(query.getQuery("satellite_create"), Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, model.getPlanetId());
                preparedStatement.setString(2, model.getName());
                //выполняем запрос
                preparedStatement.executeUpdate();
                //получаем поле, которое только что сгенерировали
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return findOne(resultSet.getLong(1));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Satellite> update(Satellite model) {
        if (connection.isPresent()) {
            Optional<Satellite> satellite = findOne(model.getId());
            if (satellite.isPresent()) {
                try (PreparedStatement preparedStatement = connection.get().prepareStatement(query.getQuery("satellite_update"))) {
                    preparedStatement.setString(1, model.getName());
                    preparedStatement.setLong(2, model.getId());
                    preparedStatement.setLong(3, model.getId());
                    //выполнение запроса
                    preparedStatement.executeUpdate();
                    return findOne(model.getId());
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query.getQuery("satellite_remove"))) {
                //устанавливаем ид в sql запрос
                preparedStatement.setLong(1, id);
                //выполнить запрос
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private List<Satellite> getResult(ResultSet resultSet) {
        try {
            List<Satellite> satelliteList = new ArrayList<>();
            while (resultSet.next()) {
                Satellite satellite = new Satellite();
                satellite.setId(resultSet.getLong("id"));
                satellite.setPlanetId(resultSet.getLong("planet_id"));
                satellite.setName(resultSet.getString("name"));
                satellite.setDateCreation(resultSet.getDate("date_creation"));
                satellite.setLastModified(resultSet.getDate("last_modified"));
                satellite.setVersion(resultSet.getLong("version"));
                satelliteList.add(satellite);
            }
            return satelliteList;
        } catch (SQLException ignore) {
            //
        }
        return Collections.emptyList();
    }
}

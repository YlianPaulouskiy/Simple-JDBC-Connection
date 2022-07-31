package edu.itstep.solarSystem.dao.impl;

import edu.itstep.solarSystem.dao.PlanetDao;
import edu.itstep.solarSystem.model.Planet;
import edu.itstep.solarSystem.singleton.ConnectionSingleton;
import edu.itstep.solarSystem.singleton.QuerySingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlanetDaoImpl implements PlanetDao {


    private final Optional<Connection> connection;
    private QuerySingleton queries;

    public PlanetDaoImpl() {
        this.connection = ConnectionSingleton.instance(null).connection();
        queries = QuerySingleton.instance(null);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Planet> findOne(Long id) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get()
                    .prepareStatement(queries.getQuery("planet_findOne"))) {
                //устанавливаем значение id в SQL запрос
                preparedStatement.setLong(1, id);
                //получаем лист планет и возвращаем единственную планету из этого листа
                return Optional.of(getResult(preparedStatement.executeQuery()).get(0));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Planet> findAll() {
        if (connection.isPresent()) {
            try (Statement statement = connection.get()
                    .createStatement()) {
                return getResult(statement.executeQuery(queries.getQuery("planet_findAll")));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }


    @Override
    public List<Planet> findAllBySunId(Long sunId) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get()
                    .prepareStatement(queries.getQuery("planet_findAllBySunId"))) {
                //устанавливаем значение sunId в SQL запрос
                preparedStatement.setLong(1, sunId);
                //получаем лист планет и возвращаем его
                return getResult(preparedStatement.executeQuery());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Planet> create(Planet model) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(
                    queries.getQuery("planet_create"), Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, model.getSunId());
                preparedStatement.setString(2, model.getName());
                preparedStatement.setString(3, model.getType());
                preparedStatement.setBoolean(4, model.isInhabitant());
                preparedStatement.setLong(5, model.getPopulation());
                //выполняем запрос
                preparedStatement.executeUpdate();
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
    public Optional<Planet> update(Planet model) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(queries.getQuery("planet_update"))) {
                preparedStatement.setLong(1, model.getSunId());
                preparedStatement.setString(2, model.getName());
                preparedStatement.setString(3, model.getType());
                preparedStatement.setBoolean(4, model.isInhabitant());
                preparedStatement.setLong(5, model.getPopulation());
                preparedStatement.setLong(6, model.getId());
                preparedStatement.setLong(7, model.getId());
                preparedStatement.executeUpdate();
                return findOne(model.getId());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        if (connection.isPresent()) {
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(queries.getQuery("planet_remove"))) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private List<Planet> getResult(ResultSet resultSet) {
        try {
            List<Planet> result = new ArrayList<>();
            while (resultSet.next()) {
                Planet planet = new Planet();
                planet.setId(resultSet.getLong("id"));
                planet.setSunId(resultSet.getLong("sun_id"));
                planet.setName(resultSet.getString("name"));
                planet.setType(resultSet.getString("type"));
                planet.setInhabitant(resultSet.getBoolean("inhabitant"));
                planet.setPopulation(resultSet.getLong("population"));
                planet.setDateCreation(resultSet.getDate("date_creation"));
                planet.setLastModified(resultSet.getDate("last_modified"));
                planet.setVersion(resultSet.getLong("version"));
                result.add(planet);
            }
            return result;
        } catch (SQLException ignore) {
            //
        }
        return Collections.emptyList();
    }

}

package edu.itstep.solarSystem.dao.impl;

import edu.itstep.solarSystem.dao.SunDao;
import edu.itstep.solarSystem.model.Sun;
import edu.itstep.solarSystem.singleton.ConnectionSingleton;
import edu.itstep.solarSystem.singleton.QuerySingleton;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class SunDaoImpl implements SunDao {

    private final Optional<Connection> connectionOptional;
    private final Map<String, String> queryMap;

    public SunDaoImpl() {
        this.connectionOptional = ConnectionSingleton.instance(null).connection();
        this.queryMap = QuerySingleton.instance(null).getQueryMap();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Sun> findOne(Long id) {
        if (connectionOptional.isPresent()) {
            String query = queryMap.getOrDefault("sun_findOne", "");
            //PreparedStatement можно использовать т.к. в запросах к бд вместо
            // знака вопроса он может поставить то значение, ктоторое нам необходимо
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(query)) {
                //подставляет вместо первого вопроса в запросе к БД наше значение id
                preparedStatement.setLong(1, id);
                //выполнить запрос и получить сет который соответствует данносму запросу
                //т.е. всю таблицу со всеми колонками по данному запросу
                ResultSet resultSet = preparedStatement.executeQuery();
                //если данные получилось найти то мы их записываем
                if (resultSet.next()) {
                    Sun sun = new Sun();
                    //можно устанавливать колонки по номеру колонки или по названию
                    //устанавливаем id из колонки id
                    sun.setId(resultSet.getLong("id"));
                    //устанавливаем name из колонки name
                    sun.setName(resultSet.getString("name"));
                    //устанавливаем type из колонки type
                    sun.setType(resultSet.getString("type"));
                    sun.setDateCreation(resultSet.getDate("date_creation"));
                    sun.setLastModified(resultSet.getDate("last_modified"));
                    sun.setVersion(resultSet.getLong("version"));
                    return Optional.of(sun);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Sun> findAll() {
        if (connectionOptional.isPresent()) {
            String query = queryMap.getOrDefault("sun_findAll", "");
            try (Statement statement = connectionOptional.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                List<Sun> sunList = new ArrayList<>();
                while (resultSet.next()) {
                    Sun sun = new Sun();
                    sun.setId(resultSet.getLong("id"));
                    sun.setName(resultSet.getString("name"));
                    sun.setType(resultSet.getString("type"));
                    sun.setDateCreation(resultSet.getDate("date_creation"));
                    sun.setLastModified(resultSet.getDate("last_modified"));
                    sun.setVersion(resultSet.getLong("version"));
                    sunList.add(sun);
                }
                return sunList;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Sun> create(Sun model) {
        if (connectionOptional.isPresent()) {
            String query = queryMap.getOrDefault("sun_create", "");
            //для генерации нового значение и запоминания ключа(id)
            try (PreparedStatement preparedStatement = connectionOptional.get()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                //записываем новое значение в БД
                preparedStatement.setString(1, model.getName());
                preparedStatement.setString(2, model.getType());
                //выполнение запроса
                preparedStatement.executeUpdate();
                //получаем ключ нового значения
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                //возвращаем это новое значение
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
    public Optional<Sun> update(Sun model) {
        if (connectionOptional.isPresent()) {
            Optional<Sun> sun = findOne(model.getId());
            if (sun.isPresent()) {
                String query = queryMap.getOrDefault("sun_update", "");
                try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(query)) {
                    preparedStatement.setString(1, model.getName());
                    preparedStatement.setString(2, model.getType());
                    //установка даты изменения
                    preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));
                    //устанавливаем новую версию после изменения
                    preparedStatement.setLong(4, sun.get().getVersion() + 1);
                    preparedStatement.setLong(5, model.getId());
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
        if (connectionOptional.isPresent()) {
            String query = queryMap.getOrDefault("sun_remove", "");
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                //выполнение запроса
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }
    }
}

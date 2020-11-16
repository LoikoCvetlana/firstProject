package com.oraz.dao;

import com.oraz.connection.ConnectionPool;
import com.oraz.exeption.DaoException;
import com.oraz.model.Rewiew;
import com.oraz.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RewiewDao {

    private static final RewiewDao INSTANCE = new RewiewDao();
    private static final String SAVE = "INSERT INTO oraz_storage.review (user_id, text) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM oraz_storage.review WHERE id = (?)";
    private static final String UPDATE = "UPDATE oraz_storage.review SET user_id=?, text=?" + "WHERE id = ?";
    private static final String FIND_ALL =
            "SELECT " +
                    "r.id AS rewiew_id, " +
                    "u.id AS user_id, " +
                    "p.text AS rewiew_text, " +
                    "u.name AS user_name, " +
                    "u.lastname AS user_lastname, " +
                    "u.organization AS user_organization, " +
                    "FROM oraz_storage.rewiew r " +
                    "INNER JOIN oraz_storage.user u " +
                    "ON r.user_id = u.id";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";

    private Rewiew getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return Rewiew.builder()
                .id(resultSet.getLong("rewiew_id"))
                .text(resultSet.getString("rewiew_text"))
                .user(User.builder()
                        .id(resultSet.getLong("user_id"))
                        .name(resultSet.getString("user_name"))
                        .lastname(resultSet.getString("user_lastname"))
                        .organization(resultSet.getString("user_organization"))
                        .build())
                .build();
    }

    public List<Rewiew> findAll() {
        List<Rewiew> rewiews = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Rewiew rewiew = getUserFromResultSet(resultSet);
                rewiews.add(rewiew);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rewiews;
    }

    public Optional<Rewiew> findById(Long rewiewId) {
        Optional<Rewiew> rewiew = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1,rewiewId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rewiew = Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rewiew;
    }

    public Rewiew save(@NonNull Rewiew rewiew) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, rewiew.getUser().getId());
            preparedStatement.setString(2, rewiew.getText());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                rewiew.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewiew;
    }

    public boolean delete(Rewiew rewiew) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, rewiew.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean update(Rewiew rewiew) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, rewiew.getUser().getId());
            preparedStatement.setString(2, rewiew.getText());
            preparedStatement.setLong(3, rewiew.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static RewiewDao getInstance() {
        return INSTANCE;
    }
}

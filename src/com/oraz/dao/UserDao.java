package com.oraz.dao;

import com.oraz.connection.ConnectionPool;
import com.oraz.exeption.DaoException;
import com.oraz.model.Material;
import com.oraz.model.User;
import com.oraz.model.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ALL = "SELECT id, role, password, name, lastname, " +
            "registration_date, e_mail, organization, other_information  FROM oraz_storage.users";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_MAIL = FIND_ALL + " WHERE e_mail = ? AND password =?";

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .role(Role.valueOf(resultSet.getString("role")))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .lastname(resultSet.getString("lastname"))
                .registrationDate(resultSet.getDate("registration_date").toLocalDate())
                .e_mail(resultSet.getString("e_mail"))
                .organization(resultSet.getString("organization"))
                .otherInformation(resultSet.getString("other_information"))
                .build();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Optional<User> findById(Long userId) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Optional<User> findByMail(String userMail) {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MAIL)) {
            preparedStatement.setString(1, userMail);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
//
//    public User save(@NonNull User user) {
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, String.valueOf(user.getRole()));
//            preparedStatement.setString(3, user.getPassword());
//            preparedStatement.setString(4, user.getName());
//            preparedStatement.setString(5, user.getLastName());
//            preparedStatement.setDate(6, Date.valueOf(String.valueOf(user.getRegistrationDate())));
//            preparedStatement.setString(7, user.getE_mail());
//            preparedStatement.setString(8, user.getOrganization());
//            preparedStatement.setString(9, user.getOtherInformation());
//            preparedStatement.executeUpdate();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                user.setId(generatedKeys.getLong("id"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return user;
//    }
//
//    public boolean delete(Material material) {
//        Boolean result = false;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
//            preparedStatement.setLong(1, material.getId());
//            result = preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return result;
//    }
//
//    public boolean update(User user) {
//        Boolean result = false;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
//            preparedStatement.setString(1, String.valueOf(user.getRole()));
//            preparedStatement.setString(2, user.getLogin());
//            preparedStatement.setString(3, user.getPassword());
//            preparedStatement.setString(4, user.getName());
//            preparedStatement.setString(5, user.getLastName());
//            preparedStatement.setDate(6, Date.valueOf(String.valueOf(user.getRegistrationDate())));
//            preparedStatement.setString(7, user.getE_mail());
//            preparedStatement.setString(8, user.getOrganizacion());
//            preparedStatement.setString(9, user.getOtherInformation());
//            preparedStatement.setLong(10, user.getId());
//            result = preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return result;
//    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

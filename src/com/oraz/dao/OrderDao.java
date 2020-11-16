package com.oraz.dao;

import com.oraz.connection.ConnectionPool;
import com.oraz.exeption.DaoException;
import com.oraz.model.Order;
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
public final class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();
    private static final String SAVE = "INSERT INTO oraz_storage.orders (date, desire_date, phone, other_information) " +
            "VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM oraz_storage.orders WHERE id = (?)";
    private static final String UPDATE = "UPDATE oraz_storage.orders " +
            "SET date=?, desire_date=?, phone=?, other_information=? WHERE id = ?";
    private static final String FIND_ALL = "SELECT id, date, desire_date, phone, other_information FROM oraz_storage.cart";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";


    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .id(resultSet.getLong("id"))
                .phone(resultSet.getInt("phone"))
                .date(resultSet.getDate("date").toLocalDate())
                .desireDate(resultSet.getDate("desireDate").toLocalDate())
                .otherInformation(resultSet.getString("otherInformation"))
                .build();
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orders;
    }

    public Optional<Order> findById(Long productId) {
        Optional<Order> order = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = Optional.of(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return order;
    }


    public Order save(@NonNull Order order) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, Date.valueOf(order.getDate()));
            preparedStatement.setDate(2, Date.valueOf(order.getDesireDate()));
            preparedStatement.setInt(3, order.getPhone());
            preparedStatement.setString(4, order.getOtherInformation());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    public boolean delete(Order order) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, order.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean update(Order order) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setDate(1, Date.valueOf(String.valueOf(order.getDate())));
            preparedStatement.setDate(2, Date.valueOf(String.valueOf(order.getDesireDate())));
            preparedStatement.setInt(3, order.getPhone());
            preparedStatement.setString(4, order.getOtherInformation());
            preparedStatement.setLong(5, order.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    public static OrderDao getInstance() {
        return INSTANCE;
    }

}

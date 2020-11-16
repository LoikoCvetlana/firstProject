package com.oraz.dao;

import com.oraz.connection.ConnectionPool;
import com.oraz.exeption.DaoException;
import com.oraz.model.Cart;
import com.oraz.model.Order;
import com.oraz.model.Product;
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
public final class CartDao {
    private static final CartDao INSTANCE = new CartDao();
    private static final String SAVE = "INSERT INTO oraz_storage.cart (user_id, product_id, amount, order_id)" +
            " VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM oraz_storage.cart WHERE id = (?)";
    private static final String UPDATE = "UPDATE oraz_storage.cart SET user_id =?, product_id=?, amount=?, order_id=? "
            + "WHERE id = ?";
    private static final String FIND_ALL = "SELECT id, user_id,  product_id, amount, order_id FROM oraz_storage.cart";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";


    private Cart getCartFromResultSet(ResultSet resultSet) throws SQLException {
        return Cart.builder()
                .id(resultSet.getLong("id"))
                .amount(resultSet.getInt("amount"))
                .user(User.builder().id(resultSet.getLong("id")).build())
                .product(Product.builder().id(resultSet.getLong("id")).build())
                .order(Order.builder().id(resultSet.getLong("id")).build())
                .build();
    }

    public List<Cart> findAll() {
        List<Cart> carts = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Cart cart = getCartFromResultSet(resultSet);
                carts.add(cart);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return carts;
    }

    public Optional<Cart> findById(Long productId) {
        Optional<Cart> cart = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cart = Optional.of(getCartFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return cart;
    }


    public Cart save(@NonNull Cart cart) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, cart.getUser().getId());
            preparedStatement.setLong(2, cart.getProduct().getId());
            preparedStatement.setDouble(3, cart.getAmount());
            preparedStatement.setLong(4, cart.getOrder().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cart.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return cart;
    }

    public boolean delete(Cart cart) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, cart.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean update(Cart cart) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, cart.getUser().getId());
            preparedStatement.setLong(2, cart.getProduct().getId());
            preparedStatement.setDouble(3, cart.getAmount());
            preparedStatement.setLong(4, cart.getOrder().getId());
            preparedStatement.setLong(5, cart.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    public static CartDao getInstance() {
        return INSTANCE;
    }
}

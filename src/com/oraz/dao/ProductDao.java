package com.oraz.dao;

import com.oraz.connection.ConnectionPool;
import com.oraz.exeption.DaoException;
import com.oraz.model.Material;
import com.oraz.model.Product;
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
public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();

    private static final String FIND_ALL =
            "SELECT p.id AS product_id, p.name AS product_name, p.article, p.value, p.picture," +
                    " m.name AS material_name ,m.id AS material_id " +
                    " FROM oraz_storage.product p INNER JOIN oraz_storage.material m " +
                    "ON p.material_id = m.id";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE p.id = ?";
    private static final String SAVE = "INSERT INTO oraz_storage.product (name, article, picture, value, material_id)" +
            "VALUES (?, ?, ?, ?, ?)";

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Product product = getProductFromResultSet(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Optional<Product> findById(Long productId) {
        Optional<Product> product = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = Optional.of(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong("product_id"))
                .name(resultSet.getString("product_name"))
                .article(resultSet.getString("article"))
                .picture(resultSet.getString("picture"))
                .value(resultSet.getDouble("value"))
                .material(Material.builder()
                        .id(resultSet.getLong("material_id"))
                        .name(resultSet.getString("material_name"))
                        .build())
                .build();
    }

    public Product save(@NonNull Product product) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getArticle());
            preparedStatement.setString(3, product.getPicture());
            preparedStatement.setDouble(4, product.getValue());
            preparedStatement.setLong(5, product.getMaterial().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return product;
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}

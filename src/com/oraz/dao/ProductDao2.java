//package com.oraz.dao;
//
//import com.oraz.connection.ConnectionPool;
//import com.oraz.exeption.DaoException;
//import com.oraz.model.Material;
//import com.oraz.model.Product;
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static java.sql.Statement.RETURN_GENERATED_KEYS;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public final class ProductDao2 {
//
//    private static final ProductDao2 INSTANCE = new ProductDao2();
//    private static final String FIND_ALL =
//            "SELECT " +
//                    "p.id AS product_id, " +
//                    "m.id AS material_, " +
//                    "p.name AS product_name, " +
//                    "m.name AS material_name, " +
//                    "p.article, " +
//                    "p.picture, " +
//                    "p.value, " +
//                    "FROM oraz_storage.product p " +
//                    "INNER JOIN oraz_storage.material m " +
//                    "ON p.material_id = m.id";
//    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
//    private static final String SAVE = "INSERT INTO oraz_storage.product name, article, picture, value, material) " +
//            "VALUES (?, ?, ?, ?, ?)";
//    private static final String DELETE = "DELETE FROM oraz_storage.product WHERE id = (?";
//    private static final String UPDATE = "UPDATE oraz_storage.product SET " +
//            "(name=?, description=?, availability=?, id_material=?) WHERE id = ?";
//
//    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
//        return Product.builder()
//                .id(resultSet.getLong("product_id"))
//                .name(resultSet.getString("product_name"))
//                .article(resultSet.getString("article"))
//                .picture(resultSet.getString("picture"))
//                .value(resultSet.getDouble("value"))
//                .material(Material.builder()
//                        .id(resultSet.getLong("material_id"))
//                        .name(resultSet.getString("material_name"))
//                        .build())
//                .build();
//    }
//
//    public List<Product> findAll() {
//        List<Product> products = new ArrayList<>();
//        try (Connection connection = ConnectionPool.getConnection();
//             Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(FIND_ALL);
//            while (resultSet.next()) {
//                Product product = getProductFromResultSet(resultSet);
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return products;
//    }
//
//    public Optional<Product> findById(Long productId) {
//        Optional<Product> product = Optional.empty();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
//            preparedStatement.setLong(1, productId);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                product = Optional.of(getProductFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//
//        return product;
//    }
//
//
//    public Product save(@NonNull Product product) {
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setString(2, product.getArticle());
//            preparedStatement.setString(3, product.getPicture());
//            preparedStatement.setDouble(4, product.getValue());
//            preparedStatement.setLong(5, product.getMaterial().getId());
//            preparedStatement.executeUpdate();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                product.setId(generatedKeys.getLong("id"));
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return product;
//    }
//
//    public boolean delete(Product product) {
//        Boolean result = false;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
//            preparedStatement.setLong(1, product.getId());
//            result = preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return result;
//    }
//
//    public boolean update(Product product) {
//        Boolean result = false;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setString(2, product.getArticle());
//            preparedStatement.setString(3, product.getPicture());
//            preparedStatement.setDouble(4, product.getValue());
//            preparedStatement.setLong(5, product.getMaterial().getId());
//            result = preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return result;
//    }
//
//    public static ProductDao2 getInstance() {
//        return INSTANCE;
//    }
//}
//

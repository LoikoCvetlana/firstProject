package itacademy.dao;


import com.oraz.connection.ConnectionPool;
import com.oraz.dao.ProductDao;
import com.oraz.dao.UserDao;
import com.oraz.model.Material;
import com.oraz.model.Product;
import com.oraz.model.User;
import com.oraz.model.enums.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoTest {

    private ProductDao dao = ProductDao.getInstance();
    private UserDao daoU = UserDao.getInstance();


    @Test
    public void checkGetAll() throws SQLException {

        String FIND_ALL = "SELECT id, role, password, name, lastname, " +
                "registration_date, e_mail, organization, other_information  FROM oraz_storage.users";
        String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
        String FIND_BY_MAIL = FIND_ALL + " WHERE e_mail = ? AND password =?";


        Optional<User> user = Optional.empty();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sportoras", "postgres", "Jgfymrb");
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, 2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = Optional.ofNullable(User.builder().id(resultSet.getLong("id"))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .lastname(resultSet.getString("lastname"))
                        .registrationDate(resultSet.getDate("registration_date").toLocalDate())
                        .e_mail(resultSet.getString("e_mail"))
                        .organization(resultSet.getString("organization"))
                        .otherInformation(resultSet.getString("other_information"))
                        .build());
                System.out.println(user);
            }
        }
    }

//
//        public Optional<User> findByMail(String userMail) {
//            Optional<User> user = Optional.empty();
//            try (Connection connection = ConnectionPool.getConnection();
//                 PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MAIL)) {
//                preparedStatement.setString(1, userMail);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//                if (resultSet.next()) {
//                    user = Optional.of(getUserFromResultSet(resultSet));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            return user;
//        }


//
//            public Optional<Product> findById (Long productId){
//                Optional<Product> product = Optional.empty();
//                try (Connection connection = ConnectionPool.getConnection();
//                     PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
//                    preparedStatement.setLong(1, productId);
//
//                    ResultSet resultSet = preparedStatement.executeQuery();
//                    if (resultSet.next()) {
//                        product = Optional.of(getProductFromResultSet(resultSet));
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                return product;
//            }


//        String FIND_BASIC_INF2 =
//                "SELECT p.id, p.name, p.article, p.value, p.picture, m.name AS material_name ,m.id AS material_id " +
//                        " FROM oraz_storage.product p INNER JOIN oraz_storage.material m " +
//                        "ON p.material_id = m.id";
//
//
//        List<Product> products = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sportoras", "postgres", "Jgfymrb")) {
//            try (Statement statement = connection.createStatement()) {
//                ResultSet resultSet = statement.executeQuery(FIND_BASIC_INF2);
//                while (resultSet.next()) {
//                    Product product = Product.builder()
//                            .id(resultSet.getLong("id"))
//                            .name(resultSet.getString("name"))
//                            .article(resultSet.getString("article"))
//                            .value(resultSet.getDouble("value"))
//                            .picture(resultSet.getString("picture"))
//                            .material(Material.builder()
//                                    .id(resultSet.getLong("material_id"))
//                                    .name(resultSet.getString("material_name"))
//                                    .build())
//                            .build();
//                    products.add(product);
//                }
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }


//        }
//    private Product getProductFromResultSet (ResultSet resultSet) throws SQLException {
//        return Product.builder()
//                .id(resultSet.getLong("product_id"))
//                .name(resultSet.getString("product_name"))
//                .article(resultSet.getString("article"))
//                .picture(resultSet.getString("picture"))
//                .value(resultSet.getDouble("value"))
//                .material(Material.builder()
//                        .id(resultSet.getLong("material_id"))
////                        .name(resultSet.getString("material_name"))
////                        .build())
////                .build();
////    }
//
//    }}}
//
//

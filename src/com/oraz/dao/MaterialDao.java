package com.oraz.dao;

import com.oraz.connection.ConnectionPool;
import com.oraz.exeption.DaoException;
import com.oraz.model.Material;
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
public final class MaterialDao {

    private static final MaterialDao INSTANCE = new MaterialDao();
    private static final String FIND_ALL = "SELECT id, name, description, availability FROM oraz_storage.material";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String SAVE = "INSERT INTO oraz_storage.material (name, description, availability) VALUES (?, ?, TRUE)";
    private static final String DELETE = "DELETE FROM oraz_storage.materia WHERE id = (?)";
    private static final String UPDATE = "UPDATE oraz_storage.material SET name=?, description=?, availability=?" +
            " WHERE id = ?";


    public Optional<Material> findById(Integer materialId) {
        Material material = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, materialId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                material = Material.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .availability(resultSet.getBoolean("availability"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(material);
    }

    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Material material = Material.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .availability(resultSet.getBoolean("availability"))
                        .build();
                materials.add(material);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return materials;
    }

    public Material save(@NonNull Material material) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, material.getName());
            preparedStatement.setString(2, material.getDescription());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                material.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e) ;
        }

        return material;
    }

    public boolean delete(Material material) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, material.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean update(Material material) {
        Boolean result = false;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, material.getName());
            preparedStatement.setString(2, material.getDescription());
            preparedStatement.setBoolean(3, material.getAvailability());
            preparedStatement.setLong(4, material.getId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static MaterialDao getInstance() {
        return INSTANCE;
    }
}
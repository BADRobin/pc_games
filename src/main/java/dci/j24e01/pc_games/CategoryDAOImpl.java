package dci.j24e01.pc_games;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private final Connection connection;

    public CategoryDAOImpl(DBConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public List<Category> getCategories() {

        String sql = "SELECT * FROM categories";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);

            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getLong("id_category"),
                        resultSet.getString("name")

                );
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        String sql = "SELECT * FROM categories WHERE id_category = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Category(
                        resultSet.getLong("id_category"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCategory(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}



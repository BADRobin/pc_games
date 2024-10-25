package dci.j24e01.pc_games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}



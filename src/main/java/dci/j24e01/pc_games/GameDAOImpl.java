package dci.j24e01.pc_games;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl implements GameDAO {
    private final Connection connection;
    private final CategoryDAO categoryDAO;

    public GameDAOImpl(DBConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
        this.categoryDAO = new CategoryDAOImpl(connectionManager);;
    }

    @Override
    public List<Game> getGames() {
        String sql = "SELECT g.id_game, g.name, g.category_id, g.price, c.name AS category_name " +
                "FROM pc_games g " +
                "LEFT JOIN categories c ON g.category_id = c.id_category";
        List<Game> games = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Game game = new Game(
                        resultSet.getLong("id_game"),
                        resultSet.getString("name"),
                        resultSet.getLong("category_id"),
                        resultSet.getDouble("price"),
                        resultSet.getString("category_name")
                );
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public void addGame(Game game) {
        Long categoryId = categoryDAO.findOrCreateCategoryByName(game.getCategoryName());
        if (categoryId == null) {
            throw new RuntimeException("Could not create or find category.");
        }


        String sql = "INSERT INTO pc_games (name, category_id, price) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, game.getName());
            preparedStatement.setObject(2, categoryId);
            preparedStatement.setDouble(3, game.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateGame(Game game) {
        if (game.getCategoryId() == null) {
            throw new RuntimeException("Cannot update game without a category.");
        }
        String sql = "UPDATE pc_games SET name = ?, category_id = ?, price = ? WHERE id_game = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, game.getName());
            preparedStatement.setObject(2, game.getCategoryId(), Types.BIGINT);
            preparedStatement.setDouble(3, game.getPrice());
            preparedStatement.setLong(4, game.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Game getGameById(Long id) {
        String sql = "SELECT g.id_game, g.name, g.category_id, g.price, c.name AS category_name " +
                "FROM pc_games g " +
                "LEFT JOIN categories c ON g.category_id = c.id_category WHERE g.id_game = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Game(
                            resultSet.getLong("id_game"),
                            resultSet.getString("name"),
                            resultSet.getLong("category_id"),
                            resultSet.getDouble("price"),
                            resultSet.getString("category_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteGame(Long id) {
        String sql = "DELETE FROM pc_games WHERE id_game = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

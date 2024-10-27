package dci.j24e01.pc_games;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImpl implements GameDAO {
    private final Connection connection;

    public GameDAOImpl(DBConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public List<Game> getGames() {
        String sql = "SELECT * FROM pc_games";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            List<Game> games = new ArrayList<>();
            while (resultSet.next()) {
                Game game = new Game(
                        resultSet.getLong("id_game"),
                        resultSet.getString("name"),
                        resultSet.getLong("category_id"),
                        resultSet.getDouble("price")
                );
                games.add(game);
            }
            return games;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Game getGameById(Long id) {
        String sql = "SELECT * FROM pc_games WHERE id_game = ?";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Game(
                        resultSet.getLong("id_game"),
                        resultSet.getString("name"),
                        resultSet.getLong("category_id"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addGame(Game game) {
        String sql = "INSERT INTO pc_games (name, category_id, price) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, game.getName());
            if (game.getCategoryId() != null) {
                preparedStatement.setLong(2, game.getCategoryId());
            } else {
                preparedStatement.setNull(2, Types.BIGINT);
            }
            preparedStatement.setDouble(3, game.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateGame(Game game) {
        String sql = "UPDATE pc_games SET name = ?, category_id = ?, price = ? WHERE id_game = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, game.getName());
            preparedStatement.setLong(2, game.getCategoryId());
            preparedStatement.setDouble(3, game.getPrice());
            preparedStatement.setLong(4, game.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

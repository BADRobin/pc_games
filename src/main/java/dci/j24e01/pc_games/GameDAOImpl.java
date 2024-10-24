package dci.j24e01.pc_games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            System.out.println(resultSet);

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
}

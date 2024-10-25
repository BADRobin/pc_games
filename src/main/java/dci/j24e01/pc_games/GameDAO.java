package dci.j24e01.pc_games;

import java.util.List;

public interface GameDAO {
    List<Game> getGames();
    Game getGameById(Long id);
    void addGame(Game game);
    void updateGame(Game game);
    void deleteGame(Long id);
}

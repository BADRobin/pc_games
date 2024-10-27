package dci.j24e01.pc_games;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AppController {
    private final DBConnectionManager dbConnectionManager = new MySQLConnectionManager();
    private final GameDAO gameDAO = new GameDAOImpl(dbConnectionManager);
    private final CategoryDAO categoryDAO = new CategoryDAOImpl(dbConnectionManager);

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/game_list")
    private String game_list(Model model) {
        List<Game> games = gameDAO.getGames();
        List<Category> categories = categoryDAO.getCategories();

        Map<Long, String> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getId(), category.getName());
        }

        model.addAttribute("games", games);
        model.addAttribute("categoryMap", categoryMap);
        return "games_list";
    }

    @GetMapping("/categories_list")
    private String categories_list(Model model) {
        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);
        return "categories_list";
    }

    @GetMapping("/games/add")
    public String showAddGameForm(Model model) {

        model.addAttribute("game", new Game());
        model.addAttribute("category", new Category());
        return "add_game";      }


    @PostMapping("/games/save")
    public String saveGameWithCategory(
            @ModelAttribute("game") Game game,
            @ModelAttribute("category") Category category) {
        categoryDAO.addCategory(category);
        game.setCategoryId(category.getId());
        gameDAO.addGame(game);

        return "redirect:/game_list";

    }

    @GetMapping("/games/edit/{id}")
    public String showEditGameForm(@PathVariable("id") Long id, Model model) {
        Game game = gameDAO.getGameById(id);
        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("game", game);
        model.addAttribute("categories", categories);
        return "game-form";
    }

    @GetMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameDAO.deleteGame(id);
        return "redirect:/game_list";
    }
}

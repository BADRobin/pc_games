package dci.j24e01.pc_games;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
public class AppController {
    private final DBConnectionManager dbConnectionManager = new MySQLConnectionManager();
    private final GameDAO gameDAO = new GameDAOImpl(dbConnectionManager);
    private final CategoryDAO categoryDAO = new CategoryDAOImpl(dbConnectionManager);

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/games_list")
    public String gameList(Model model) {
        List<Game> games = gameDAO.getGames();
        model.addAttribute("games", games);
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
        return "add_game";
    }

    @PostMapping("/games/save")
    public String saveGame(@ModelAttribute("game") Game game) {
        gameDAO.addGame(game);
        return "redirect:/games_list";
    }

    @GetMapping("/games/edit/{id}")
    public String showEditGameForm(@PathVariable("id") Long id, Model model) {
        Game game = gameDAO.getGameById(id);
        model.addAttribute("game", game);
        return "game-form";
    }
    @PostMapping("/games/update")
    public String updateGame(@ModelAttribute("game") Game game,
                             @RequestParam("categoryName") String categoryName) {
        Long categoryId = game.getCategoryId();
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            categoryId = categoryDAO.findOrCreateCategoryByName(categoryName);
        }

        if (categoryId == null) {
            throw new RuntimeException("Category must be provided for the game.");
        }
        if (categoryId == null || categoryId <= 0) {
            if (categoryName != null && !categoryName.trim().isEmpty()) {
                categoryId = categoryDAO.findOrCreateCategoryByName(categoryName);
            } else {
                throw new RuntimeException("A category must be selected or created.");
            }
        }

        game.setCategoryId(categoryId);
        gameDAO.updateGame(game);
        return "redirect:/games_list";
    }
    @GetMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id, Model model) {
        Game game = gameDAO.getGameById(id);
        model.addAttribute("game", game);
        return "confirm_delete";
    }
    @PostMapping("/games/delete")
    public String confirmDelete(@RequestParam("id") Long id) {
        gameDAO.deleteGame(id);
        return "redirect:/games_list";
    }
}

package dci.j24e01.pc_games;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories();
    Category getCategoryById(Long id);
}

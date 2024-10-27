package dci.j24e01.pc_games;

import java.util.List;

public interface CategoryDAO {
    Long findOrCreateCategoryByName(String categoryName);

    List<Category> getCategories();
    Category getCategoryById(Long id);
    void addCategory(Category category);
}

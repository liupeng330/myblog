package pengliu.me.service;

import pengliu.me.domain.Category;

import java.util.List;

/**
 * Created by peng on 4/11/16.
 */
public interface CategoryService
{
    void createCategoryByName(String name);
    Category findCategoryById(Integer id);
    void updateCategoryNameById(Integer id, String newName);
    void deleteCategoryById(Integer id);
    List<Category> getAllCategories();
}

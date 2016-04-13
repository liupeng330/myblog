package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.dao.CategoryDao;
import pengliu.me.domain.Category;
import pengliu.me.service.CategoryService;

import java.util.List;

/**
 * Created by peng on 4/11/16.
 */
@Service
public class CategoryServiceImpl implements CategoryService
{
    private Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    public void createCategoryByName(String name)
    {
        this.categoryDao.createCategoryByName(name);
    }

    public void updateCategoryNameById(Integer id, String newName)
    {
        this.updateCategoryNameById(id, newName);
    }

    public void deleteCategoryById(Integer id)
    {
        this.deleteCategoryById(id);
    }

    public List<Category> getAllCategories()
    {
        return this.categoryDao.getAllCategories();
    }
}

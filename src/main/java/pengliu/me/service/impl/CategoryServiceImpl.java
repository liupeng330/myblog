package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.common.BlogStatus;
import pengliu.me.dao.CategoryDao;
import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.service.CategoryService;
import pengliu.me.utils.Transfer;
import pengliu.me.vo.BlogVo;
import pengliu.me.vo.CategoryVo;

import java.util.ArrayList;
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

    public CategoryVo findCategoryById(Integer id)
    {
        Category category = this.findCategoryPoById(id);

        CategoryVo vo = new CategoryVo();
        vo.setId(category.getId());
        vo.setName(category.getName());
        vo.setCreateTime(category.getCreateTime());
        vo.setUpdateTime(category.getUpdateTime());

        return vo;
    }

    public Category findCategoryPoById(Integer id)
    {
        return this.categoryDao.get(id);
    }

    public List<BlogVo> getAllPublishedBlogsByCategoryId(Integer id)
    {
        Category category = this.findCategoryPoById(id);
        List<BlogVo> resultBlogVos = new ArrayList<BlogVo>();
        if(category != null)
        {
            for(Blog blog: category.getBlogs())
            {
                if(blog.getStatus() == BlogStatus.PUBLISHED)
                {
                    resultBlogVos.add(Transfer.transferBlogPoToVo(blog));
                }
            }
        }

        return resultBlogVos;
    }

    public void updateCategoryNameById(Integer id, String newName)
    {
        this.categoryDao.updateCategoryNameById(id, newName);
    }

    public void deleteCategoryById(Integer id)
    {
        this.categoryDao.deleteCategoryById(id);
    }

    public List<CategoryVo> getAllCategories()
    {
        return Transfer.transferCategoryListPoToVo(this.categoryDao.getAllCategories());
    }
}

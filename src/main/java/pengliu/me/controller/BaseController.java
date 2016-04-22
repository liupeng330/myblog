package pengliu.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.service.CategoryService;
import pengliu.me.service.TagService;
import pengliu.me.vo.CategoryVo;
import pengliu.me.vo.TagVo;

import java.util.List;

/**
 * Created by peng on 4/22/16.
 */
public class BaseController
{
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    protected void addAllTagAndCategoriesToModelAndView(ModelAndView modelAndView)
    {
        List<TagVo> tags = this.tagService.getAllTags();
        modelAndView.addObject("allTags", tags);

        List<CategoryVo> categories = this.getCategoryService().getAllCategories();
        modelAndView.addObject("allCategories", categories);
    }

    protected CategoryService getCategoryService() {
        return categoryService;
    }

    protected TagService getTagService() {
        return tagService;
    }
}

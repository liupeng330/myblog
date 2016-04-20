package pengliu.me.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.domain.Category;
import pengliu.me.service.CategoryService;
import pengliu.me.vo.BlogVo;
import pengliu.me.vo.CategoryVo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by peng on 4/12/16.
 */
@Controller
@RequestMapping
public class CategoryController
{
    private Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getBlogsByCategory(@PathVariable Integer categoryId)
    {
        ModelAndView modelAndView = new ModelAndView();

        CategoryVo categoryVo = this.categoryService.findCategoryById(categoryId);
        modelAndView.addObject("category", categoryVo.getName());


        List<BlogVo> blogVos = this.categoryService.getAllPublishedBlogsByCategoryId(categoryId);
        modelAndView.addObject("allBlogs", blogVos);
        modelAndView.setViewName("/main");

        return modelAndView;
    }

    @RequestMapping(value = "/management/category/listAll", method = RequestMethod.GET)
    public ModelAndView listAllCategories()
    {
        ModelAndView modelAndView = new ModelAndView();

        List<CategoryVo> categories = this.categoryService.getAllCategories();
        modelAndView.addObject("allCategories", categories);
        modelAndView.setViewName("/categoryManager");

        return modelAndView;
    }

    @RequestMapping(value = "/management/category/update/{id}", method = RequestMethod.GET)
    public ModelAndView gotToUpdateCategoryPage(@PathVariable Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();

        CategoryVo category = this.categoryService.findCategoryById(id);
        modelAndView.addObject("category", category);
        modelAndView.setViewName("/categoryUpdate");

        return modelAndView;
    }

    @RequestMapping(value = "/management/category/update", method = RequestMethod.POST)
    public String updateCategory(Integer id, String newName)
    {
        ModelAndView modelAndView = new ModelAndView();
        this.categoryService.updateCategoryNameById(id, newName);
        String target = "/management/category/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }

    @RequestMapping(value = "/management/category/create", method = RequestMethod.GET)
    public String goToCreateCategoryPage(String name)
    {
        return "/categoryCreate";
    }

    @RequestMapping(value = "/management/category/create", method = RequestMethod.POST)
    public String createCategory(String name)
    {
        this.logger.info("Start to create category " + name);
        this.categoryService.createCategoryByName(name);

        String target = "/management/category/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }

    @RequestMapping(value = "/management/category/delete/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Integer id)
    {
        this.categoryService.deleteCategoryById(id);
        String target = "/management/category/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }
}

package pengliu.me.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.domain.Category;
import pengliu.me.service.CategoryService;

import java.util.List;

/**
 * Created by peng on 4/12/16.
 */
@Controller
public class CategoryController
{
    private Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/management/category/listAll", method = RequestMethod.GET)
    public ModelAndView listAllCategories()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = this.categoryService.getAllCategories();
        modelAndView.addObject("allCategories", categories);
        modelAndView.setViewName("/categoryManager");
        return modelAndView;
    }

    @RequestMapping(value = "/management/category/update", method = RequestMethod.POST)
    public ModelAndView updateCategory(Integer id, String newName)
    {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/management/category/create", method = RequestMethod.GET)
    public String goToCreateCategoryPage(String name)
    {
        return "/categoryCreateAndUpdate";
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
}

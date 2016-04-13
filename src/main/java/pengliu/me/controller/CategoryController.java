package pengliu.me.controller;

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
}

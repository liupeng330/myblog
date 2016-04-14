package pengliu.me.controller;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.domain.Blog;
import pengliu.me.service.CategoryService;
import pengliu.me.service.TagService;

/**
 * Created by peng on 16-4-14.
 */
@Controller
@RequestMapping("/blog")
public class BlogController
{
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage()
    {
        return "main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView goToBlogManagePage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCategories", this.categoryService.getAllCategories());
        modelAndView.addObject("allTags", this.tagService.getAllTags());
        modelAndView.setViewName("blogCreate");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createBlog(
            Blog blog
//            @RequestParam("title") String title,
//            @RequestParam("summary") String summary,
//            @RequestParam("content") String content,
//            @RequestParam("category") String category
    )
    {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}

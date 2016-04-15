package pengliu.me.controller;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.common.BlogStatus;
import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.domain.Tag;
import pengliu.me.domain.User;
import pengliu.me.exception.UserNotExistException;
import pengliu.me.service.BlogService;
import pengliu.me.service.CategoryService;
import pengliu.me.service.TagService;
import pengliu.me.service.UserService;
import pengliu.me.utils.Common;
import pengliu.me.vo.BlogVo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by peng on 16-4-14.
 */
@Controller
@RequestMapping("/blog")
public class BlogController
{
    private Logger logger = Logger.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showMainPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<BlogVo> publishedBlogs = this.blogService.getAllPublishedBlogs();
        modelAndView.addObject("allBlogs", publishedBlogs);
        modelAndView.setViewName("main");

        return modelAndView;
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
    public ModelAndView createBlog(Blog blog)
    {
        ModelAndView modelAndView = new ModelAndView();

        this.logger.info("Get category by blog's category id");
        Category category = this.categoryService.findCategoryById(blog.getCategoryId());

        this.logger.info("Get tags by blog's tag ids");
        List<Tag> tags = this.tagService.findTagsByIds(blog.getTagIds());

        try
        {
            this.logger.info("Create blog");
            this.blogService.createBlog(blog, category, tags);
        }
        catch (UserNotExistException ex)
        {
            this.logger.error("Admin user doesn't exist!!!");
            modelAndView.addObject("errorMsg", ex.getMessage());
            modelAndView.setViewName("blogCreate");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/blog.html");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable Integer id)
    {
        this.logger.info("Start to delete blog for id: " + id);
        this.blogService.deleteBlogById(id);
        return "redirect:/blog/listAll.html";
    }

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public ModelAndView listAllBlogs()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allBlogs", this.blogService.getAllBlogs());
        modelAndView.setViewName("blogManager");

        return modelAndView;
    }
}

package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.common.CommonConstant;
import pengliu.me.dao.Page;
import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.domain.Tag;
import pengliu.me.exception.BlogNotExistException;
import pengliu.me.exception.UserNotExistException;
import pengliu.me.service.BlogService;
import pengliu.me.service.CategoryService;
import pengliu.me.service.TagService;
import pengliu.me.service.UserService;
import pengliu.me.vo.BlogVo;
import pengliu.me.vo.CategoryVo;
import pengliu.me.vo.TagVo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by peng on 16-4-14.
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController
{
    private Logger logger = Logger.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showMainPage(@RequestParam(value = "pageNo", required = false) Integer pageNo)
    {
        ModelAndView modelAndView = new ModelAndView();
        if(pageNo == null)
        {
            pageNo = 1;
        }
        Page<BlogVo> publishedBlogs = this.blogService.getAllPagedPublishedBlogs(pageNo, CommonConstant.PAGE_SIZE);
        modelAndView.addObject("pageResult", publishedBlogs);

        addAllTagAndCategoriesToModelAndView(modelAndView);
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView goToCreateBlogPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        addAllTagAndCategoriesToModelAndView(modelAndView);
        modelAndView.setViewName("blogCreate");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createBlog(
            @RequestParam("title") String title,
            @RequestParam("summary") String summary,
            @RequestParam("content") String content,
            @RequestParam("categoryVo") Integer categoryId,
            @RequestParam("tagVos") Integer[] tagIds,
            @RequestParam("status") String status)
    {
        return this.saveOrUpdateBlog(null, title, summary, content, categoryId, tagIds, status, false);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable Integer id)
    {
        this.logger.info("Start to delete blogVo for id: " + id);
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

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView goToUpdateBlogPage(@PathVariable Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();

        List<CategoryVo> categoryVos = this.getCategoryService().getAllCategories();
        modelAndView.addObject("allCategories", categoryVos);

        List<TagVo> tagVos = this.getTagService().getAllTags();
        modelAndView.addObject("allTags", tagVos);

        BlogVo blogVo = null;
        try
        {
            blogVo = this.blogService.getBlogById(id);
        }
        catch (BlogNotExistException ex)
        {
            modelAndView.addObject("errorMsg", ex.getMessage());
            modelAndView.addObject("allBlogs", this.blogService.getAllBlogs());
            modelAndView.setViewName("blogManager");
            return modelAndView;
        }
        modelAndView.addObject("blog", blogVo);

        CategoryVo checkedCategoryVo = blogVo.getCategoryVo();
        for(CategoryVo categoryVo: categoryVos)
        {
            if(categoryVo.equals(checkedCategoryVo))
            {
                categoryVo.setChecked(true);
            }
        }

        Set<TagVo> checkedTagVos = blogVo.getTagVos();
        for(TagVo tagVo: tagVos)
        {
            if(checkedTagVos.contains(tagVo))
            {
                tagVo.setChecked(true);
            }
        }

        modelAndView.setViewName("blogUpdate");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateBlog(
            @RequestParam("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("summary") String summary,
            @RequestParam("content") String content,
            @RequestParam("categoryVo") Integer categoryId,
            @RequestParam("tagVos") Integer[] tagIds,
            @RequestParam("status") String status)
    {
        return this.saveOrUpdateBlog(id, title, summary, content, categoryId, tagIds, status, true);
    }

    private ModelAndView saveOrUpdateBlog(
            Integer id,
            String title,
            String summary,
            String content,
            Integer categoryId,
            Integer[] tagIds,
            String status,
            boolean isUpdate)
    {
        ModelAndView modelAndView = new ModelAndView();

        this.logger.info("Get category by category id");
        Category category = this.getCategoryService().findCategoryPoById(categoryId);

        this.logger.info("Get tags by tag ids");
        List<Tag> tags = this.getTagService().findTagsPoByIds(tagIds);

        BlogVo blogVo = new BlogVo();
        if(isUpdate)
        {
            try
            {
                blogVo = this.blogService.getBlogById(id);
            }
            catch (BlogNotExistException ex)
            {
                modelAndView.setViewName("blogUpdate");
                modelAndView.addObject("errorMsg", ex.getMessage());
                return modelAndView;
            }
        }

        blogVo.setTitle(title);
        blogVo.setSummary(summary);
        blogVo.setContent(content);
        blogVo.setStatus(status);

        try
        {
            if(isUpdate)
            {
                this.logger.info("Update blogVo");
                this.blogService.updateBlog(blogVo, category, tags);
            }
            else
            {
                this.logger.info("Create blogVo");
                this.blogService.createBlog(blogVo, category, tags);
            }
        }
        catch (UserNotExistException ex)
        {
            this.logger.error("Admin user doesn't exist!!!");
            modelAndView.addObject("errorMsg", ex.getMessage());
            addAllTagAndCategoriesToModelAndView(modelAndView);

            if(isUpdate)
            {
                modelAndView.setViewName("blogUpdate");
            }
            else
            {
                modelAndView.setViewName("blogCreate");
            }
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/blog.html");
        return modelAndView;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showBlog(@PathVariable Integer id)
    {
        ModelAndView modelAndView = this.goToUpdateBlogPage(id);
        modelAndView.setViewName("blogDisplay");
        this.blogService.plusBlogViewCount(id);
        return modelAndView;
    }
}

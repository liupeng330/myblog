package pengliu.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.common.CommonConstant;
import pengliu.me.service.BlogService;
import pengliu.me.service.CategoryService;
import pengliu.me.service.TagService;
import pengliu.me.vo.CategoryVo;
import pengliu.me.vo.TagVo;

import javax.servlet.ServletContext;
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

    @Autowired
    private BlogService blogService;

    @Autowired
    private ServletContext context;

    protected void addAllTagAndCategoriesToModelAndView(ModelAndView modelAndView)
    {
        List<TagVo> tags = this.tagService.getAllTags();
        modelAndView.addObject("allTags", tags);

        List<CategoryVo> categories = this.getCategoryService().getAllCategories();
        modelAndView.addObject("allCategories", categories);
    }

    protected void addTopTenBlogToModelAndView(ModelAndView modelAndView)
    {
        modelAndView.addObject("topTenBlogs", this.getBlogService().getTopTenLatestPublicBLog());
        modelAndView.addObject("topTenViewCountBlogs", this.getBlogService().getTopTenViewCountPublicBLog());
    }


    protected CategoryService getCategoryService() {
        return categoryService;
    }

    protected TagService getTagService() {
        return tagService;
    }

    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    protected String getUploadImageRealPath()
    {
        return this.context.getRealPath(CommonConstant.UPLOAD_PATH);
    }
}

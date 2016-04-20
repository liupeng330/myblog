package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.common.CommonConstant;
import pengliu.me.dao.Page;
import pengliu.me.domain.Tag;
import pengliu.me.service.TagService;
import pengliu.me.vo.BlogVo;
import pengliu.me.vo.TagVo;

import java.util.List;

/**
 * Created by peng on 16-4-14.
 */
@Controller
@RequestMapping
public class TagController
{
    private Logger logger = Logger.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
    public ModelAndView getBlogsByCategory(@PathVariable Integer tagId, @RequestParam(value = "pageNo", required = false) Integer pageNo)
    {
        ModelAndView modelAndView = new ModelAndView();
        if(pageNo == null)
        {
            pageNo = 1;
        }

        TagVo tagVo = this.tagService.findTagById(tagId);
        modelAndView.addObject("tag", tagVo);

        Page<BlogVo> publishedBlogs = this.tagService.getAllPagedPublishedBlogsByTagId(tagId, pageNo, CommonConstant.PAGE_SIZE);
        modelAndView.addObject("pageResult", publishedBlogs);
        modelAndView.setViewName("/main");

        return modelAndView;
    }

    @RequestMapping(value = "/management/tag/listAll", method = RequestMethod.GET)
    public ModelAndView listAllTags()
    {
        ModelAndView modelAndView = new ModelAndView();

        List<TagVo> categories = this.tagService.getAllTags();
        modelAndView.addObject("allTags", categories);
        modelAndView.setViewName("/tagManager");

        return modelAndView;
    }

    @RequestMapping(value = "/management/tag/update/{id}", method = RequestMethod.GET)
    public ModelAndView gotToUpdateTagPage(@PathVariable Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();

        TagVo tag = this.tagService.findTagById(id);
        modelAndView.addObject("tag", tag);
        modelAndView.setViewName("/tagUpdate");

        return modelAndView;
    }

    @RequestMapping(value = "/management/tag/update", method = RequestMethod.POST)
    public String updateTag(Integer id, String newName)
    {
        ModelAndView modelAndView = new ModelAndView();
        this.tagService.updateTagNameById(id, newName);
        String target = "/management/tag/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }

    @RequestMapping(value = "/management/tag/create", method = RequestMethod.GET)
    public String goToCreateTagPage(String name)
    {
        return "tagCreate";
    }

    @RequestMapping(value = "/management/tag/create", method = RequestMethod.POST)
    public String createTag(String name)
    {
        this.logger.info("Start to create tag " + name);
        this.tagService.createTagByName(name);

        String target = "/management/tag/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }

    @RequestMapping(value = "/management/tag/delete/{id}", method = RequestMethod.GET)
    public String deleteTag(@PathVariable Integer id)
    {
        this.tagService.deleteTagById(id);
        String target = "/management/tag/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }
}

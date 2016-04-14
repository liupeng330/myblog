package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.domain.Tag;
import pengliu.me.service.TagService;

import java.util.List;

/**
 * Created by peng on 16-4-14.
 */
@Controller
@RequestMapping("/management/tag")
public class TagController
{
    private Logger logger = Logger.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public ModelAndView listAllTags()
    {
        ModelAndView modelAndView = new ModelAndView();

        List<Tag> categories = this.tagService.getAllTags();
        modelAndView.addObject("allTags", categories);
        modelAndView.setViewName("/tagManager");

        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView gotToUpdateTagPage(@PathVariable Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();

        Tag tag = this.tagService.findTagById(id);
        modelAndView.addObject("tag", tag);
        modelAndView.setViewName("/tagUpdate");

        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateTag(Integer id, String newName)
    {
        ModelAndView modelAndView = new ModelAndView();
        this.tagService.updateTagNameById(id, newName);
        String target = "/management/tag/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String goToCreateTagPage(String name)
    {
        return "tagCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTag(String name)
    {
        this.logger.info("Start to create tag " + name);
        this.tagService.createTagByName(name);

        String target = "/management/tag/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTag(@PathVariable Integer id)
    {
        this.tagService.deleteTagById(id);
        String target = "/management/tag/listAll.html";
        this.logger.info("Redirect to " + target);

        return "redirect:" + target;
    }
}

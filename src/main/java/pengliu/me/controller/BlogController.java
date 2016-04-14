package pengliu.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by peng on 16-4-14.
 */
@Controller
@RequestMapping("/blog")
public class BlogController
{
    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage()
    {
        return "main";
    }
}

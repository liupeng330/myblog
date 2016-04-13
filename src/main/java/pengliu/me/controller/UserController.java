package pengliu.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.domain.User;
import pengliu.me.service.UserService;

/**
 * Created by peng on 16-4-13.
 */
@Controller
@RequestMapping("/management/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categoryManager");
        return modelAndView;
    }
}

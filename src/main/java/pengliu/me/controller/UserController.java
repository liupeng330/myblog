package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.common.CommonConstant;
import pengliu.me.domain.User;
import pengliu.me.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by peng on 16-4-13.
 */
@Controller
@RequestMapping("/management/user")
public class UserController
{
    private Logger logger = Logger.getLogger(UserController.class);

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
    public ModelAndView login(HttpServletRequest request, User user)
    {
        ModelAndView modelAndView = new ModelAndView();

        if(this.userService.canLogin(user))
        {
            this.userService.updateLoginTime(user.getName());

            this.logger.info(String.format("将user'%s'加入到session中", user.getName()));
            request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);

            Object targetUrlBeforeLogin = request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);

            String target = targetUrlBeforeLogin==null ? CommonConstant.DEFAULT_URL : targetUrlBeforeLogin.toString();
            this.logger.info(String.format("跳转页面为'%s'", target));
            modelAndView.setViewName("redirect:" + target);
        }
        else
        {
            this.logger.error(String.format("用户'%s'登陆失败", user.getName()));
            modelAndView.addObject("errorMsg", "登陆失败，请重试");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}

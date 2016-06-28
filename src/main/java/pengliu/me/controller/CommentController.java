package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.service.CommentService;
import pengliu.me.vo.CommentVo;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController
{
    private Logger logger = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createComment(HttpServletRequest request, @ModelAttribute("commentForm") CommentVo commentVo)
    {
        //is client behind something?
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null)
        {
            ipAddress = request.getRemoteAddr();
        }
        commentVo.setUserremoteIp(ipAddress);

        try
        {
            this.commentService.createComment(commentVo);
        }
        catch (Exception ex)
        {
            return String.format("redirect:/blog/show/%d.html?errorMsg=%s#comments", commentVo.getBlogId(), ex.getMessage());
        }

        return String.format("redirect:/blog/show/%d.html#comments", commentVo.getBlogId());

    }
}

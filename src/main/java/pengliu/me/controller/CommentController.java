package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pengliu.me.common.CommonConstant;
import pengliu.me.domain.CommentUser;
import pengliu.me.service.CommentService;
import pengliu.me.vo.CommentVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController
{
    private Logger logger = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createComment(HttpServletRequest request, @ModelAttribute("commentForm") CommentVo commentVo) {
        if (commentVo.getRememberMe()) {
            CommentUser commentUser = new CommentUser();
            commentUser.setName(commentVo.getUserName());
            commentUser.setBlogUrl(commentVo.getUserUrl());
            commentUser.setEmail(commentVo.getUserEmail());
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstant.COMMENT_USER_CONTEXT, commentUser);
        }

        //is client behind something?
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        this.logger.info("Creating comment: IpAddress from remote is " + ipAddress);
        if (ipAddress == null)
        {
            ipAddress = request.getRemoteAddr();
        }
        commentVo.setUserremoteIp(ipAddress);
        this.logger.info("Creating comment: IpAddress from remote is " + ipAddress);

//        if (!ipAddress.equals(CommonConstant.LOCALHOST_IP))
//        {
            try
            {
                //用户输入的验证码的值
                String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                String kaptchaRecived = request.getParameter("kaptcha");
                //校验验证码是否正确
                if (kaptchaExpected == null || !kaptchaRecived.equals(kaptchaExpected))
                {
                    return String.format("redirect:/blog/show/%d.html?errorMsg=%s#comments",
                            commentVo.getBlogId(),
                            URLEncoder.encode("验证码错误!!", "UTF-8"));
                }

                this.logger.info("Start to create comment.");
                this.commentService.createComment(commentVo);
            }
            catch (Exception ex)
            {
                String message = null;
                try
                {
                    message = URLEncoder.encode(ex.getMessage(), "UTF-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    this.logger.error("Fail to encode message of " + e.getMessage());
                }
                this.logger.error("Error happened when creating comment!! " + ex.getMessage());
                String target = String.format("redirect:/blog/show/%d.html?errorMsg=%s#comments", commentVo.getBlogId(), message);
                return target;
            }
//        }
//        else
//        {
//            this.logger.info("Ignore creating comment.");
//        }

        return String.format("redirect:/blog/show/%d.html#comments", commentVo.getBlogId());
    }

    @RequestMapping(value = "/delete/{blogId}/{commentId}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable Integer blogId, @PathVariable Integer commentId)
    {
        this.commentService.deleteCommentById(commentId);
        return String.format("redirect:/blog/show/%d.html#comments", blogId);
    }
}

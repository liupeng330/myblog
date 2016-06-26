package pengliu.me.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController
{
    private Logger logger = Logger.getLogger(CommentController.class);

}

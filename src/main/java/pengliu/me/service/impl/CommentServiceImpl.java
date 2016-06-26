package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pengliu.me.dao.CommentDao;
import pengliu.me.domain.Comment;
import pengliu.me.service.BlogService;
import pengliu.me.service.CommentService;
import pengliu.me.vo.CommentVo;

/**
 * Created by peng on 6/26/16.
 */
public class CommentServiceImpl implements CommentService
{
    private Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogService blogService;

    public void createComment(CommentVo commentVo, Integer blogId)
    {

    }
}

package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pengliu.me.dao.BlogDao;
import pengliu.me.dao.CommentDao;
import pengliu.me.dao.CommentUserDao;
import pengliu.me.domain.Blog;
import pengliu.me.domain.Comment;
import pengliu.me.domain.CommentUser;
import pengliu.me.exception.BlogNotExistException;
import pengliu.me.exception.CommentUserHasExistException;
import pengliu.me.service.BlogService;
import pengliu.me.service.CommentService;
import pengliu.me.utils.CommonUtil;
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
    private CommentUserDao commentUserDao;

    @Autowired
    private BlogDao blogDao;

    public void createComment(CommentVo commentVo, Integer blogId) throws BlogNotExistException, CommentUserHasExistException
    {
        //Verify if comment user has been exist
        CommentUser userFindByEmail = this.commentUserDao.findCommentUserByEmail(commentVo.getUserEmail());
        CommentUser userFindByNickName = this.commentUserDao.findCommentUserByNickName(commentVo.getUserName());

        StringBuffer sb = new StringBuffer();
        if(userFindByEmail != null && userFindByNickName != null)
        {
            if(!userFindByEmail.equals(userFindByNickName))
            {
                throw new CommentUserHasExistException(
                        String.format("昵称'%s'和邮箱'%s'已经有人使用了，请换一个吧", commentVo.getUserName(), commentVo.getUserEmail()));
            }
        }
        //save comment user to db
        if(userFindByEmail == null && userFindByNickName == null)
        {
            CommentUser commentUser = new CommentUser();
            commentUser.setEmail(commentVo.getUserEmail());
            commentUser.setName(commentVo.getUserName());
            commentUser.setRemoteIp(commentVo.getUserremoteIp());
            commentUser.setBlogUrl(commentVo.getUserUrl());
            commentUser.setUpdateTime(CommonUtil.getTimeStampNow());
            this.commentUserDao.persist(commentUser);
        }

        //save comment to db
        Comment comment = new Comment();
        comment.setCreateTime(CommonUtil.getTimeStampNow());
        comment.setContent(commentVo.getContent());
        comment.setCommentUser(userFindByEmail);
        Blog blog = this.blogDao.get(blogId);
        if(blog == null)
        {
            throw new BlogNotExistException(String.format("Blog doesn't exist for blog id %s!!", blogId));
        }
        comment.setBlog(blog);
        this.commentDao.persist(comment);
    }
}

package pengliu.me.service;

import pengliu.me.vo.CommentVo;

public interface CommentService
{
    void createComment(CommentVo commentVo, Integer blogId);
}

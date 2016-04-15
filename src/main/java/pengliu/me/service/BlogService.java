package pengliu.me.service;

import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.domain.Tag;
import pengliu.me.exception.UserNotExistException;
import pengliu.me.vo.BlogVo;

import java.util.List;

/**
 * Created by peng on 16-4-15.
 */
public interface BlogService
{
    void createBlog(Blog blog, Category category, List<Tag> tags) throws UserNotExistException;
    List<BlogVo> getAllPublishedBlogs();
}

package pengliu.me.service;

import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.domain.Tag;
import pengliu.me.exception.BlogNotExistException;
import pengliu.me.exception.UserNotExistException;
import pengliu.me.vo.BlogVo;
import pengliu.me.vo.CategoryVo;
import pengliu.me.vo.TagVo;

import java.util.List;

/**
 * Created by peng on 16-4-15.
 */
public interface BlogService
{
    void createBlog(BlogVo blogVo, Category category, List<Tag> tags) throws UserNotExistException;
    BlogVo getBlogById(Integer id) throws BlogNotExistException;
    List<BlogVo> getAllPublishedBlogs();
    List<BlogVo> getAllBlogs();
    void deleteBlogById(Integer id);
}

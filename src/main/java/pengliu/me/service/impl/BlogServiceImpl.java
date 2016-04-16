package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.dao.BlogDao;
import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.domain.Tag;
import pengliu.me.domain.User;
import pengliu.me.exception.BlogNotExistException;
import pengliu.me.exception.UserNotExistException;
import pengliu.me.service.BlogService;
import pengliu.me.service.UserService;
import pengliu.me.utils.Common;
import pengliu.me.utils.Transfer;
import pengliu.me.vo.BlogVo;
import pengliu.me.vo.CategoryVo;
import pengliu.me.vo.TagVo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by peng on 16-4-15.
 */
@Service
public class BlogServiceImpl implements BlogService
{
    private Logger logger = Logger.getLogger(BlogServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BlogDao blogDao;

    public void createBlog(BlogVo blogVo, Category category, List<Tag> tags) throws UserNotExistException
    {
        this.logger.info("Get admin user");
        User user = this.userService.getAdminUser();
        Blog blog = Transfer.transferBlogVoToPo(blogVo);
        blog.setCategory(category);
        blog.setTags(new HashSet<Tag>(tags));
        blog.setCreateTime(Common.getTimeStampNow());
        blog.setUpdateTime(Common.getTimeStampNow());
        blog.setShowCount(0);
        blog.setUser(user);

        this.blogDao.persist(blog);
    }

    public List<BlogVo> getAllPublishedBlogs()
    {
        return this.populateAllBlogsToVo(this.blogDao.getAllPublishedBlogs());
    }

    public BlogVo getBlogById(Integer id) throws BlogNotExistException
    {
        Blog blog = this.blogDao.get(id);
        if(blog == null)
        {
            throw new BlogNotExistException("Blog for id " + id + " doesn't exist!!!");
        }

        return Transfer.transferBlogPoToVo(blog);
    }

    public List<BlogVo> getAllBlogs()
    {
        return this.populateAllBlogsToVo(this.blogDao.getAllBlogs());
    }

    private List<BlogVo> populateAllBlogsToVo(List<Blog> blogsFromDB)
    {
        List<BlogVo> blogVos = new ArrayList<BlogVo>();
        for(Blog blog: blogsFromDB)
        {
            blogVos.add(Transfer.transferBlogPoToVo(blog));
        }
        return blogVos;
    }

    public void deleteBlogById(Integer id)
    {
        this.blogDao.deleteBlogById(id);
    }

}

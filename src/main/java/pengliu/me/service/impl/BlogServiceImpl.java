package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.common.BlogStatus;
import pengliu.me.dao.BlogDao;
import pengliu.me.domain.Blog;
import pengliu.me.domain.Category;
import pengliu.me.domain.Tag;
import pengliu.me.domain.User;
import pengliu.me.exception.UserNotExistException;
import pengliu.me.service.BlogService;
import pengliu.me.service.UserService;
import pengliu.me.utils.Common;
import pengliu.me.vo.BlogVo;

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

    public void createBlog(Blog blog, Category category, List<Tag> tags) throws UserNotExistException
    {
        this.logger.info("Get admin user");
        User user = this.userService.getAdminUser();
        blog.setCategory(category);
        blog.setTags(new HashSet<Tag>(tags));
        blog.setCreateTime(Common.getTimeStampNow());
        blog.setUpdateTime(Common.getTimeStampNow());
        blog.setShowCount(0);
        blog.setUser(user);
        blog.setStatus(BlogStatus.CREATED);
        if(blog.getStatusInString() != null)
        {
            blog.setStatus(BlogStatus.valueOf(blog.getStatusInString()));
        }

        this.blogDao.persist(blog);
    }

    public List<BlogVo> getAllPublishedBlogs()
    {
        return this.populateAllBlogsToVo(this.blogDao.getAllPublishedBlogs());
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
            BlogVo blogVo = new BlogVo();
            blogVo.setId(blog.getId());
            blogVo.setTitle(blog.getTitle());
            blogVo.setSummary(blog.getSummary());
            blogVo.setContent(blog.getContent());
            blogVo.setUpdateTime(blog.getUpdateTime());
            blogVo.setCreateTime(blog.getCreateTime());
            blogVo.setShowCount(blog.getShowCount());
            blogVo.setUserName(blog.getUser().getName());
            blogVo.setCategoryName(blog.getCategory().getName());
            blogVo.setStatus(blog.getStatus().toString());

            List<String> tagNams = new ArrayList<String>();
            for(Tag tag: blog.getTags())
            {
                tagNams.add(tag.getName());
            }

            blogVo.setTagNames(new HashSet<String>(tagNams));
            blogVos.add(blogVo);
        }
        return blogVos;
    }


    public void deleteBlogById(Integer id)
    {
        this.blogDao.deleteBlogById(id);
    }

}

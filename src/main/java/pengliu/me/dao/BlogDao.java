package pengliu.me.dao;

import org.springframework.stereotype.Repository;
import pengliu.me.common.BlogStatus;
import pengliu.me.domain.Blog;

import java.util.List;

/**
 * Created by peng on 16-4-15.
 */
@Repository
public class BlogDao extends BaseDaoHibernate4<Blog>
{
    public List<Blog> getAllBlogsByStatus(BlogStatus status)
    {
        List<Blog> blogs = this.getList("status", status);
        return blogs;
    }

    public List<Blog> getAllPublishedBlogs()
    {
        return this.getAllBlogsByStatus(BlogStatus.PUBLISHED);
    }
}

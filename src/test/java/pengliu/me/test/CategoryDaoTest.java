package pengliu.me.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pengliu.me.dao.CategoryDao;

/**
 * Created by peng on 4/10/16.
 */
public class CategoryDaoTest extends BaseDaoTest
{
    private CategoryDao categoryDao;

    @Before
    public void init()
    {
        this.categoryDao = new CategoryDao();
        this.categoryDao.setSessionFactory(this.sessionFactory);
    }

    @After
    public void cleanup()
    {
        System.out.println("In cleanup method");
    }

    @Test
    public void testCreateCategory()
    {
        Assert.assertNotNull(this.categoryDao);
    }

    @Test
    public void testDeleteCategory()
    {
        Assert.assertNotNull(this.categoryDao);
    }
}

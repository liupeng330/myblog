package pengliu.me.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.core.Unitils;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;
import pengliu.me.dao.CategoryDao;

/**
 * Created by peng on 4/10/16.
 */
@SpringApplicationContext( {"applicationContext.xml" })
public class CategoryDaoTest extends UnitilsJUnit4
{
    @SpringBeanByType
    private CategoryDao categoryDao;

    @Before
    public void init()
    {
        System.out.println("In init method");
    }

    @After
    public void cleanup()
    {
        System.out.println("In cleanup method");
    }

    @Test
    @DataSet("category.create.xls")
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

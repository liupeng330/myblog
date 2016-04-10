package pengliu.me.test;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.orm.hibernate.annotation.HibernateSessionFactory;

/**
 * Created by peng on 4/10/16.
 */
@HibernateSessionFactory("hibernate.cfg.xml")
public class BaseDaoTest extends UnitilsJUnit4
{
    @HibernateSessionFactory
    public SessionFactory sessionFactory;

    @Test
    public void testSessionFacotry()
    {
        Assert.assertNotNull(this.sessionFactory);
    }
}

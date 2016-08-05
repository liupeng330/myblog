package pengliu.me.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import pengliu.me.dao.CategoryDao;
import pengliu.me.domain.Category;
import pengliu.me.service.impl.CategoryServiceImpl;
import pengliu.me.utils.CommonUtil;
import pengliu.me.vo.CategoryVo;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

//使用mockito去mock service依赖的dao对象，来测试service层的业务逻辑
@SpringApplicationContext({"applicationContext.xml"})
public class MockDaoToTestService extends UnitilsJUnit4
{
    private CategoryDao mockedCategoryDao;

    @Before
    public void init()
    {
        this.mockedCategoryDao = mock(CategoryDao.class);
    }

    @Test
    public void findCategoryByIdTest()
    {
        Category category = new Category();
        category.setId(1);
        Timestamp now = CommonUtil.getTimeStampNow();
        category.setUpdateTime(now);
        category.setCreateTime(now);
        category.setName("testing for find category by id");

        // 设置categoryDao.get方法当传入1的时候，返回上面的category对象
        doReturn(category).when(this.mockedCategoryDao).get(1);

        // 创建被测对象，即业务逻辑对象, 并使用mock的categoryDao对象去设置categoryService引用的categoryDao对象
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        ReflectionTestUtils.setField(categoryService, "categoryDao", this.mockedCategoryDao);

        // 开始调用被测试的业务方法，并验证结果
        CategoryVo needToVerifyCategoryVo = categoryService.findCategoryById(1);
        assertNotNull("The returned value of categoryService.findCategoryById return a null object!!", needToVerifyCategoryVo);
        assertEquals("The name of category is incorrect!!", category.getName(), needToVerifyCategoryVo.getName());
        assertEquals("The create time of category is incorrect!!", category.getCreateTime(), needToVerifyCategoryVo.getCreateTime());
        assertEquals("The update time of category is incorrect!!", category.getUpdateTime(), needToVerifyCategoryVo.getUpdateTime());
        assertEquals("The id of category is incorrect!!", category.getId(), needToVerifyCategoryVo.getId());

        // 验证mock的dao被调用了一次
        verify(mockedCategoryDao, atLeastOnce()).get(1);
    }
}


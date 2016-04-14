package pengliu.me.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengliu.me.dao.TagDao;
import pengliu.me.domain.Tag;
import pengliu.me.service.TagService;

import java.util.List;

/**
 * Created by peng on 16-4-14.
 */
@Service
public class TagServiceImpl implements TagService
{
    private Logger logger = Logger.getLogger(TagServiceImpl.class);

    @Autowired
    private TagDao tagDao;

    public void createTagByName(String name)
    {
        this.tagDao.createTagByName(name);
    }

    public Tag findTagById(Integer id)
    {
        return this.tagDao.get(id);
    }

    public void updateTagNameById(Integer id, String newName)
    {
        this.tagDao.updateTagNameById(id, newName);
    }

    public void deleteTagById(Integer id)
    {
        this.tagDao.deleteTagById(id);
    }

    public List<Tag> getAllTags()
    {
        return this.tagDao.getAllTags();
    }
}

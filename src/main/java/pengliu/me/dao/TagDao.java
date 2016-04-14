package pengliu.me.dao;

import org.springframework.stereotype.Repository;
import pengliu.me.domain.Tag;
import pengliu.me.utils.Common;

import java.util.List;

/**
 * Created by peng on 16-4-14.
 */
@Repository
public class TagDao extends BaseDaoHibernate4<Tag>
{
    public void createTagByName(String name)
    {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setCreateTime(Common.getTimeStampNow());
        tag.setUpdateTime(Common.getTimeStampNow());
        this.persist(tag);
    }

    public void updateTagNameById(Integer id, String newName)
    {
        Tag tag = this.get(id);
        tag.setName(newName);
        tag.setUpdateTime(Common.getTimeStampNow());
    }

    public void deleteTagById(Integer id)
    {
        this.delete(id);
    }

    public List<Tag> getAllTags()
    {
        return this.findAll();
    }
}

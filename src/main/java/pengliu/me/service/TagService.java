package pengliu.me.service;

import pengliu.me.domain.Tag;
import pengliu.me.vo.TagVo;

import java.util.List;

/**
 * Created by peng on 16-4-14.
 */
public interface TagService
{
    void createTagByName(String name);
    TagVo findTagById(Integer id);
    List<TagVo> findTagsByIds(Integer... id);
    void updateTagNameById(Integer id, String newName);
    void deleteTagById(Integer id);
    List<TagVo> getAllTags();
    List<Tag> findTagsPoByIds(Integer... id);
}

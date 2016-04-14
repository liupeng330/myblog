package pengliu.me.service;

import pengliu.me.domain.Tag;

import java.util.List;

/**
 * Created by peng on 16-4-14.
 */
public interface TagService
{
    void createTagByName(String name);
    Tag findTagById(Integer id);
    void updateTagNameById(Integer id, String newName);
    void deleteTagById(Integer id);
    List<Tag> getAllTags();
}

package pengliu.me.vo;

import java.sql.Timestamp;

/**
 * Created by peng on 4/16/16.
 */
public class TagVo
{
    private Integer id;
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;
    private boolean isChecked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagVo tagVo = (TagVo) o;

        if (id != null ? !id.equals(tagVo.id) : tagVo.id != null) return false;
        if (name != null ? !name.equals(tagVo.name) : tagVo.name != null) return false;
        if (createTime != null ? !createTime.equals(tagVo.createTime) : tagVo.createTime != null) return false;
        return updateTime != null ? updateTime.equals(tagVo.updateTime) : tagVo.updateTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}

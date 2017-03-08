package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class Permission extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Date createTime;

    /**  **/
    private String info;

    /**  **/
    private String name;

    /**  **/
    private Integer sequence;

    /** flag **/
    private String type;

    /**  **/
    private String value;

    /**  **/
    private String groupName;

    /**  **/
    private Integer userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
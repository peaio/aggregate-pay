package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class SettingKeyVal extends BaseModel {
    /** 类型 **/
    private String type;

    /** key值，对应对象的字段名 **/
    private String key;

    /**  **/
    private String value;

    /**  **/
    private Date updateTime;

    /**  **/
    private Date createTime;

    /** 说明 **/
    private String info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}
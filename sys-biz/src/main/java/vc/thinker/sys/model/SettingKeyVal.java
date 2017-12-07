package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class SettingKeyVal extends BaseModel {
    /** key值，对应对象的字段名 **/
    private String fkey;

    /** 类型 **/
    private String type;

    /**  **/
    private String fvalue;

    /**  **/
    private Date updateTime;

    /**  **/
    private Date createTime;

    /** 说明 **/
    private String info;

    public String getFkey() {
        return fkey;
    }

    public void setFkey(String fkey) {
        this.fkey = fkey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFvalue() {
        return fvalue;
    }

    public void setFvalue(String fvalue) {
        this.fvalue = fvalue;
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
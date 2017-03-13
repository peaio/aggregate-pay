package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;

public class DicArea extends BaseModel {
    /**  **/
    private Integer id;

    /**  **/
    private String code;

    /**  **/
    private String name;

    /**  **/
    private String parentCode;

    /**  **/
    private Integer language;

    /**  **/
    private Integer sequence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
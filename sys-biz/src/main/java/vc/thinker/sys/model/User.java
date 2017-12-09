package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class User extends BaseModel {
    /** id **/
    private Long id;

    /** 创建时间 **/
    private Date createTime;

    /** 创建人 **/
    private String createBy;

    /** 修改时间 **/
    private Date updateTime;

    /** 修改人 **/
    private String updateBy;

    /** 是否删除 **/
    private Boolean isDeleted;

    /** 注册id **/
    private String registIp;

    /**  **/
    private Long companyId;

    /**  **/
    private Long officeId;

    /**  **/
    private String dataScope;

    /** 邀请码 **/
    private String invitationCode;

    /** 国家 **/
    private String country;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
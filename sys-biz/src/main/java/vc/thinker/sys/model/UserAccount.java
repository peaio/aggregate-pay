package vc.thinker.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import com.sinco.mybatis.dal.model.DataModel;

import java.util.Date;

public class UserAccount extends DataModel {
    /**  **/
    private Long id;

    /**  **/
    private Long uid;

    /**  **/
    private String loginName;

    /**  **/
    private String password;

    /** 账户类型 1:用户名 **/
    private String accountType;

    /**  **/
    private Date lastLoginTime;

    /**  **/
    private String lastLoginIp;

    /** 是否删除 **/
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
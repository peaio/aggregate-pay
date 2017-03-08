package vc.thinker.sys.model;


import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class Template extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Date addTime;

    /**  **/
    private Integer deleteStatus;

    /** 模板描述 **/
    private String info;

    /** 模板代码 根据模板代码获取对应的模板 **/
    private String mark;

    /** 是否开启 开启后可使用 **/
    private Boolean open;

    /** 模板标题  使用velocity标签管理 **/
    private String title;

    /** 模板类型 Msg:站内短信模板，email：邮件模板，sms：手机短信模板 **/
    private String type;

    /** 模板内容 使用${}velocity模板标签 **/
    private String content;
    
    /** 次数限制（默认0：不限制） **/
    private Integer times;
    
    private String thirdTemplateId;
    
    public String getThirdTemplateId() {
		return thirdTemplateId;
	}

	public void setThirdTemplateId(String thirdTemplateId) {
		this.thirdTemplateId = thirdTemplateId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
    
}
package vc.thinker.sys.vo;

public class TemplateVO {

	private String id;
	
	//模板类型 Msg:站内短信模板，email：邮件模板，sms：手机短信模板
	private String type;
	
	/** 模板描述 **/
    private String info;

    /** 模板代码 根据模板代码获取对应的模板 **/
    private String mark;

    /** 是否开启 开启后可使用 **/
    private Boolean open;

    /** 模板标题  使用velocity标签管理 **/
    private String title;
    
    /** 模板内容 使用${}velocity模板标签 **/
    private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
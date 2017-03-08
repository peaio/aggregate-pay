package vc.thinker.sys.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.sinco.dic.client.model.LangDicBase;

import vc.thinker.dal.common.persistence.mybatis.DataEntity;

public class Dict extends DataEntity<User> implements LangDicBase,Comparable<Dict>{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String label;	// 标签名
	private String labelEn;	// 标签名
	private String value;	// 数据值
	private String type;	// 类型
	private String description;// 描述
	private Integer sort;	// 排序
	
	public String getLabelEn() {
		return labelEn;
	}

	public void setLabelEn(String labelEn) {
		this.labelEn = labelEn;
	}

	public Dict() {
		super();
	}
	
	public Dict(Long id) {
		this();
		this.id = id;
	}

	@Length(min=1, max=100)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=100)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Length(min=1, max=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getCode() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.label;
	}
	
	@Override
	public int compareTo(Dict o) {
		return this.getSort().compareTo(o.getSort());
	}

	@Override
	public String getName(String local) {
		if( "en".equals(local) || (local != null && local.startsWith("en"))){
			return StringUtils.isBlank(this.labelEn) ? label : this.labelEn;
		}
		return label;
	}
}
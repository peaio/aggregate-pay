<#include "/include/taglib.ftl" >
<html>
<head>
	<title></title>
	<link href="${pubStatic}/wgt/area/selectAddress.css" rel="stylesheet" type="text/css" />
	<script src="${pubStatic}/wgt/area/selectAddress.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			$("input[name='areaId']").areaSelect({
        		dateUrl: "${request.contextPath}/dic/getAreaJsPath"
        	});
		});
	</script>
</head>
<body>


	<div>
	<#-- 
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
				<li>
				    <span>系统设置<i class="fa fa-angle-right"></i></span>
				</li>
                 <li>
                 <span>机构管理</span>
                 <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:office:edit">
                    ${(office.id??)?string('修改','添加')}
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="sys:office:edit">
                    	查看
                    </@shiro.lacksPermission>
                    </span>
                </li>
            </ul>
        </div>
	 -->
	<div class="portlet light ">
	
			<ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/office/list">
	              机构管理</a>
	            </li>
	            <li class="active">
	                <a  href="javascript:;">
	                 <@shiro.hasPermission name="sys:office:edit">
                    ${(office.id??)?string('修改','添加')}
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="sys:office:edit">
                   		 查看
                    </@shiro.lacksPermission>
	            	 </a>
	            </li>
        	</ul>
            <div class="portlet-body form">	
	<@form.form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
		<@form.hidden path="id"/>
		<@tags.message content=message! />
		<div class="form-body">
		<div class="form-group">
			<label class="col-xs-3 control-label">上级机构:</label>
			<div class="col-xs-4">
                <@tags.treeselect id="office" name="parentId" value=office.parentId! labelName="parent.name" labelValue=(office.parent.name)!
					title='上级机构' url="/sys/office/treeData" extId=office.id cssClass="required form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">归属区域:</label>
			<div class="col-xs-4">
				<div id=""></div>
				<@form.hidden path="areaId" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">机构名称:</label>
			<div class="col-xs-4">
				<@form.input path="name" htmlEscape=false maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">机构编码:</label>
			<div class="col-xs-4">
				<@form.input path="code" htmlEscape=false maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">机构类型:</label>
			<div class="col-xs-4">
				<@form.select path="type" cssClass="form-control">
					<@form.options items=fns.getDictList('sys_office_type') itemLabel="label" itemValue="value" htmlEscape=false/>
				</@form.select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">机构级别:</label>
			<div class="col-xs-4">
				<@form.select path="grade" cssClass="form-control">
					<@form.options items=fns.getDictList('sys_office_grade') itemLabel="label" itemValue="value" htmlEscape=false/>
				</@form.select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">联系地址:</label>
			<div class="col-xs-4">
				<@form.input path="address" htmlEscape=false maxlength="50" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">邮政编码:</label>
			<div class="col-xs-4">
				<@form.input path="zipCode" htmlEscape=false maxlength="50" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">负责人:</label>
			<div class="col-xs-4">
				<@form.input path="master" htmlEscape=false maxlength="50" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">电话:</label>
			<div class="col-xs-4">
				<@form.input path="phone" htmlEscape=false maxlength="50" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">传真:</label>
			<div class="col-xs-4">
				<@form.input path="fax" htmlEscape=false maxlength="50" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">邮件:</label>
			<div class="col-xs-4">
				<@form.input path="email" htmlEscape=false maxlength="50" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">备注:</label>
			<div class="col-xs-4">
				<@form.textarea path="remarks" htmlEscape=false rows="3" maxlength="200" class="form-control input-xlarge"/>
			</div>
		</div>
		
		<div class="form-actions">
			<div class="row">
                <div class="col-xs-offset-3 col-xs-4">
				<@shiro.hasPermission name="sys:user:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				</@shiro.hasPermission>
				</div>
            </div>
		</div>
	</@form.form>
	</div>
   </div>

	</div>
</body>
</html>
<#include "/include/taglib.ftl" >
<html>
<head>
	<title>模板管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#info").focus();
			$("#inputForm").validate({
				rules: {
					mark: {
						remote: "${ctx}/sys/template/checkMark?id="+$('#id').val()+"&mark=" + $('#mark').val()
					},
					times:{digits:true}
				},
				messages: {
					mark: {
						remote: "模板标识已存在"
					},
					times:{digits:"必须为数字"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
		});
	</script>
</head>
<body>

	<div class="page-container-custom">
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
                 <span>模板管理</span>
                 <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:template:edit">
                    <#if template.id??>
                                                     修改<#else>
                                                     添加</#if>
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="sys:template:edit">
                                                     查看
                    </@shiro.lacksPermission>
                    </span>
                </li>
            </ul>
        </div>

	<#if message??><@tags.message content=message /></#if>
	<div class="portlet light ">
			<ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/template/">
	                                          模板列表
	                </a>
	            </li>
	            <li class="active">
	                <a  href="javascript:;">
	                 <@shiro.hasPermission name="sys:template:edit">
                    <#if template.id??>
                                                     修改<#else>
                                                     添加</#if>
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="sys:template:edit">
                                                     查看
                    </@shiro.lacksPermission>
	            	 </a>
	            </li>
        	</ul>
    <div class="portlet-body form">
                <!-- BEGIN FORM-->
	<@form.form id="inputForm" modelAttribute="template" action="${ctx}/sys/template/save" method="post" class="form-horizontal">
		<@form.hidden path="id"/>
		<div class="form-body">
			<div class="form-group">
				<label class="col-md-3 control-label">模板描述:</label>
				<div class="col-md-4">
					<@form.input path="info" minlength="6" maxlength="80" class="form-control required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">模板标题:</label>
				<div class="col-md-4">
					<@form.input path="title" htmlEscape=false minlength="6" maxlength="50" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">模板标识:</label>
				<div class="col-md-4">
					<@form.input path="mark" minlength="6" maxlength="50" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">模板类型:</label>
				<div class="col-md-4">
					<#if (template.type)??><#assign x=template.type><#else><#assign x='sms'></#if>
					<select id="type" name="type" class="form-control">
						<option value="sms" <#if x == 'sms'>selected</#if>>--短信模板--</option>
						<option value="email" <#if x == 'email'>selected</#if>>--邮件模板--</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">限制次数:</label>
				<div class="col-md-4">
					<@form.input path="times" maxlength="2" minlength="1" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">内容:</label>
				<div class="col-md-4">
					<@form.textarea path="content" htmlEscape=false maxlength="100" class="form-control required"/>
				</div>
			</div>
			<#if template.open??><#assign x="${template.open?c}"><#else><#assign x="true"></#if> 
			<div class="form-group">
				<label class="col-md-3 control-label">是否开启:</label>
				<div class="col-md-4">
					<select id="open" name="open" class="input-small form-control">
						<option value='1' <#if x=='true'>selected='selected'</#if>>是</option>
						<option value='0' <#if x=='false'>selected='selected'</#if>>否</option>
					<select>
				</div>
			</div>
			<div class="form-actions">
				<@shiro.hasPermission name="sys:template:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</@shiro.hasPermission>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</@form.form>
	</div>
   </div>



	</div>
</body>
</html>
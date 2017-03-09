<#include "/include/taglib.ftl" >
<html>
<head>
	<title>字典管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate();
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
                    <span>系统设置</span><i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>字典管理</span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
        	<ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/dict/">
	             字典列表 </a>
	            </li>
	            <li class="active">
	                <a  href="javascript:;">
	                <#if dict.id?? >修改<#else>添加</#if>
	            	 </a>
	            </li>
        	</ul>
            <div class="portlet-body form">
			<#if message??><@tags.message content=message /></#if>
			<@form.form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label" for="value">键值<span class="c-red">*</span>:</label>
					<div class="col-md-4">
						<@form.input path="value" htmlEscape=false maxlength="50" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="label">标签<span class="c-red">*</span>:</label>
					<div class="col-md-4">
						<@form.input path="label" htmlEscape=false maxlength="50" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="label">标签(英文):</label>
					<div class="col-md-4">
						<@form.input path="labelEn" htmlEscape=false maxlength="50" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="type">类型<span class="c-red">*</span>:</label>
					<div class="col-md-4">
						<@form.input path="type" htmlEscape=false maxlength="50" class="form-control required abc"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="description">描述<span class="c-red">*</span>:</label>
					<div class="col-md-4">
						<@form.input path="description" htmlEscape=false maxlength="50" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="sort">排序<span class="c-red">*</span>:</label>
					<div class="col-md-4">
						<@form.input path="sort" htmlEscape=false maxlength="11" class="form-control required digits"/>
					</div>
				</div>
				<div class="form-actions">
					<@shiro.hasPermission name="sys:dict:edit">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
					</@shiro.hasPermission>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
				</div>
			</@form.form>
	       </div>
        </div>
	</div>
</body>
</html>
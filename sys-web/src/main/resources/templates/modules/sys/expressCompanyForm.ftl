<#include "/include/taglib.ftl" >
<html>
<head>
	<title>快递管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#info").focus();
			$("#inputForm").validate({
				rules:{
				  companySequence:{digits:true},
				  companyPrice:{number:true},
				  companyMark:{
					   remote: "${ctx}/sys/express/expressCompanyMark?id="+$('#id').val()+"&companyMark=" + $('#companyMark').val()
				  }
				 },
				messages:{
					companySequence:{digits:"序号只能为数字"},
					 companyPrice:{number:"必须为数字"},
					companyMark:{remote:"快递代码已经存在"}
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/express/">快递列表</a></li>
		<li class="active"><a href="${ctx}/sys/express/form?id=${express.id!}">模板<@shiro.hasPermission name="sys:express:edit"><#if express.id??>修改<#else>添加</#if></@shiro.hasPermission><@shiro.lacksPermission name="sys:express:view">查看</@shiro.lacksPermission></a></li>
	</ul><br/>
	<#if message??><@tags.message content=message /></#if>
	<@form.form id="inputForm" modelAttribute="express" action="${ctx}/sys/express/save" method="post" class="form-horizontal">
		<@form.hidden path="id"/>
		<div class="control-group">
			<label class="control-label" for="companyName">快递名称:</label>
			<div class="controls">
				<@form.input path="companyName" minlength="4" maxlength="80" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="companyMark">快递代码:</label>
			<div class="controls">
				<@form.input path="companyMark" htmlEscape=false minlength="4" maxlength="50" class="required"/>
			</div>
		</div>
		<#--<div class="control-group">
			<label class="control-label" for="mark">快递类型:</label>
			<div class="controls">
				<@form.input path="mark" minlength="6" maxlength="50" class="required"/>
			</div>
		</div>-->
		<div class="control-group">
			<label class="control-label" for="companySequence">快递序号:</label>
			<div class="controls">
				<@form.input path="companySequence" maxlength="2" minlength="1" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="companyPrice">快递价格:</label>
			<div class="controls">
				<@form.input path="companyPrice" htmlEscape=false class="required"/>
			</div>
		</div>
		<#if express.companyStatus??><#assign x="${express.companyStatus!'0'}"><#else><#assign x="0"></#if> 
		<div class="control-group">
			<label class="control-label" for="email">是否显示:</label>
			<div class="controls">
				<select id="companyStatus" name="companyStatus" class="input-small">
					<option value='0' <#if x=='0'>selected='selected'</#if>>是</option>
					<option value='-1' <#if x=='-1'>selected='selected'</#if>>否</option>
				<select>
			</div>
		</div>
		<div class="form-actions">
			<@shiro.hasPermission name="sys:express:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</@shiro.hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</@form.form>
</body>
</html>
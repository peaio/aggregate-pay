<#include "/include/taglib.ftl" >
<html>
<head>
	<title>快递管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/template/">快递列表</a></li>
		<@shiro.hasPermission name="sys:express:edit"><li><a href="${ctx}/sys/express/form">快递添加</a></li></@shiro.hasPermission>
	</ul>
	<@form.form id="searchForm" action="${ctx}/sys/express/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
	</@form.form>
	<#if message??><@tags.message content=message /></#if>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>快递名称</th><th>快递代码</th><th>快递序号</th><th>快递类型</th><th>快递价格</th><th>是否开启</th><th>操作</th></tr></thead>
		<tbody>
		<#list page.list as express >
			<tr>
				<td>${express.companyName!}</td>
				<td>${express.companyMark!}</td>
				<td>${express.companySequence!}</td>
				<td>${express.companyType!}</td>
				<td>${express.companyPrice!}</td>
				<#assign x="${express.companyStatus!'0'}">
				<td><#if x=='0'>是<#else>否</#if></td>
				<@shiro.hasPermission name="sys:express:edit"><td>
    				<a href="${ctx}/sys/express/form?id=${express.id!}">修改</a>
				</td></@shiro.hasPermission>
			</tr>
		</#list>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
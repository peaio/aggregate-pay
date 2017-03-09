<#include "/include/taglib.ftl" >
<html>
<head>
	<title>字典管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    function deleteThis(uuid){
	    	 top.jBox.confirm('确认要删除该字典吗？', '提示', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/dict/delete?id="+uuid;
	            }
	            return true;
 			 });
	    }
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
                    <span>系统设置</span>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>字典管理</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">字典列表</a>
	            </li>
                <@shiro.hasPermission name="sys:role:edit">
	            <li class="">
					<a href="${ctx}/sys/dict/form">添加</a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
				
				<@form.form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<div>
						<label>类型：</label><@form.select id="type" path="type" class="select2 form-control input-small"><@form.option value="" label="--请选择--" /><@form.options items=typeList htmlEscape=false /></@form.select>
						&nbsp;&nbsp;<label>描述：</label><@form.input path="description" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page(1)"/>
					</div>
				</@form.form>
				<#if message??><@tags.message content=message /></#if>
	        	<div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
                <div class="table-scrollable">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable" >
					<thead><tr><th>键值</th><th>标签</th><th>标签(英文)</th><th>类型</th><th>描述</th><th>排序</th><@shiro.hasPermission name="sys:dict:edit"><th>操作</th></@shiro.hasPermission></tr></thead>
					<tbody>
					<#list page.list as dict >
						<tr>
							<td>${dict.value!}</td>
							<td><a href="${ctx}/sys/dict/form?id=${dict.id!}">${dict.label!}</a></td>
							<td>${dict.labelEn!}</td>
							<td><a href="javascript:" onclick="$('#type').val('${dict.type!}');$('#searchForm').submit();return false;">${dict.type!}</a></td>
							<td>${dict.description!}</td>
							<td>${dict.sort!}</td>
							<@shiro.hasPermission name="sys:dict:edit"><td>
			    				<a href="${ctx}/sys/dict/form?id=${dict.id!}">修改</a>
			    				<a onclick="deleteThis(${dict.id!})">删除</a>
			    				<a href="${ctx}/sys/dict/form?type=${dict.type!}&sort=${dict.sort!0+10}&description=${dict.description!}">添加键值</a>
							</td></@shiro.hasPermission>
						</tr>
					</#list>
					</tbody>
				</table>
                </div>
                <div class="text-center"><div class="pagination">${page}</div><div>
            </div>
        </div>
    </div>
    </div>
</body>
</html>
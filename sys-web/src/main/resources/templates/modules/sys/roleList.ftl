<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
	    function deleteThis(uuid){
	    	 top.jBox.confirm('确认要删除该角色吗？', '提示', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/role/delete?id="+uuid;
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
				    <span>系统设置<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>角色管理</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">角色列表 </a>
	            </li>
                <@shiro.hasPermission name="sys:role:edit">
	            <li class="">
					<a href="${ctx}/sys/role/form">添加</a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
            
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<@tags.message content=message! />
                <div class="table-scrollable">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th>角色名称</th>
						<th>角色标识</th>
						<th>归属机构</th>
<!-- 						<th>数据范围</th>
						<th>角色类型</th>
						<th>是否系统数据</th> -->
						<th>是否可用</th>
						<@shiro.hasPermission name="sys:role:edit"><th>操作</th></@shiro.hasPermission>
					</tr>
					<#list list as role>
						<tr>
							<td><a href="form?id=${role.id}">${role.name!}</a></td>
							<td>${role.code!}</td>
							<td>${role.officeName!}</td>
							<!-- <td>
								${fns.getDictLabel(role.dataScope, 'sys_data_scope', '无')}
							</td>
							<td>
								<#if role.roleType??>
									<#if role.roleType==1>
									普通角色
									<#elseif role.roleType==2>
									管理角色
									<#elseif role.roleType==3>
									任务分配
									</#if>
								</#if>
							</td>
							<td>
								<#if role.isSys??>
									<#if role.isSys==1>
									是
									<#elseif role.isSys==0>
									否
									</#if>
								</#if>
							</td> -->
							<td>
								<#if role.useable??>
									<#if role.useable==1>
									是
									<#elseif role.useable==0>
									否
									</#if>
								</#if>
							</td>
							<@shiro.hasPermission name="sys:role:edit">
							<td>
								<a href="${ctx}/sys/role/form?id=${role.id}">修改</a>
								<a onclick="deleteThis(${role.id!})">删除</a>
							</td>
							</@shiro.hasPermission>	
						</tr>
					</#list>
				</table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
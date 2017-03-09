<#include "/include/taglib.ftl" >
<html>
<head>
	<title>模板管理</title>
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
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">模板列表 </a>
	            </li>
	            <li class="">
					<a href="${ctx}/sys/template/form">添加</a>
	            </li>
	        </ul>
            
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<@tags.message content=message! />
                <div class="table-scrollable">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<thead><tr><th>模板说明</th><th>模板标识</th><th>模板类型</th><th>限制次数</th><th>是否开启</th><th>操作</th></tr></thead>
					<tbody>
					<#list page.list as template >
						<tr>
							<td>${template.info!}</td>
							<td>${template.mark!}</td>
							<#if (template.type)??><#assign x=template.type><#else><#assign x='sms'></#if>
							<td>
							<#if x == 'sms'>短信模板</#if>
							<#if x == 'email'>邮件模板</#if>
							</td>
							<td>${template.times!}</td>
							<td><#if template.open!>是<#else>否</#if></td>
							<td>
			    				<a href="${ctx}/sys/template/form?id=${template.id!}">修改</a>
							</td>
						</tr>
					</#list>
					</tbody>
				</table>
                </div>
                <div class="text-center"><div class="pagination">${page}</div><div>
            </div>
        </div>
    </div>
</body>
</html>
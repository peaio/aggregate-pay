<#include "/include/taglib.ftl" >
<html>
<head>
	<title></title>
	<#include "/include/treetable.ftl" >
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function deleteOrg(uid){
	    	 top.jBox.confirm('确认要删除该机构及所有子机构项吗？', '提示', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/office/delete?id="+uid;
	            }
	            return true;
 			 });
	    }
	</script>
</head>
<body>
	<div class="page-container-custom">
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
                </li>
            </ul>
        </div>
	 -->
         <div class="portlet light portlet-fit portlet-datatable ">
         	 <ul class="nav nav-tabs mb-25">
            <li class="active">
                <a data-toggle="tab" href="javascript:;">
             机构管理</a>
            </li>
            <@shiro.hasPermission name="sys:office:edit">
            <li class="">
                <a  href="${ctx}/sys/office/form">
             添加 </a>
            </li>
            </@shiro.hasPermission>
        	</ul>
			<div class="portlet-body">
		            <div id="sample_4_wrapper" class="dataTables_wrapper">
		            	<@tags.message content=message! />
		                <div class="table-scrollable">
							<table id="treeTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
								<tr>
								<th>机构名称</th>
								<th>归属区域</th>
								<th>机构编码</th>
								<th>机构类型</th>
								<th>备注</th>
								<@shiro.hasPermission name="sys:office:edit">
								<th>操作</th></@shiro.hasPermission>
								</tr>
								<#list list as item>
									<tr id="${item.id}" pId="${(item.parentId != office.id)?string(item.parentId,'0')}">
										<td><a href="${ctx}/sys/office/form?id=${item.id}">${item.name}</a></td>
										<td>
											${fns.getDictFullLabel(item.areaId,'vc.thinker.cabbage.sys.model.DicArea',' ')}
										</td>
										<td>${item.code}</td>
										<td>${fns.getDictLabel(item.type, 'sys_office_type', '无')}</td>
										<td>${item.remarks}</td>
										<@shiro.hasPermission name="sys:office:edit"><td>
											<a href="${ctx}/sys/office/form?id=${item.id}">修改</a>
											<a  onclick="deleteOrg(${item.id})">删除</a>
											<a href="${ctx}/sys/office/form?parentId=${item.id}">添加下级机构</a> 
										</td></@shiro.hasPermission>
									</tr>
								</#list>
							</table>
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>
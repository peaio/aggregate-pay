<#include "/include/taglib.ftl" >
<#include "/include/dialog.ftl" >
<html>
<head>
	<title>用户管理</title>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// 表格排序
			//tableSort({callBack : page});
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/").submit();
	    	return false;
	    }
	    function deleteUser(uid){
	    	 top.jBox.confirm('确认要删除该用户吗？', '提示', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/user/delete?id="+uid;
	            }
	            return true;
 			 });
	    }
	</script>
</head>
<body>

        <div class=" ">

        <ul class="nav nav-tabs mb-25">
            <li class="active">
              <a data-toggle="tab" href="javascript:;">
         	        用户管理
              </a>
            </li>
            <li class="">
              <a  href="${ctx}/sys/user/form">
                                         添加
			  </a>
            </li>
        </ul>





        <@form.form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/" method="post" class="form-search">
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
            <input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy!}"/>
            <div>


                <label class="ml-15">归属公司：</label>
                 <div style="display: inline-block; vertical-align: bottom;max-width: 28%;">
                <@tags.treeselect id="company" name="companyId" value=user.companyId! labelName="companyName" labelValue=user.companyName!
                    title='归属公司' url="/sys/office/treeData?type=1" cssClass="form-control" allowClear=true/>
				 </div>
				 
                <label class="ml-15">归属部门：</label>
                <div style="display: inline-block; vertical-align: bottom;max-width: 28%;">
                <@tags.treeselect id="office" name="officeId" value=user.officeId! labelName="officeName" labelValue=user.officeName!
                    title='归属部门' url="/sys/office/treeData?type=2" cssClass="form-control" allowClear=true/>
				 </div>
				<br/><br/>
                <label class="ml-15">登录名：</label><@form.input path="loginName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
                <label class="ml-15">姓名：</label><@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
                &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page(1);"/>
            </div>
        </@form.form>
        <div class="portlet-body">
            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<@tags.message content=message! />
                <div class="table-scrollable">

                    <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable" role="grid">
                        <thead><tr><th>归属公司</th><th>归属部门</th><th class="sort loginName">登录名</th><th class="sort name">姓名</th><th>电话</th><th>手机</th><th>用户角色</th><@shiro.hasPermission name="sys:user:edit"><th>操作</th></@shiro.hasPermission></tr></thead>
                        <tbody>
                        <#list page.list as user >
                            <tr>
                                <td>${(user.company.name)!}</td>
                                <td>${(user.office.name)!}</td>
                                <td><a href="${ctx}/sys/user/form?id=${user.uid!}">${user.loginName!}</a></td>
                                <td>${user.name!}</td>
                                <td>${user.phone!}</td>
                                <td>${user.mobile!}</td>
                                <td>${user.roleNames!}</td>
                                <@shiro.hasPermission name="sys:user:edit"><td>
                                    <a href="${ctx}/sys/user/form?id=${user.uid!}">修改</a>
                                    <a   onclick="deleteUser(${user.uid!})">删除</a>
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
</body>
</html>
<#include "/include/taglib.ftl" >
<html>
<head>
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
	<div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>登陆日志</span>
                </li>
            </ul>
        </div>

        <div class="portlet light portlet-fit portlet-datatable ">
        <@form.form id="searchForm" action="${ctx}/sys/log/loginList" method="post" class="breadcrumb form-search">
        	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
			<div>
				<label class="ml-15">用户姓名：</label>
				<input id="userName" name="userName" type="text" maxlength="50" class="form-control input-small input-inline" value="${userName!}"/>
				<label>工号：</label>
				<input id="no" name="no" type="text" maxlength="50" class="form-control input-small input-inline" value="${no!}"/>
				&nbsp;
				<label>查询日期：</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
					value="${beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				- <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
					value="${endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page(1)"/>
			</div>
		</@form.form>
        
        <#if message??><@tags.message content=message /></#if>
          <div class="portlet-body">
            <div id="sample_4_wrapper" class="dataTables_wrapper">
            <#if page.list?size &gt; 0 >
                <div class="table-scrollable">
			        <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable" role="grid">
			            <thead>
							<tr>
								<th>用户姓名</th>
								<th>工号</th>
								<th>登陆IP</th>
								<th>登陆IP所在地区</th>
								<th>登陆时间</th>
						</thead>
						<tbody>
						<#list page.list as log >
							<tr>
								<td>${log.userName!}</td>
								<td>${log.no!}</td>
								<td>${log.loginIp!}</td>
								<td>${log.loginArea!}</td>
								<td>${log.loginTime?string("yyyy-MM-dd HH:mm:ss")}</td>
							</tr>
						</#list>
						</tbody>
			        </table>
			    	</div>
	                <div class="text-center"><div class="pagination">${page}</div><div>
	               <#else>
						<div class="note note-warning alert">暂无数据！</div>
					</#if>
	            </div>
	        </div>
	    </div>
</body>
</html>
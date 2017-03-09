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
                    <span>日志</span>
                </li>
            </ul>
        </div>
        <div class="portlet light portlet-fit portlet-datatable ">
	<@form.form id="searchForm" action="${ctx}/sys/log/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
		<div>
			<label>首页：</label><input id="createById" name="createById" type="text" maxlength="50" class="form-control input-small input-inline" value="${createById!}"/>
			<label>URI：</label><input id="requestUri" name="requestUri" type="text" maxlength="50" class="form-control input-small input-inline" value="${requestUri!}"/>
			&nbsp;
			<label>查询日期：</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
				value="${beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			- <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
				value="${endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			<label for="exception"><input id="exception" name="exception" type="checkbox" <#if exception?? && exception == '1'>checked</#if> value="1"/>异常信息</label>
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
							<th>所在公司</th>
							<th>所在部门</th>
							<th>操作用户</th>
							<th>URI</th>
							<th>提交方式</th>
							<th>操作者IP</th>
							<th>创建时间</th>
							</thead>
						<tbody>
						</tr>
						<#list page.list as log >
							<tr>
								<td>${log.companyName!}</td>
								<td>${log.officeName!}</td>
								<td>${log.createByName!}</td>
								<td><strong>${log.requestUri!}</strong></td>
								<td>${log.method!}</td>
								<td>${log.remoteAddr!}</td>
								<td>${log.createDate?string("yyyy-MM-dd HH:mm:ss")}</td>
							</tr>
							<tr>
								<td colspan="7">用户代理: ${log.userAgent!}<br/>
								提交参数: <#escape x as x?html>${log.params!}</#escape>
								<#if log.exception??><br/>异常信息: <br/>
								${log.exception!}</#if></td>
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
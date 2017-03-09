<#include "/include/taglib.ftl" >
<html>
<head>
	<title>用户管理</title>
	<#include "/include/treeview.ftl" >
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginName").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?id="+$('#uid').val()}
				},
				messages: {
					loginName: {remote: "用户名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
					for(var i=0; i<nodes2.length; i++) {
						ids2.push(nodes2[i].id);
					}
					$("#officeIds").val(ids2);
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
			
			var setting = {
				check:{enable:true,nocheckInherit:true},
				view:{selectedMulti:false},
				data:{
					simpleData:{enable:true}
				},
				callback:{
					beforeClick:function(id, node){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}
				}
			};
			
			// 用户-机构
			var zNodes2=[
				<#list officeList as office >
					{id:${office.id!}, pId:${office.parentId!0}, name:'${office.name!}'},
            	</#list>];
			// 初始化树结构
			var tree2 = $.fn.zTree.init($("#officeTree"), setting, zNodes2);
			// 不选择父节点
			tree2.setting.check.chkboxType = { "Y" : "s", "N" : "s" };
			// 默认选择节点
			var ids2 = "${user.officeIds}".split(",");
			for(var i=0; i<ids2.length; i++) {
				var node = tree2.getNodeByParam("id", ids2[i]);
				try{tree2.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开全部节点
			tree2.expandAll(true);
			
			// 刷新（显示/隐藏）机构
			refreshOfficeTree();
			$("#dataScope").change(function(){
				refreshOfficeTree();
			});
		});
		
		function refreshOfficeTree(){
			// 按明细设置
			if($("#dataScope").val()==9){ 
				$("#officeTreeDiv").show();
			}else{
				$("#officeTreeDiv").hide();
			}
		}
	</script>
	<style type="text/css">
		#roleIdList span, .roles span{
			width:200px;
			float:left;
		}
	</style>
</head>
<body>

	<div>
       

	<#if message??><@tags.message content=message /></#if>
	<div class="portlet light ">
			<ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/user/list">
	                                          用户管理
	                </a>
	            </li>
	            <li class="active">
	                <a  href="javascript:;">
	                 <@shiro.hasPermission name="sys:user:edit">
                    <#if user.uid??>
                                                     修改<#else>
                                                     添加</#if>
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="sys:user:edit">
                                                     查看
                    </@shiro.lacksPermission>
	            	 </a>
	            </li>
        	</ul>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
	<@form.form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<@form.hidden path="uid"/>
		<div class="form-body">
		  
		<div class="form-group">
			<label class="col-xs-3 control-label">归属公司:</label>
			<div class="col-xs-4">
                <@tags.treeselect id="company" name="companyId" value=(user.company.id)! labelName="companyName" labelValue=(user.company.name)!
					title='归属公司' url="/sys/office/treeData?type=1" cssClass="form-control required" allowClear=false />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">归属部门:</label>
			<div class="col-xs-4">
                <@tags.treeselect id="office" name="officeId" value=(user.office.id)! labelName="officeName" labelValue=(user.office.name)!
					title='归属部门' url="/sys/office/treeData?type=2" cssClass="form-control required" allowClear=false />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">登录名:</label>
			<div class="col-xs-4">
				<input id="loginName" name="loginName" minlength="6" maxlength="50" type="text" value="${(user.userAccount.loginName)!}" class="form-control required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">姓名:</label>
			<div class="col-xs-4">
				<@form.input path="name" htmlEscape=false maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">工号:</label>
			<div class="col-xs-4">
				<@form.input path="no" htmlEscape=false maxlength="50" class="form-control required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">密码:</label>
			<div class="col-xs-4">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="form-control ${user.uid!'required'}"/>
				<#if user.uid??><span class="help-inline">若不修改密码，请留空。</span></#if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">确认密码:</label>
			<div class="col-xs-4">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">身份证:</label>
			<div class="col-xs-4">
				<@form.input path="idCard" htmlEscape=false maxlength="18" class="required" class="form-control card"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">邮箱:</label>
			<div class="col-xs-4">
				<@form.input path="email" htmlEscape=false maxlength="100" class="form-control email"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">电话:</label>
			<div class="col-xs-4">
				<@form.input path="phone" htmlEscape=false maxlength="100" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">手机:</label>
			<div class="col-xs-4">
				<@form.input path="mobile" htmlEscape=false maxlength="100" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label">备注:</label>
			<div class="col-md-6 col-xs-8">
				<@form.textarea path="remarks" htmlEscape=false rows="3" maxlength="200" class="form-control input-xlarge"/>
			</div>
		</div>
		<div class="form-group">
			<label class=" col-xs-3 control-label">用户角色:</label>
			<div class="col-md-4 col-xs-6">
				<p class="form-control-static user-role-list"><@form.checkboxes path="roleIdList" items=allRoles itemLabel="name" itemValue="id" htmlEscape=false class="required"/></p>
			</div>
		</div>
		<div class="form-group" id="officeTreeDiv">
			<label class=" col-xs-3 control-label"></label>
			<div class="col-md-4 col-xs-6">
				<div id="officeTree" class="form-control ztree" style="margin-top:3px;float:left;"></div>
				<@form.hidden path="officeIds"/>
			</div>
		</div>
		<#if user.uid??>
			<div class="form-group">
				<label class="col-xs-3 control-label">创建时间:</label>
				<div class="col-xs-4">
					<label class="lbl"><#if user.createTime??>${user.createTime?string("yyyy-MM-dd HH:mm:ss")}</#if></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-3 control-label">最后登陆:</label>
				<div class="col-xs-4">
					<label class="lbl">IP: ${(user.userAccount.lastLoginIp)!}&nbsp;&nbsp;&nbsp;&nbsp;时间：<#if (user.userAccount.lastLoginTime)??>${user.userAccount.lastLoginTime?string("yyyy-MM-dd HH:mm:ss")}</#if></label>
				</div>
			</div>
		</#if>
		<div class="form-actions">
			<div class="row">
                <div class="col-md-offset-3 col-md-4">
				<@shiro.hasPermission name="sys:user:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				</@shiro.hasPermission>
				</div>
            </div>
		</div>
	</@form.form>
	</div>
   </div>



	</div>
</body>
</html>
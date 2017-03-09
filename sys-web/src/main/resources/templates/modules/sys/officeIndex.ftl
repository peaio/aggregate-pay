<#include "/include/taglib.ftl" >
<#include "/include/dialog.ftl" >
<html>
<head>
	<title>用户管理</title>
	<#include "/include/treeview.ftl" >
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
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
                    <span>用户管理</span>
                </li>
            </ul>
        </div>

	<div id="content" class="clearfix bg-white p-15">
		<div id="left" class="tree-bar">
            <div class="portlet blue-hoki box">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cogs"></i> 组织机构
                    </div>
                    <div class="actions">
                        <a href="javascript:;" class="btn btn-default btn-sm">
                        <i class="icon-refresh pull-right" onclick="refreshTree();"></i> </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div id="ztree" class="ztree tree-body-nav"></div>
                </div>
            </div>
		</div>
		<div id="openClose" class="tree-close">&nbsp;</div>
		<div id="right" class="tree-body-main">
			<iframe id="officeContent" src="${ctx}/sys/office/list" width="100%" height="91%" frameborder="0"></iframe>
		</div>
	</div>
       
    </div>
    
    <script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id == '0' ? '' :treeNode.id;
					$('#officeContent').attr("src","${ctx}/sys/office/list?officeId="+id);
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ctx}/sys/office/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();

		function reSizeIframe() {
		    var wHeight = $(window).height();
		    $("#officeContent").height(wHeight - 115);
		    $("#ztree").height(wHeight - 180);
		}

		$(window).resize(function() {
		    reSizeIframe();
		});
		reSizeIframe();
		 


	</script>
</body>
</html>
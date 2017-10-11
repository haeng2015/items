<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>资源文件列表管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resource/resourceList.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>资源文件列表管理</h2>
				<ul>
					<li class="cur"><a href="${pageContext.request.contextPath}/resource/toResourceList.do">资源列表</a> </li>
					<li><a href="${pageContext.request.contextPath}/resource/toResourceEdit.do">资源编辑</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<div class="classify_box">
						<div class="tb">
							文件类型：<select id="type" name="type"><option value="">全部</option><option value="1">H5</option><option value="2">视频</option></select>
							文件名称：<input id="fileName" name="fileName" class="easyui-textbox" /><br>
							上传时间：<input class="easyui-datebox" id="searchBeginTime" name="searchBeginTime" data-options="editable:false">
							到&nbsp;&nbsp;<input class="easyui-datebox" id="searchEndTime" name="searchEndTime" data-options="editable:false">&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="search_resource()" class="easyui-linkbutton"><i class="icon">&#xe917;</i>&nbsp;查询</a>
						</div>
						<div class="tb">
							<input type="hidden" id="userDeviceIdStrs" name="userDeviceIdStrs" value="${requestScope.userDeviceIdStrs }">
							<a href="javascript:void(0);" onclick="resourceInfoList(0)" class="easyui-linkbutton"><i class="icon">&#xe82b;</i>&nbsp;添加资源</a>
							<a href="javascript:void(0);" onclick="resourceInfoList(1)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;修改资源</a>
							<a href="javascript:void(0);" onclick="resourceInfoList(2)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;删除资源</a>
							
							<a href="javascript:void(0);" onclick="resourceInfoList(3)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;下载资源</a>
							<c:if test="${empty requestScope.userDeviceIdStrs }">
								<a href="javascript:void(0);" onclick="resourceInfoList(4)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;推送资源</a>
							</c:if>
							<c:if test="${!empty requestScope.userDeviceIdStrs }">
								<a href="javascript:void(0);" onclick="pushResourceToDevice()" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;确定推送</a>
							</c:if>
						</div>
					</div>
					<div class="easyui-layout" style="width:811px;height:750px;">
						<div data-options="region:'center',split:false,border:false" style="height: 700px;">
							<table id="resourceListEasyUI"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>

<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>用户设备管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/device/deviceList.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>用户设备管理</h2>
				<ul>
					<li class="cur"><a href="${pageContext.request.contextPath}/device/toDeviceList.do">用户列表</a> </li>
					<li><a href="${pageContext.request.contextPath}/device/toDeviceEdit.do">用户编辑</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<div class="classify_box">
						<div class="tb">
							设备名称： <input id="deviceName" name="deviceName" class="easyui-textbox" />
							设备ID：<input id="deviceId" name="deviceId" class="easyui-textbox" />
							用户名称：<input id="userName" name="userName" class="easyui-textbox" />
							<a href="javascript:void(0);" onclick="search_device()" class="easyui-linkbutton"><i class="icon">&#xe917;</i>&nbsp;查询</a>
						</div>
						<input type="hidden" id="resourceId" name="resourceId" value="${requestScope.resourceId }">
						<div class="tb">
							<a href="javascript:void(0);" onclick="deviceInfoList(0)" class="easyui-linkbutton"><i class="icon">&#xe82b;</i>&nbsp;添加设备</a>
							<a href="javascript:void(0);" onclick="deviceInfoList(1)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;修改设备</a>
							<a href="javascript:void(0);" onclick="deviceInfoList(2)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;删除设备</a>
							<c:if test="${!empty requestScope.resourceId }">
								<a href="javascript:void(0);" onclick="pushResourceToDevice()" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;推送资源</a>
							</c:if>
						</div>
					</div>
					<div class="easyui-layout" style="width:811px;height:750px;">
						<div data-options="region:'center',split:false,border:false" style="height: 700px;">
							<table id="deviceListEasyUI"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>

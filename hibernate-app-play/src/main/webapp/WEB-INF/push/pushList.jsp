<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>资源推送管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/push/pushList.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>资源推送管理</h2>
				<ul>
					<li class="cur"><a href="${pageContext.request.contextPath}/device/toDeviceList.do?mark=1">推送列表</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<div class="classify_box">
						<div class="tb">
							设备名称： <input id="deviceName" name="deviceName" class="easyui-textbox" />
							设备ID：<input id="deviceId" name="deviceId" class="easyui-textbox" />
							用户名称：<select id="userName" name="userName">
								<option value="">全部</option>
								<c:forEach items="${requestScope.userDeviceVOs }" var="userDeviceVO">
									<option value="${userDeviceVO.userDeviceId }">${userDeviceVO.userName }</option>								
								</c:forEach>
							</select>
							<!-- <input id="userName" name="userName" class="easyui-textbox" /> -->
							<a href="javascript:void(0);" onclick="search_push()" class="easyui-linkbutton"><i class="icon">&#xe917;</i>&nbsp;查询</a>
						</div>
<%-- 
<a href="${pageContext.request.contextPath}/device/downloadResourceByDeviceId.do?deviceId=345reg"><font color="blue">下载设备资源</font></a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="downloadResource()"><font color="red">ajax下载设备资源</font></a>
 --%>
						<div class="tb">
							<a href="javascript:void(0);" onclick="pushInfoList(1)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;修改推送</a>
							<a href="javascript:void(0);" onclick="pushInfoList(2)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;删除推送</a>
						</div>
					</div>
					<div class="easyui-layout" style="width:811px;height:750px;">
						<div data-options="region:'center',split:false,border:false" style="height: 700px;">
							<table id="pushListEasyUI"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>

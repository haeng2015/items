<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>APK列表管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/apk/apkList.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>APK列表管理</h2>
				<ul>
					<li class="cur"><a href="${pageContext.request.contextPath}/apk/toApkList.do">APK列表</a> </li>
					<li><a href="${pageContext.request.contextPath}/apk/toApkEdit.do">APK编辑</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<div class="classify_box">
						<div class="tb">
							版本号： <input id="versionCode" name="versionCode" class="easyui-textbox" />
							版本名称：<input id="fileName" name="fileName" class="easyui-textbox" /><br>
							发布时间：<input class="easyui-datebox" id="searchBeginTime" name="searchBeginTime" data-options="editable:false">
							到&nbsp;&nbsp;<input class="easyui-datebox" id="searchEndTime" name="searchEndTime" data-options="editable:false">&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0);" onclick="search_apk()" class="easyui-linkbutton"><i class="icon">&#xe917;</i>&nbsp;查询</a>
						</div>
						<div class="tb">
							<a href="javascript:void(0);" onclick="apkInfoList(0)" class="easyui-linkbutton"><i class="icon">&#xe82b;</i>&nbsp;添加APK</a>
							<a href="javascript:void(0);" onclick="apkInfoList(1)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;修改APK</a>
							<a href="javascript:void(0);" onclick="apkInfoList(2)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;删除APK</a>
							<a href="javascript:void(0);" onclick="apkInfoList(3)" class="easyui-linkbutton"><i class="icon">&#xe834;</i>&nbsp;下载APK</a>
						</div>
					</div>
					<div class="easyui-layout" style="width:811px;height:750px;">
						<div data-options="region:'center',split:false,border:false" style="height: 700px;">
							<table id="apkListEasyUI"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>

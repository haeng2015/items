<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
</head>
<body>
	<div id="center">
		<div id="main">
				<a href="javascript:void(0)" onclick="addAuthForAdmin()">为管理员刷新权限</a>
			<div id="table">
				<img src="${pageContext.request.contextPath}/images/0047.gif" />
				<!-- <div id="nowDateTime" class="easyui-dateTimebox" data-options="editable:true"></div> -->
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>无权限页面</title>
<meta charset="utf-8" />
<link href="${pageContext.request.contextPath}/css/error/error.min.css" rel="stylesheet" />
</head>
<body>
	<div class="no_div">
		<div class="back_btn">
			<a href="javascript:history.back(-1);">我要返回</a> <a href="login.do">转到首页</a>
		</div>
	</div>
</body>
</html>

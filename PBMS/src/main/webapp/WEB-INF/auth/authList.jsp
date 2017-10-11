<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link type="text/css" href="${pageContext.request.contextPath}/css/authList.min.css"
	rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div class="White_box">
			<div class="power_box">
				<div class="power_div">
					<ul id="roleAuthList_tree" class="easyui-tree"
						data-options="url:'${pageContext.request.contextPath}/user/loadAuthTree.do',method:'post',animate:true,checkbox:false"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
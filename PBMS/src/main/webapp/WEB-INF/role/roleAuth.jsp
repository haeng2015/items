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
<script src="${pageContext.request.contextPath}/js/user/roleAuthList.js" type="text/javascript"></script>
</head>
<body>
	<div id="main">
		<div class="White_box">
			<div class="power_box">
				<div class="power_div">
					<input id="roleId" name="roleId" type="hidden" value="${requestScope.roleId }" />
					<ul id="roleAuthList_tree" class="easyui-tree"
						data-options="url:'${pageContext.request.contextPath}/user/loadAuthTree.do',method:'post',animate:true,checkbox:true"></ul>
					<!--按钮-->
				</div>
				<input type="button" onclick="saveRoleAuth();" value="保存权限" class="green" /> <input
					type="button" onclick="deleteRoleAuth()" value="清空权限" class="red" /> <input type="button"
					onclick="resetRoleAuth()" value="重置权限" class="blue" />
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/roleList.js"></script>
</head>
<body>
	<div id="main">
		<form name="roleList_form" method="post">
			<div id="index">
				角色名:<input type="text" name="roleName" id="roleName" /> <input type="button" value="查询" />
			</div>
			<input id="addRole" type="button" value="添加角色" onclick="window.location='roleAdd.html'"
				class="button" onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
			<input id="deleteRole" type="button" value="删除角色" class="button"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
			<input id="addAuth" type="button" value="权限分配" class="button"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
		</form>
		<div class="easyui-layout" style="height:390px;">
			<!-- <div data-options="region:'center',split:false,border:false" style="height: 700px;"> -->
			<table class="easyui-datagrid" id="roleList_table"></table>
			<div id="roleList_pager" class="tb"></div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
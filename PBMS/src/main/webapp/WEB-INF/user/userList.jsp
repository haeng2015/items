<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/userList.js"></script>
</head>
<body>
	<div id="main">
		<form id="userList_form" method="post">
			<div id="index">
				<span>用户名：</span> <input name="userName" type="text" contextmenu="请输入用户名" id="userName" /> <input name="2"
					type="button" value="搜索" onclick="userList_search()" />
				<div>
					<input id="addUser_button" type="button" value="添加用户" class="button"
						onmouseout="this.className='button';" onmouseover="this.className='buttondown';"
						 /> <input  onclick="editUserInfo(1)" type="button"
						value="详细信息" class="button" onmouseout="this.className='button';"
						onmouseover="this.className='buttondown';" /> <input id="deleteUser_button"
						type="button" value="删除用户" class="button" onmouseout="this.className='button';"
						onmouseover="this.className='buttondown';" /> <input onclick="editUserInfo(2)"
						type="button" value="修改用户" class="button" onmouseout="this.className='button';"
						onmouseover="this.className='buttondown';" /><input onclick="editUserRole()"
						type="button" value="角色编辑" class="button" onmouseout="this.className='button';"
						onmouseover="this.className='buttondown';" />
				</div>
		</form>
		<form name="userList_form" method="post">
			<div class="easyui-layout" style="height:390px;">
				<!-- <div data-options="region:'center',split:false,border:false" style="height: 700px;"> -->
				<table class="easyui-datagrid" id="userList_table"></table>
				<div id="userList_pager" class="tb"></div>
			</div>
		</form>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
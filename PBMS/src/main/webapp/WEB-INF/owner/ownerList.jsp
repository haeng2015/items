<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/owner/ownerList.js"></script>
</head>
<body>
	<div id="main" class="index">
		<div>
				业主姓名:<input id="ownerName" name="ownerName" type="text" />
				身份证:<input id="ownerCard" name="ownerCard" type="text" /><input type="button"
				name="Submit" value="查询" onclick="ownerList_search()" />
		</div>
		<div>
			<input id="selectRoom" type="button" value="查看详细" class="button" onclick="ownerList_button(2)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
				id="updateRoom" type="button" value="修改业主" class="button" onclick="ownerList_button(1)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
				id="deleteOwner" type="button" value="删除业主" class="button" onmouseout="this.className='button';"
				onmouseover="this.className='buttondown';" />
		</div>
		<div class="easyui-layout" style="height:390px;">
			<!-- <div data-options="region:'center',split:false,border:false" style="height: 700px;"> -->
				<table class="easyui-datagrid" id="ownerList_table"></table>
				<div id="ownerList_table_TypedatagridToolbar" class="tb"></div>
			</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
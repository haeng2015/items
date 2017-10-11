<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/room/roomList.js"></script>
</head>
<body>
	<div id="main" class="index">
		<div>
			<span class="style3">查询条件：</span> <select name="getOneway" id="getOneway">
				<option value="0">--请选择--</option>
				<option value="1">楼栋名</option>
				<option value="2">房间名</option>
				<option value="3">房型</option>
				<option value="4">用途</option>
			</select> <input type="text" name="selectRoomSearch" id="selectRoomSearch" /> 
				入住时间:<input id="roomStartDate" name="roomStartDate" class="easyui-datebox"
				data-options="editable:true" />到期时间:<input id="roomEndDate" name="roomEndDate" class="easyui-datebox"
				data-options="editable:true" /><input type="button"
				name="Submit" value="查询" onclick="roomList_search()" />
		</div>
		<div>
			<input id="selectRoom" type="button" value="查看详细" class="button" onclick="roomList_button(2)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
				id="updateRoom" type="button" value="修改房间" class="button" onclick="roomList_button(1)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
				id="deleteRoom" type="submit" value="删除删除" class="button" onmouseout="this.className='button';"
				onmouseover="this.className='buttondown';" />
		</div>
		<div class="easyui-layout" style="height:390px;">
			<!-- <div data-options="region:'center',split:false,border:false" style="height: 700px;"> -->
				<table class="easyui-datagrid" id="roomList_table"></table>
				<div id="roomList_table_TypedatagridToolbar" class="tb"></div>
			</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
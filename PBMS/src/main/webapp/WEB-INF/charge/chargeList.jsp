<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/charge/chargeList.js"></script>
</head>
<body>
	<div id="main" class="index">
		<div>
			项目名称:<input id="chargeName" name="chargeName" type="text" /> <input type="button" name="Submit"
				value="查询" onclick="chargeList_search()" />
		</div>
		<div>
			<input id="updateRoom" type="button" value="修改项目" class="button" onclick="chargeList_button(1)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
			type="button" value="删除项目" class="button" onmouseout="this.className='button';"
				onmouseover="this.className='buttondown';" onclick="chargeList_button(2)" />
		</div>
		<div class="easyui-layout" style="height:390px;">
			<!-- <div data-options="region:'center',split:false,border:false" style="height: 700px;"> -->
			<table class="easyui-datagrid" id="chargeList_table"></table>
			<div id="chargeList_table_TypedatagridToolbar" class="tb"></div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
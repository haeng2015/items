<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/build/buildList.js"></script>
</head>
<body>
	<div id="main" class="index">
		<div>
			<span class="style3">查询条件：</span> <select name="getOneway" id="getOneway"
				onchange="changeInputAttr()">
				<option value="0">--请选择--</option>
				<option value="1">楼栋名</option>
				<!-- <option value="2">开工时间</option>
				<option value="3">竣工时间</option> -->
				<option value="4">楼栋信息</option>
				<option value="5">建筑面积</option>
			</select> <input type="text" name="selectBuildSearch" id="selectBuildSearch" /> 
				开工时间:<input id="buildStartDate" name="buildStartDate" class="easyui-datebox"
				data-options="editable:false" />竣工时间:<input id="buildEndDate" name="buildEndDate" class="easyui-datebox"
				data-options="editable:false" /><input type="button"
				name="Submit" value="查询" onclick="buildList_search()" />
		</div>
		<div>
			<input id="selectBuild" type="button" value="查看详细" class="button" onclick="buildList_button(1)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
				id="updateBuild" type="button" value="修改楼栋" class="button" onclick="buildList_button(2)"
				onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
				id="deleteBuild" type="submit" value="删除楼栋" class="button" onmouseout="this.className='button';"
				onmouseover="this.className='buttondown';" />
		</div>
		<div class="easyui-layout" style="height:390px;">
			<!-- <div data-options="region:'center',split:false,border:false" style="height: 700px;"> -->
				<table class="easyui-datagrid" id="buildList_table"></table>
				<div id="buildList_table_TypedatagridToolbar" class="tb"></div>
			</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
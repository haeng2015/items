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
		<form id="buildDetailed_form" method="post">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>楼栋详细</caption>
					<thead>
					</thead>
					<tbody>
						<input id="buildId" name="buildId" type="hidden" value="${requestScope.buildingVO.buildId }" />
						<tr>
							<td>楼栋名</td>
							<td><span>${requestScope.buildingVO.buildName }</span></td>
						</tr>
						<tr>
							<td>开工时间</td>
							<td><span>${requestScope.buildingVO.buildStartDate }</span></td>
						</tr>
						<tr>
							<td>竣工时间</td>
							<td><span>${requestScope.buildingVO.buildEndDate }</span></td>
						</tr>
						<tr>
							<td>建筑面积(m*m)</td>
							<td><span>${requestScope.buildingVO.buildArea }</span></td>
						</tr>
						<tr>
							<td>楼栋信息</td>
							<td><span>${requestScope.buildingVO.buildInfo }</span></td>
						</tr>
						<tr>
							<td>楼栋资料</td>
							<td colspan="3"><span> <c:if test="${empty requestScope.boAccessoryBuilds }">
										<font color="red">暂无楼栋资料</font>
									</c:if> <c:if test="${!empty requestScope.boAccessoryBuilds }">
										<c:forEach items="${requestScope.boAccessoryBuilds }" var="materials">
											<a href="javascript:void();" id="${materials.id }" title="点击下载"
												onclick="uploadMaterials(this.id);"> <font color="blue">${materials.name }</font>
											</a>
											<br>
										</c:forEach>
									</c:if>
							</span></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<center>
									<input onclick="updateBuild_submit()" type="button" value="修改" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
										onclick="javascript:history.back(-1);" type="button" value="返回" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
								</center>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
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
		<form id="buildDetailed_form" method="post">
			<div id="table">
				<table>
					<caption>房间详细</caption>
					<tbody>
						<input id="roomId" name="roomId" type="hidden" value="${requestScope.roomVO.roomId }" />
						<tr>
							<td>楼栋名</td>
							<td><span>${requestScope.roomVO.buildName }</span></td>
						</tr>
						<tr>
							<td>房间名</td>
							<td><span>${requestScope.roomVO.roomName }</span></td>
						</tr>
						<tr>
							<td>所属者</td>
							<td><span>${requestScope.roomVO.ownerName }</span></td>
						</tr>
						<tr>
							<td>入住时间</td>
							<td><span>${requestScope.roomVO.roomStartDate }</span></td>
						</tr>
						<tr>
							<td>到期时间</td>
							<td><span>${requestScope.roomVO.roomEndDate }</span></td>
						</tr>
						<tr>
							<td>房型</td>
							<td><span>${requestScope.roomVO.roomType }</span></td>
						</tr>
						<tr>
							<td>用途</td>
							<td><span>${requestScope.roomVO.roomUse }</span></td>
						</tr>
						<tr>
							<td>房间面积</td>
							<td><span>${requestScope.roomVO.roomArea }</span></td>
						</tr>
						<tr>
							<td>得房率</td>
							<td><span>${requestScope.roomVO.roomProb }</span></td>
						</tr>
						<tr>
							<td>房间附件</td>
							<td><div id="dvReadAttach">
									<c:if test="${empty requestScope.boAccessoryRooms }">
										<font color="red">暂无房间资料</font>
									</c:if>
									<c:if test="${!empty requestScope.boAccessoryRooms }">
										<c:forEach items="${requestScope.boAccessoryRooms }" var="materials">
											<a href="javascript:void();" id="${materials.id }" title="点击下载"
												onclick="uploadMaterials(this.id);"> <font color="blue">${materials.name }</font></a>
											<br>
										</c:forEach>
									</c:if>
								</div></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<center>
									<input onclick="updateRoom_submit()" type="button" value="修改" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
										onclick="javascript:history.back(-1)" type="button" value="返回" class="button"
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
<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/owner/editOwner.js"></script>
</head>
<body>
	<div id="main" class="index">
		<div id="table">
			<table>
				<caption>业主详细</caption>
				<tbody>
					<tr>
						<th><p>业主姓名</p></th>
						<td><span>${requestScope.ownerVO.ownerName }</span></td>
					</tr>
					<tr>
						<th><p>性别</p></th>
						<td><span> <c:if test="${requestScope.ownerVO.ownerSex == '1' }">男</c:if> <c:if
									test="${requestScope.ownerVO.ownerSex == '2' }">女</c:if>
						</span></td>
					</tr>
					<tr>
						<th><p>居住房间</p></th>
						<td><span>${requestScope.ownerVO.buildName },${requestScope.ownerVO.roomName }</span></td>
					</tr>
					<tr>
						<th><p>籍贯</p></th>
						<td><span>${requestScope.ownerVO.ownerAddr }</span></td>
					</tr>
					<tr>
						<th><p>联系电话</p></th>
						<td><span>${requestScope.ownerVO.ownerPhone }</span></td>
					</tr>
					<tr>
						<th><p>身份证</p></th>
						<td><span>${requestScope.ownerVO.ownerCard }</span></td>
					</tr>
					<tr>
						<th><p>工作单位</p></th>
						<td><span>${requestScope.ownerVO.ownerWork }</span></td>
					</tr>
					<tr>
						<th><p>业主资料</p></th>
						<td colspan="3"><span> <c:if test="${empty requestScope.boAccessoryOwner }">
									<font color="red">暂无业主资料</font>
								</c:if> <c:if test="${!empty requestScope.boAccessoryOwner }">
									<c:forEach items="${requestScope.boAccessoryOwner }" var="materials">
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
							<td colspan="2">
								<center>
									<input onclick="javascript:history.back(-1);" type="button" value="返回" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
								</center>
							</td>
						</tr>
					</tfoot>
			</table>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
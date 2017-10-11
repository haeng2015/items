<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/userRole.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="userRole_form" method="post">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>用户角色管理</caption>
					<tbody>
						<input id="userId" name="userId" type="hidden" value="${requestScope.userVO.userId }" />
						<input id="userRoleId" name="userRoleId" type="hidden" value="${requestScope.userVO.userRoleId }" />
						<!-- <tr><td><input type="radio" id="0" name="userRole" value="0" >无角色</td></tr> -->
						<c:forEach items="${requestScope.roleList }" var="roles">
							<c:if test="${roles.roleId != 1 }">
								<tr>
									<td><input type="radio" id="${roles.roleId }" name="userRole" value="${roles.roleId }"
										<c:if test="${requestScope.userVO.roleId == roles.roleId }">checked="checked"</c:if>>${roles.roleName }</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input onclick="saveUserRole()" type="button" value="保存" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /></li>
								</ul>
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
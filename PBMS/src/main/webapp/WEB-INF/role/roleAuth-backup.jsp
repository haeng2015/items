<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/roleAuth.js"></script>
</head>
<body>
	<div id="main">
		<form id="userRole_form" method="post">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>角色权限管理</caption>
					<tbody>
						<input id="roleId" name="roleId" type="hidden" value="${requestScope.roleId }" />
						
						<!-- 选中该角色的权限 -->
						<c:forEach items="${requestScope.roleAuths }" var="roleAuth">
							<tr>
								<td><input type="checkbox" id="${roleAuth.auth.authId }" name="roleAuth" checked="checked"
									value="${roleAuth.auth.authId }">${roleAuth.auth.authName }</td>
							</tr>
						</c:forEach>
						
						<!-- 未被选中的权限 -->
						<c:forEach items="${requestScope.auths }" var="auth">
							<tr>
								<td><input type="checkbox" id="${auth.authId }" name="roleAuth"
									value="${auth.authId }">${auth.authName }</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input onclick="saveRoleAuth()" type="button" value="保存" class="button"
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
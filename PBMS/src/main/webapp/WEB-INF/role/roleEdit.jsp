<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/roleEdit.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="roleEditForm" method="post">
			<div id="index"></div>
			<div id="table">
				<table id="roleEditTable">
					<caption>角色编辑</caption>
					<thead>
					</thead>
					<tbody>
					<input type="hidden" id="roleId" name="roleId" value="${requestScope.roleVO.roleId }">
						<tr>
							<td>角色名</td>
							<td><input name="roleName" id="roleName" type="text" value="${requestScope.roleVO.roleName }" />
							<p class="error_word"></p></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input type="button" value="保存" class="button" id="addRoleButton"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /></li>
									<li><input name="Roleselect" type="reset" value="重置" class="button"
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
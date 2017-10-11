<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/userList.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="buildDetailed_form" method="post">
			<div id="table">
				<table>
					<caption>用户详细</caption>
					<thead>
					</thead>
					<tbody>
						<input id="userId" name="userId" type="hidden" value="${requestScope.userVO.userId }" />
						<tr>
							<td>登录名</td>
							<td><span>${requestScope.userVO.userLogin }</span></td>
						</tr>
						<tr>
							<td>用户名</td>
							<td><span>${requestScope.userVO.userName }</span></td>
						</tr>
						<tr>
							<td>用户性别</td>
							<td><span><c:if test="${requestScope.userVO.userSex == '1'}">男</c:if> <c:if
										test="${requestScope.userVO.userSex == '2'}">女</c:if></span></td>
						</tr>
						<tr>
							<td>用户类型</td>
							<td><span><c:if test="${requestScope.userVO.userType == '1'}">超级管理员</c:if> <c:if
										test="${requestScope.userVO.userType == '2'}">普通管理员</c:if></span></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td><span>${requestScope.userVO.userPhone }</span></td>
						</tr>
						<tr>
							<td>身份证</td>
							<td><span>${requestScope.userVO.userCard }</span></td>
						</tr>
						<tr>
							<td>QQ</td>
							<td><span>${requestScope.userVO.userQq }</span></td>
						</tr>
						<tr>
							<td>MSN</td>
							<td><span>${requestScope.userVO.userMsn }</span></td>
						</tr>
						<tr>
							<td>图片</td>
							<td id="pic"><span><img width="250" height="200" alt=""
									src="../${requestScope.userVO.userPhoto }" onmouseover="viewPhoto(this.src)"></span></td>
						</tr>
						<div id="photo"></div>
						<div id="mask"></div>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input onclick="updateBuild_submit()" type="button" value="修改" class="button"
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
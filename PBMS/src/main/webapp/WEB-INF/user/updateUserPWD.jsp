<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css" title="currentStyle" media="screen">
	@import url(${pageContext.request.contextPath}/css/common/maincss1.css);
	@import url(${pageContext.request.contextPath}/css/common/mainborder1.css);
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerys/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerys/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/updateUserPWD.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="updateUserPWD_form" method="POST">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>用户密码更新</caption>
					<thead>
					</thead>
					<tbody>
						<input id="userId" name="userId" type="hidden" value="${requestScope.userVO.userId }" />
						<tr>
							<th><p>登录账号</p></th>
							<td><span>${requestScope.userVO.userLogin }</span></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>输入密码</p></th>
							<td><input name="userPwd1" id="userPwd1" type="password" /></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>确认密码</p></th>
							<td><input name="userPwd" id="userPwd" type="password"
								onblur="checkPassword(this.value)" /></td>
							<td><div>
									<p class="release_error_word" id="checkpassword"></p>
								</div></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input onclick="updateUserPWD_submit()" type="button" value="确定" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /></li>
									<li><input name="sumselect" type="reset" value="重置" class="button"
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
</html>
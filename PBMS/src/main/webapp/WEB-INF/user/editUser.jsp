<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/editUser.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="addUser_form" method="POST" enctype="multipart/form-data">
			<div id="table">
				<table>
					<caption>用户编辑</caption>
					<tbody>
						<input id="userId" name="userId" type="hidden" value="${requestScope.userVO.userId }" />
						<input id="mark" name="mark" type="hidden" value="${requestScope.mark }" />
						<tr>
							<th><p>登录账号</p></th>
							<c:if test="${empty requestScope.userVO.userId }">
								<td><input name="userLogin" id="userLogin" type="text"
									value="${requestScope.userVO.userLogin }" onblur="checkIsLoginName()" /></td>
							</c:if>
							<c:if test="${!empty requestScope.userVO.userId }">
								<td><span>${requestScope.userVO.userLogin }</span></td>
							</c:if>
							<td><div>
									<p class="release_error_word" id="checkLogin"></p>
								</div></td>
						</tr>
						<c:if test="${empty requestScope.userVO.userId }">
							<tr>
								<th><p>密码</p></th>
								<td><input name="userPwd" id="userPwd" type="password" /></td>
								<td><div>
										<p class="release_error_word"></p>
									</div></td>
							</tr>
						</c:if>
						<tr>
							<th><p>用户名</p></th>
							<td><input name="userName" id="userName" type="text"
								value="${requestScope.userVO.userName }" /></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>性别</p></th>
							<td><input name="userSex" type="radio" value="1"
								<c:if test="${requestScope.userVO.userSex == '1' }">checked="checked"</c:if>>男 <input
								name="userSex" type="radio" value="2"
								<c:if test="${requestScope.userVO.userSex == '2' }">checked="checked"</c:if>>女</td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>联系电话</p></th>
							<td><input name="userPhone" id="userPhone" type="text"
								value="${requestScope.userVO.userPhone }" /></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>身份证</p></th>
							<td><input name="userCard" id="userCard" type="text"
								value="${requestScope.userVO.userCard }" /></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>QQ</p></th>
							<td><input name="userQq" id="userQq" type="text" value="${requestScope.userVO.userQq }" /></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>MSN</p></th>
							<td><input name="userMsn" id="userMsn" type="text"
								value="${requestScope.userVO.userMsn }" /></td>
							<td><div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>图片</p></th>
							<td><input name="userFile" id="userFile" type="file" onchange="previewImage(this)" /></td>
							<td><div class="imgBox" id="preview">
									<c:if test="${!empty requestScope.userVO.userPhoto }">
										<img id="imghead" width="250" height="200"
											src='${pageContext.request.contextPath}/${requestScope.userVO.userPhoto }'>
								</div> </c:if> <c:if test="${empty requestScope.userVO.userPhoto }">
									<img id="imghead" width="250" height="200"
										src='${pageContext.request.contextPath}/images/defaul.jpg'>
									</div>
								</c:if></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input id="addUser_submit" type="button" value="确定" class="button"
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
<jsp:include page="/footer.do" />
</html>
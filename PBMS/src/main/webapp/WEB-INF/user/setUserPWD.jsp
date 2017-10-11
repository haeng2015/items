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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/setUserPWD.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="setpwd_form" method="POST">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>验证用户信息</caption>
					<thead>
						<font id="showInfo" color="red"></font>
					</thead>
					<tbody>
						<tr>
							<th><p>输入账号</p></th>
							<td><span><input name="userLogin" id="userLogin" type="text" /></span>
							<div>
									<p class="release_error_word" id="checkLogin"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>联系电话</p></th>
							<td><input name="userPhone" id="userPhone" type="text" />
							<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>身份证</p></th>
							<td><input name="userCard" id="userCard" type="text" />
							<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<center>
									<input onclick="validateUserInfo_submit()" type="button" value="确定" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
									<input name="sumselect" type="reset" value="重置" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
									<input onclick="javascript:history.back(-1)" type="button" value="返回" class="button"
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
</html>
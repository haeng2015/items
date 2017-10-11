<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<style type="text/css" title="currentStyle" media="screen">
		@import url(${pageContext.request.contextPath}/css/common/login.css);
	</style>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerys/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerys/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>

  </head>
  
  <body>
	<div id="top"></div>
	<div id="main">
		<form id="loginForm" method="post" action="">
			<div id="image"></div>
			<div id="login">
				<table>
					<tr>
						<td><p id="loginname"></p></td>
						<td><input name="userLogin" id="userLogin" type="text" placeholder="请输入帐号" class="textin" /></td>
						<td><p class="error_word"></p></td>
					</tr>
					<tr>
						<td><p id="password"></p></td>
						<td><input name="userPwd" id="userPwd" type="password" placeholder="请输入密码" class="textin" /></td>
						<td><p class="error_word"></p></td>
					</tr>
					<tr>
						<td><img title = "点我换一张" alt="" src="${pageContext.request.contextPath}/ClinicCountManager/captcha-image.do" style="cursor:pointer;" id="kaptchaImage"></td>
						<td><input name="securityCode" id="securityCode" type="text" placeholder="验证码" class="textin" /></td>
						<td><p class="error_word"></p></td>
					</tr>
					<tr>
						<td colspan="3"><p id="error_word_securityCode"></p></td>
					</tr>
					<tr>
						<td><input onclick="loginSubmit();" type="button" class="loginbutton" onmouseover="this.className='loginbuttonover'" onmouseout="this.className='loginbutton'"
							onmousedown="this.className='loginbuttondown'" /></td>
						<td><input class="forgetButton" onclick="forgetPWD();" type="button" value="忘记密码?"/></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div id="foot"></div>
</body>
</html>

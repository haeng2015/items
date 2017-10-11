<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/header.do" />
<head>
<title>主页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body>
	<div class="White_box">
		<div class="main_box">
			<div class="nav_left">
				<!--个人信息 begin-->
				<div class="info_box">
					<div style="overflow:auto;">
						<div class="info_pic">
							<img src="${pageContext.request.contextPath}/images/common/pic00.png" />
						</div>
						<div class="info_word">
							<p>
								<b class="tit">${sessionScope.loginUser.realName}</b>
							</p>
							<p>
								<b class="grey">您好，欢迎登录</b>
							</p>
						</div>
					</div>
				</div>
				<!--个人信息 end-->
				<div class="date_div">
					<div class="date_current"></div>
					<div id="date_id"></div>
				</div>
			</div>
			<div class="right_div">
				<!--账号信息-->
				<div class="card_box">
					<div class="card_top">
						<b>账号信息</b>
					</div>
					<div class="card_div">
						<ul class="info_ul">
							<h2>This is my main page.</h2>
						</ul>
					</div>
				</div>
				<!--关于我们-->
				<div class="card_box">
					<div class="card_top">
						<b>关于我们</b>
					</div>
					<div class="card_div">
						<div class="tuijian">
							<p>柏年智能管理平台，将客户分级管理、在先下单、项目跟踪、工程结算、产品应用、后期维保、远程控制、在线报修等，以物联网方式进行整合，并依托大数据分析技术进行升级管理的专业化B2B平台。</p>
							<img src="${pageContext.request.contextPath}/images/common/pic1.png" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>

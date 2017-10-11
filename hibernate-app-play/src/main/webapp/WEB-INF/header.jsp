<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/userCenter/login_bt.png">
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/userCenter/login_bt.png">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/own.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fontCss/icon-fontfamily-ie7.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fontCss/icon-fontfamily.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fontCss/animation.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/main.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/wl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/userCenter.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/validatebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script>
	$(function() {
		$(".qyxx").click(function() {
			$(this).removeAttr("id");
		});
	});
</script>
<div class="b_bg">
	<div class="header_top">
		<div>
			<div class="floatleft">您好！欢迎来到APP播放平台</div>
			<%-- <div class="floatright">
				<p>
					<span> <img src="${pageContext.request.contextPath}/images/common/header_tb8.png">用户： <span>${sessionScope.loginUser.loginName}</span>
					</span> <a class="xg" href="${pageContext.request.contextPath}/changePasswordUI.do"> 【修改密码】 </a> <a class="tc"
						href="javascript:void(0);" onclick="loginOut();">【注销退出】</a>
				</p>
			</div> --%>
		</div>
	</div>
	<div class="header_div">
		<div class="logo_div">
			<div class="logo">
				<a href="javascript:void(0);"> <img src="${pageContext.request.contextPath}/images/common/logo1.png" />
				</a>
			</div>
			<div class="img">
				<img src="${pageContext.request.contextPath}/images/common/header_tp.png" />
			</div>
			<div class="img1">
				<img src="${pageContext.request.contextPath}/images/common/header_tp1.png" />
			</div>
		</div>
	</div>
	<div class="nav_bg">
		<div class="nav">
			<ul>
				<li>
					<div class="block_a">
						<a id="menu_header" href="${pageContext.request.contextPath}/login.do"><p>首&nbsp;&nbsp;页</p>
							<p class="bor">
								<span></span>
							</p></a>
					</div>
				</li>
				<li>
					<div class="block_a">
						<a href="javascript:;"><p>APK管理</p>
							<p class="bor">
								<span></span>
							</p></a>
					</div>
					<div class="block_div">
						<a href="${pageContext.request.contextPath}/apk/toApkList.do">APK列表</a> <a
							href="${pageContext.request.contextPath}/apk/toApkEdit.do">APK编辑</a>
					</div>
				</li>
				<li>
					<div class="block_a">
						<a href="javascript:;"><p>资源管理</p>
							<p class="bor">
								<span></span>
							</p></a>
					</div>
					<div class="block_div">
						<a href="${pageContext.request.contextPath}/resource/toResourceList.do">资源列表</a> <a
							href="${pageContext.request.contextPath}/resource/toResourceEdit.do">资源编辑</a> 
					</div>
				</li>
				<li>
					<div class="block_a">
						<a href="javascript:;"><p>用户设备</p>
							<p class="bor">
								<span></span>
							</p></a>
					</div>
					<div class="block_div">
						<a href="${pageContext.request.contextPath}/device/toDeviceList.do">用户列表</a> <a
							href="${pageContext.request.contextPath}/device/toDeviceEdit.do">用户编辑</a> 
					</div>
				</li>
				<li>
					<div class="block_a">
						<a href="javascript:;"><p>推送管理</p>
							<p class="bor">
								<span></span>
							</p></a>
					</div>
					<div class="block_div">
						<a href="${pageContext.request.contextPath}/device/toDeviceList.do?mark=1">推送列表</a>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
<!----------------------------- 滚动条 --------------------------------------->
<div id="progressBarWindow" class="easyui-window" title="程序正在努力运行中，请稍候片刻……" style="width:350px;height:121px;"
	data-options="modal:true,closable:false,collapsible:false,minimizable:false,maximizable:false,draggable:false">
	<div id="progressBar" class="easyui-progressbar" data-options="value:20,text:''"
		style="margin: 20px;text-align: left;padding-left: 0px;"></div>
</div>
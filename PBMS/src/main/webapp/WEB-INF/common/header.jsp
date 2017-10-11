<%@ page language="java" pageEncoding="UTF-8" errorPage="/error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css" title="currentStyle" media="screen">
@import url(${pageContext.request.contextPath}/css/common/maincss1.css);

@import
	url(${pageContext.request.contextPath}/css/common/mainborder1.css);

@import url(${pageContext.request.contextPath}/css/common/login.css);
/* @import url(${pageContext.request.contextPath}/css/common/mainborder2.css); */
/* @import url(${pageContext.request.contextPath}/css/common/maincss2.css); */
/* @import url(${pageContext.request.contextPath}/css/common/submit1.css); */
/* @import url(${pageContext.request.contextPath}/css/common/submit2.css); */
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo.css"> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/own.css">
<link type="text/css" href="${pageContext.request.contextPath}/fontCss/icon-fontfamily-ie7.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fontCss/icon-fontfamily.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fontCss/animation.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jqGrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jqGrid/ui.multiselect.css" />
<%-- <script src="${pageContext.request.contextPath}/js/common/proAjax.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/birthday.js" type="text/javascript"></script> --%>
<script src="${pageContext.request.contextPath}/js/common/valentine.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/information.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerys/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquerys/ajaxFileUpload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerys/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validatebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/attachment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/address.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jqGrid/grid.locale-en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerys/jquery.easyui.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquerys/easyui-lang-zh_CN.js" charset="UTF-8"></script>
<div id="icon">fourthfire</div>
<div id="userinfo">
	<h5>欢迎 :</h5>
	<h5>${sessionScope.userVO.userName}</h5>
	<h5>
		<a href="javascript:void(0)" id="cancelQuite">注销退出</a>
	</h5>
	<h5>
		<a href="javascript:void(0)" onclick="updateUserInfo()">修改个人信息</a>
	</h5>
	<h5>当前时间 ：</h5>
	<font id="year"></font> <font>-</font> <font id="mouth"></font> <font>-</font> <font id="day"></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<h5 id="hours"></h5>
	<h5>:</h5>
	<h5 id="minutes"></h5>
	<h5>:</h5>
	<h5 id="seconds"></h5>
	<!-- 当前时间设置（在这种没有head和html标签的页面直接引入js无效，且不能将js放在所引用页面的上方） -->
	<script type="text/javascript">
	<!--
		var isn1 = null;
		var isn2 = false;
		today = new Date();
		startit();

		function stopit() {
			if (isn2) {
				clearTimeout(isn1);
			}
			isn2 = false;
		}
		function startit() {
			stopit();
			isnclock();
		}
		function isnclock() {
			var now = new Date();

			var year = now.getUTCFullYear();
			var mou = now.getMonth() + 1; //从0开始，需要加1
			var day = now.getDate();
			document.getElementById("year").innerHTML = year;
			document.getElementById("mouth").innerHTML = mou;
			document.getElementById("day").innerHTML = day;

			var hrs = now.getHours();
			var min = now.getMinutes();
			var sec = now.getSeconds();
			document.getElementById("hours").innerHTML = ""
					+ ((hrs > 24) ? hrs - 24 : hrs);
			document.getElementById("minutes").innerHTML = ((min < 10) ? "0"
					: "")
					+ min;
			document.getElementById("seconds").innerHTML = ((sec < 10) ? "0"
					: "")
					+ sec;
			isn1 = setTimeout("isnclock()", 1000);
			isn2 = true;
		}

		function isnArray() {
			argnr = isnArray.arguments.length
			for (var i = 0; i < argnr; i++) {
				this[i + 1] = isnArray.arguments[i];
			}
		}
		var isnMonths = new isnArray("1月", "2月", "3月", "4月", "5月", "6月", "7月",
				"8月", "9月", "10月", "11月", "12月");
		var isnDays = new isnArray("星期一", "星期二", "星期三", "星期四", "星期五", "星期六",
				"星期日");
		isnDays[0] = "星期日";
	//-->
	</script>
</div>
<div id="topicon"></div>
<div id="center">
	<div id="banner">
		<div id="topimg"></div>
		<ul id="bannerul">
			<li class="bannerli" onmouseover="show(1);"><h5>
					<a href="#">用户管理</a>
				</h5>
				<ul class="bannerchild" id="banner1">
					<li><a href="${pageContext.request.contextPath}/user/toUserList.do">用户管理</a></li>
					<li><a href="${pageContext.request.contextPath}/user/toRoleList.do">角色管理</a></li>
					<li><a href="${pageContext.request.contextPath}/user/toAuthList.do">权限查看</a></li>
					<li><a href="backup/backup.html">数据管理</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(2);"><h5>
					<a href="#">楼栋管理</a>
				</h5>
				<ul class="bannerchild" id="banner2">
					<li><a href="${pageContext.request.contextPath}/build/editBuilding.do">增加楼栋</a></li>
					<li><a href="${pageContext.request.contextPath}/build/toBuildingList.do">查询楼栋</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(3);"><h5>
					<a href="#">房间管理</a>
				</h5>
				<ul class="bannerchild" id="banner3">
					<li><a href="${pageContext.request.contextPath}/room/editRoom.do">增加房间</a></li>
					<li><a href="${pageContext.request.contextPath}/room/toRoomList.do">查询房间</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(4);"><h5>
					<a href="#">业主管理</a>
				</h5>
				<ul class="bannerchild" id="banner4">
					<li><a href="${pageContext.request.contextPath}/owner/editOwner.do">增加业主</a></li>
					<li><a href="${pageContext.request.contextPath}/owner/toOwnerList.do">查询业主</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(5);"><h5>
					<a href="#">业主成员</a>
				</h5>
				<ul class="bannerchild" id="banner5">
					<li><a href="member/MemberPreAdd.do">增加成员</a></li>
					<li><a href="member/MemberList.do">修改成员</a></li>
					<li><a href="member/MemberList.do">查询成员</a></li>
					<li><a href="member/MemberList.do">删除成员</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(6);">
				<h5>
					<a href="#">收费项目</a>
				</h5>
				<ul class="bannerchild" id="banner6">
					<li><a href="${pageContext.request.contextPath}/charge/editCharge.do">增加项目</a></li>
					<li><a href="${pageContext.request.contextPath}/charge/toChargeList.do">查询项目</a></li>
				</ul>
			</li>
			<li class="bannerli" onmouseover="show(7);"><h5>
					<a href="#">业主缴费</a>
				</h5>
				<ul class="bannerchild" id="banner7">
					<li><a href="pay/payList.html">业主缴费</a></li>
					<li><a href="pay/payList.html">缴费单查询</a></li>
					<li><a href="pay/payList.html">修改收费记录</a></li>
					<li><a href="pay/pagestat.html">缴费统计</a></li>
					<li><a href="pay/payList.html">删除缴费</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(8);"><h5>
					<a href="#">物资类别</a>
				</h5>
				<ul class="bannerchild" id="banner8">
					<li><a href="type/typeAdd.html">添加物资类别</a></li>
					<li><a href="type/typeList.html">修改物资类别</a></li>
					<li><a href="type/typeList.html">查询物资类别</a></li>
					<li><a href="type/typeList.html">删除物资类别</a></li>
				</ul></li>
			<li class="bannerli" onmouseover="show(9);"><h5>
					<a href="#">物资设备</a>
				</h5>
				<ul class="bannerchild" id="banner9">
					<li><a href="material/materialAdd.html">入库</a></li>
					<li><a href="material/materialList.html">出库</a></li>
					<li><a href="material/materialList.html">查询物资</a></li>
					<li><a href="material/materialAdd.html">添加物资</a></li>
					<li><a href="material/materialList.html">修改物资</a></li>
				</ul></li>
		</ul>
	</div>
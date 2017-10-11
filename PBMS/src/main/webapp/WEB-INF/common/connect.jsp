<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<div id="main">
		<div id="table">
			<h5 class="toph5">点击以下连接寻找您所需要的管理员</h5>
			<table>
				<caption>以下是管理员联系方式，请选择您支持的方式</caption>
				<tr>
					<td><a target="blank"
						href="http://wpa.qq.com/msgrd?V=1&Uin=59170121&Site=物业管理系统& Menu=yes"><img border="0"
							SRC=http://wpa.qq.com/pa?p=1:59170121:6 alt="点击这里给我发消息" align="absmiddle">QQ用户请点这里</a></td>
				</tr>
				<tr>
					<td><a href="http://osi.hshh.org:8088/message/msn/akipeng@163.com"> <img
							src="http://osi.hshh.org:8088/msn/akipeng@hotmail.com" align="absmiddle" border="0"
							alt="MSN Online Status Indicator">MSN用户请点这里
					</a></td>
				</tr>
				<tr>
					<td><a href="skype:venky1979?call" on-click="return skypeCheck();"><img
							src=http://mystatus.skype.com/smallclassic/akipeng style="border: none;" alt="Call me!" />SKYPE用户请点这里</a>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>
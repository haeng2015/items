<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>用户设备管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/device/deviceEdit.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>用户设备管理</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/device/toDeviceList.do">用户列表</a></li>
					<li class="cur"><a href="${pageContext.request.contextPath}/device/toDeviceEdit.do">用户编辑</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<form id="deviceEdit_form">
						<table cellpadding="0" cellspacing="0" id="device_table">
							<input type="hidden" id="userDeviceId" name="userDeviceId" value="${requestScope.deviceVO.userDeviceId}">
							<tr>
								<td><p>用户名称：</p></td>
								<td><div>
										<c:if test="${empty requestScope.userName}">
											<input placeholder="请输入用户名称" name="userName" id="userName"
												data-options="required:true,validType:['length[1,50]']" class="easyui-validatebox tb"
												value="${requestScope.deviceVO.userName}">
										</c:if>
										<c:if test="${!empty requestScope.userName}">
											<input readonly="readonly" name="userName" id="userName" value="${requestScope.userName}" class="easyui-validatebox tb">
										</c:if>
									</div>
									<p class="release_error_word"></p></td>
								<td><p>设备名称：</p></td>
								<td><div>
										<input placeholder="请输入设备名称" name="deviceName" id="deviceName"
											data-options="required:true,validType:['length[1,50]']" class="easyui-validatebox tb"
											value="${requestScope.deviceVO.deviceName}">
									</div>
									<p class="release_error_word"></p></td>
							</tr>
							<tr>
								<td><p>设备ID：</p></td>
								<td><div>
										<input placeholder="请输入设备ID" name="deviceId" id="deviceId" class="easyui-validatebox tb"
											data-options="required:true,validType:['length[1,50]'<c:if test="${empty requestScope.deviceVO.userDeviceId}">,'checkDeviceId'</c:if>]" 
											value="${requestScope.deviceVO.deviceId}" <c:if test="${!empty requestScope.deviceVO.userDeviceId}">readonly="readonly"</c:if> >
									</div></td>
								<td><p>设备编号：</p></td>
								<td><div>
										<input placeholder="请输入设备编号" name="deviceNum" id="deviceNum" class="easyui-validatebox tb"
											data-options="required:true,validType:['length[1,50]']" 
											value="${requestScope.deviceVO.deviceNum}" >
									</div></td>
							</tr>
							<tr>
								<td><p>设备位置：</p></td>
								<td colspan="3"><div>
										<textarea rows="1" cols="100" placeholder="请输入设备所在位置" name="devicePlace" id="devicePlace" class="easyui-validatebox tb"
											data-options="required:true,validType:['length[1,100]']" >${requestScope.deviceVO.devicePlace}</textarea>
									</div></td>
							</tr>
							<tr>
								<td><p>备注:</p></td>
								<td colspan="3"><textarea data-options="required:false,validType:['length[1,500]']" class="easyui-validatebox tb"
								 rows="4" cols="100" id="remarks" name="remarks">${requestScope.deviceVO.remarks}</textarea></td>
							</tr>
							<tr>
								<td colspan="4">
									<div class="btn">
										<center>
											<input type="button" value="保存" onclick="saveOrUpdateDevice();"
												style="cursor: pointer;border: none;width: 80px;padding: 7px;color: #fff;background: #e21323;" />
										</center>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="/footer.do" />
</html>

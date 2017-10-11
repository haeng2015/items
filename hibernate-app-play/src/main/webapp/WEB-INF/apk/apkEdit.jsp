<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>apk文件编辑</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/apk/apkEdit.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>APK列表管理</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/apk/toApkList.do">APK列表</a></li>
					<li class="cur"><a href="${pageContext.request.contextPath}/apk/toApkEdit.do">APK编辑</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<form id="apkEdit_form">
						<table cellpadding="0" cellspacing="0" id="apk_table">
							<input type="hidden" id="apkId" name="apkId" value="${requestScope.attachmentApkVO.apkId}">
							<tr>
								<td><p>版本号:</p></td>
								<td><div>
										<input placeholder="请输入版本号" name="versionCode" id="versionCode" 
											data-options="required:true,validType:['figure','length[1,11]'<c:if test="${empty requestScope.attachmentApkVO.apkId}">,'checkVersionNumber'</c:if>]" class="easyui-validatebox tb" 
											value="${requestScope.attachmentApkVO.versionCode}" <c:if test="${!empty requestScope.attachmentApkVO.apkId}">readonly="readonly"</c:if> >
									</div>
									<p class="release_error_word"></p></td>
								<td><p>apk文件:</p></td>
								<td><div>
										<c:if test="${!empty requestScope.attachmentApkVO.apkId}">
										<input id="apkFileUpload" name="apkFileUpload" type="file" >
											<a
												href="${pageContext.request.contextPath}/apk/downloadApk.do?uniqueName=${requestScope.attachmentApkVO.uniqueName}"
												title="点击下载"> <font color="blue">${requestScope.attachmentApkVO.fileName}</font></a>
										</c:if>
										
										<c:if test="${empty requestScope.attachmentApkVO.apkId}">
											<input id="apkFileUpload" name="apkFileUpload" type="file" class="easyui-validatebox" data-options="required:true">
										</c:if>
									</div></td>
							</tr>
							<tr>
								<td><p>备注:</p></td>
								<td colspan="3"><textarea data-options="required:false,validType:['length[1,500]']" class="easyui-validatebox tb"
								rows="4" cols="100" id="remarks" name="remarks">${requestScope.attachmentApkVO.remarks}</textarea></td>
							</tr>
							<tr>
								<td colspan="4"><a href="${pageContext.request.contextPath}/apk/downloadLastApk.do"><font color="red">下载最新版本</font></a>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<div class="btn">
										<center>
											<input type="button" value="保存" onclick="saveOrUpdateApk();"
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

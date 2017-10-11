<%@ page language="java" pageEncoding="UTF-8" errorPage="/accessError.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/header.do" />
<head>
<title>资源文件编辑</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/resource/resourceEdit.js"></script>
</head>
<body>
	<div class="White_box">
		<div class="content">
			<div class="left_nav">
				<h2>资源列表管理</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/resource/toResourceList.do">资源列表</a></li>
					<li class="cur"><a href="${pageContext.request.contextPath}/resource/toResourceEdit.do">资源编辑</a></li>
				</ul>
			</div>
			<div class="right_div">
				<div class="easyui-body">
					<form id="resourceEdit_form">
						<table cellpadding="0" cellspacing="0" id="resource_table">
							<input type="hidden" id="resourceId" name="resourceId" value="${requestScope.attachmentSourceVO.resourceId}">
							<tr>
								<td><p>文件类型：</p></td>
								<td><div>
										<select name="type" id="type" data-options="required:true" class="easyui-validatebox tb">
											<option value="">请选择文件类型</option>
											<option value="1" <c:if test="${requestScope.attachmentSourceVO.type == '1'}">selected="selected"</c:if>>H5</option>
											<option value="2" <c:if test="${requestScope.attachmentSourceVO.type == '2'}">selected="selected"</c:if>>视频</option>
										</select>
									</div>
									<p class="release_error_word"></p></td>
								<td><p>资源文件:</p></td>
								<td><div>
										<input id="resourceFileUpload" name="resourceFileUpload" type="file" <c:if test="${empty requestScope.attachmentSourceVO.resourceId}"> class="easyui-validatebox" data-options="required:true"</c:if> >
										<c:if test="${!empty requestScope.attachmentSourceVO.resourceId}">
											<a
												href="${pageContext.request.contextPath}/resource/downloadResourceByName.do?uniqueName=${requestScope.attachmentSourceVO.uniqueName}"
												title="点击下载"> <font color="blue">${requestScope.attachmentSourceVO.fileName}</font></a>
										</c:if>
									</div></td>
							</tr>
							<tr>
								<td><p>备注:</p></td>
								<td colspan="3"><textarea data-options="required:false,validType:['length[1,500]']" class="easyui-validatebox tb"
								 rows="4" cols="100" id="remarks" name="remarks">${requestScope.attachmentSourceVO.remarks}</textarea></td>
							</tr>
							<tr>
								<td colspan="4">
									<div class="btn">
										<center>
											<input type="button" value="保存" onclick="saveOrUpdateResource();"
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

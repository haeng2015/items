<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/build/editBuilding.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="addBuilding_form" method="POST" enctype="multipart/form-data">
			<div id="table">
				<table>
					<caption>楼栋添加</caption>
					<tbody>
						<input id="buildId" name="buildId" type="hidden" value="${requestScope.buildingVO.buildId }" />
						<input id="startDate" name="startDate" type="hidden"
							value="${requestScope.buildingVO.buildStartDate }" />
						<input id="endDate" name="endDate" type="hidden"
							value="${requestScope.buildingVO.buildEndDate }" />
						<tr>
							<th><p>楼栋名</p></th>
							<td><input name="buildName" id="buildName" type="text"
								value="${requestScope.buildingVO.buildName }" />
							<div><p class="release_error_word"></p></div></td>
						</tr>
						<tr>
							<th><p>开工时间</p></th>
							<td><input id="buildStartDate" name="buildStartDate"
								value="${requestScope.buildingVO.buildStartDate }" class="easyui-datebox"
								data-options="editable:false" />
							<div><p class="release_error_word"></p></div></td>
						</tr>
						<tr>
							<th><p>竣工时间</p></th>
							<td><input name="buildEndDate" id="buildEndDate"
								value="${requestScope.buildingVO.buildEndDate }" class="easyui-datebox"
								data-options="editable:false" />
							<div><p class="release_error_word"></p></div></td>
						</tr>
						<tr>
							<th><p>建筑面积</p></th>
							<td><input name="buildArea" id="buildArea" type="text"
								value="${requestScope.buildingVO.buildArea }" />
							<div><p class="release_error_word"></p></div></td>
						</tr>
						<tr>
							<th><p>楼栋资料</p></th>
							<td><center id="aAddAttachLimit">
									<a href="javascript:addFile();" id="aAddAttach"><font color="green">添加附件</font></a>
								</center></td>
						</tr>
						<tr>
							<th><p>附件</p></th>
							<td><div id="dvReadAttach">
									<c:if test="${!empty requestScope.buildingVO.buildId }">
										<c:forEach items="${requestScope.boAccessoryBuilds }" var="materials">
											<a href="javascript:void();" id="${materials.id }" title="点击下载"
												onclick="uploadMaterials(this.id);"> <font color="blue">${materials.name }</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:void();" id="${materials.id }" onclick="deleteMaterials(this.id)">删除</a>
											<br>
										</c:forEach>
									</c:if>
								</div></td>
						</tr>
						<tr>
							<th><p>楼栋信息</p></th>
							<td><textarea style="width:526px;height:66px;" id="buildInfo" name="buildInfo">${requestScope.buildingVO.buildInfo }</textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input id="addBuilding_submit" type="button" value="确定" class="button"
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
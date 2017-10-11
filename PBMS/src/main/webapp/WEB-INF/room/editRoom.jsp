<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/room/editRoom.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="addRoom_form" method="POST" enctype="multipart/form-data">
			<div id="table">
				<table>
					<caption>楼栋编辑</caption>
					<input id="roomId" name="roomId" type="hidden" value="${requestScope.roomVO.roomId }" />
					<tr>
						<th><p>楼栋名</p></th>
						<td><select name="buildId" id="buildId">
								<option value="">请选择楼栋</option>
								<option value="0"
									<c:if test="${0 == requestScope.roomVO.buildId}">selected="selected"</c:if>>暂未分配楼栋</option>
								<c:forEach items="${requestScope.buildingVOs }" var="build">
									<option value="${build.buildId }"
										<c:if test="${build.buildId == requestScope.roomVO.buildId}">selected="selected"</c:if>>${build.buildName }</option>
								</c:forEach>
						</select></td>
					</tr>
					<%-- <tr>
						<th><p>所属用户</p></th>
						<td><select name="ownerId" id="ownerId">
								<option value="">请选择所属者</option>
								<option value="0"
									<c:if test="${0 == requestScope.roomVO.ownerId}">selected="selected"</c:if>>暂无业主</option>
								<c:forEach items="${requestScope.OwnerVOs }" var="owner">
									<option value="${owner.ownerId }"
										<c:if test="${owner.ownerId == requestScope.roomVO.ownerId}">selected="selected"</c:if>>${owner.ownerName }</option>
								</c:forEach>
						</select></td>
					</tr> --%>
					<tr>
						<th><p>房间名</p></th>
						<td><input name="roomName" id="roomName" type="text"
							value="${requestScope.roomVO.roomName }" />
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>入住时间</p></th>
						<td><input id="roomStartDate" name="roomStartDate"
							value="${requestScope.roomVO.roomStartDate }" class="easyui-datebox"
							data-options="editable:false" />
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>到期时间</p></th>
						<td><input id="roomEndDate" name="roomEndDate"
							value="${requestScope.roomVO.roomEndDate }" class="easyui-datebox"
							data-options="editable:false" />
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>房型</p></th>
						<td><select name="roomType" id="roomType">
								<option value="">请选择房型</option>
								<option value="平层户型"
									<c:if test="${requestScope.roomVO.roomType == '平层户型' }">selected="selected"</c:if>>平层户型</option>
								<option value="跃层户型"
									<c:if test="${requestScope.roomVO.roomType == '跃层户型' }">selected="selected"</c:if>>跃层户型</option>
								<option value="错层户型"
									<c:if test="${requestScope.roomVO.roomType == '错层户型' }">selected="selected"</c:if>>错层户型</option>
								<option value="复式户型"
									<c:if test="${requestScope.roomVO.roomType == '复式户型' }">selected="selected"</c:if>>复式户型</option>
								<option value="小户型"
									<c:if test="${requestScope.roomVO.roomType == '小户型' }">selected="selected"</c:if>>小户型</option>
								<option value="大户型"
									<c:if test="${requestScope.roomVO.roomType == '大户型' }">selected="selected"</c:if>>大户型</option>
						</select>
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>用途</p></th>
						<td><select name="roomUse" id="roomUse">
								<option value="">请选择用途</option>
								<option value="住宅"
									<c:if test="${requestScope.roomVO.roomUse == '住宅' }">selected="selected"</c:if>>住宅</option>
								<option value="非住宅"
									<c:if test="${requestScope.roomVO.roomUse == '非住宅' }">selected="selected"</c:if>>非住宅</option>
								<option value="商用"
									<c:if test="${requestScope.roomVO.roomUse == '商用' }">selected="selected"</c:if>>商用</option>
								<option value="办公"
									<c:if test="${requestScope.roomVO.roomUse == '办公' }">selected="selected"</c:if>>办公</option>
								<option value="物管用房"
									<c:if test="${requestScope.roomVO.roomUse == '物管用房' }">selected="selected"</c:if>>物管用房</option>
								<option value="自留房"
									<c:if test="${requestScope.roomVO.roomUse == '自留房' }">selected="selected"</c:if>>自留房</option>
								<option value="抵押房"
									<c:if test="${requestScope.roomVO.roomUse == '抵押房' }">selected="selected"</c:if>>抵押房</option>
								<option value="其它"
									<c:if test="${requestScope.roomVO.roomUse == '其它' }">selected="selected"</c:if>>其它</option>
						</select>
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>建筑面积</p></th>
						<td><input name="roomArea" id="roomArea" type="text"
							value="${requestScope.roomVO.roomArea }" />
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>得房率</p></th>
						<td><input name="roomProb" id="roomProb" type="text"
							value="${requestScope.roomVO.roomProb }" />
							<div>
								<p class="release_error_word"></p>
							</div></td>
					</tr>
					<tr>
						<th><p>房间附件</p></th>
						<td><center id="aAddAttachLimit">
								<a href="javascript:addFile();" id="aAddAttach"><font color="green">添加附件</font></a>
							</center></td>
					</tr>
					<tr>
						<td></td>
						<td><div id="dvReadAttach">
								<c:if test="${!empty requestScope.roomVO.buildId }">
									<c:forEach items="${requestScope.boAccessoryRooms }" var="materials">
										<a href="javascript:void();" id="${materials.id }" title="点击下载"
											onclick="uploadMaterials(this.id);"> <font color="blue">${materials.name }</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:void();" id="${materials.id }" onclick="deleteMaterials(this.id)">删除</a>
										<br>
									</c:forEach>
								</c:if>
							</div></td>
					</tr>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input id="addRoom_submit" type="button" value="确定" class="button"
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
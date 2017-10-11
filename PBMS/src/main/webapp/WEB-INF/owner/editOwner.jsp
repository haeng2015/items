<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/owner/editOwner.js"></script>
</head>
<body>
	<div id="main" class="index">
		<form id="addOwner_form" method="POST" enctype="multipart/form-data">
			<div id="table">
				<table>
					<caption>业主编辑</caption>
					<tbody>
						<input id="ownerId" name="ownerId" type="hidden" value="${requestScope.ownerVO.ownerId }" />
						<tr>
							<th><p>业主姓名</p></th>
							<td><input name="ownerName" id="ownerName" type="text"
								value="${requestScope.ownerVO.ownerName }" />
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>性别</p></th>
							<td><input type="radio" name="ownerSex" value="1"
								<c:if test="${requestScope.ownerVO.ownerSex == '1' }">checked="checked"</c:if> />男 <input
								type="radio" name="ownerSex" value="2"
								<c:if test="${requestScope.ownerVO.ownerSex == '2' }">checked="checked"</c:if> />女
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<input id="build" name="build" type="hidden" value="${requestScope.ownerVO.buildId }" />
							<input id="room" name="room" type="hidden" value="${requestScope.ownerVO.roomId }" />
							<th><p>居住房间</p></th>
							<td><select name="buildId" id="buildId" onchange="getRoomsByBuildId(this.value)">
									<option value="">请选择楼栋</option>
									<c:forEach items="${requestScope.buildingList }" var="building">
										<option value="${building.buildId }"
											<c:if test="${requestScope.ownerVO.buildId == building.buildId }">selected="selected"
										</c:if>>${building.buildName }</option>
									</c:forEach>
							</select> <em id="selcctRoom"></em> <!-- <select id="roomId" name="roomId">
								<option value="">请选择房间</option>
							</select> -->
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<input id="provinceId" name="provinceId" type="hidden"
								value="${requestScope.ownerVO.provinceId }" />
							<input id="cityId" name="cityId" type="hidden" value="${requestScope.ownerVO.cityId }" />
							<input id="regionId" name="regionId" type="hidden" value="${requestScope.ownerVO.regionId }" />
							<th><p>籍贯</p></th>
							<td><select id="pId" name="pId" onchange="getCityByProvince(this.value)">
									<option value="">请选择省份</option>
									<c:forEach items="${requestScope.addressList }" var="province">
										<option value="${province.pId }"
											<c:if test="${requestScope.ownerVO.provinceId == province.pId }">selected="selected"</c:if>>${province.pName }</option>
									</c:forEach>
							</select> <em id="selcctCity"></em> <em id="selcctRegion"></em> <!-- <select id="cId" name="cId" onclick="getRegionByCity(this)">
									<option value="">请选择市级</option>
							</select> <select id="rId" name="rId">
									<option value="">请选择区级</option>
							</select> -->
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>联系电话</p></th>
							<td><input name="ownerPhone" id="ownerPhone" type="text"
								value="${requestScope.ownerVO.ownerPhone }" />
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>身份证</p></th>
							<td><input name="ownerCard" id="ownerCard" type="text"
								value="${requestScope.ownerVO.ownerCard }" />
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>工作单位</p></th>
							<td><input name="ownerWork" id="ownerWork" type="text"
								value="${requestScope.ownerVO.ownerWork }" />
								<div>
									<p class="release_error_word"></p>
								</div></td>
						</tr>
						<tr>
							<th><p>业主附件</p></th>
							<td><center id="aAddAttachLimit">
									<a href="javascript:addFile();" id="aAddAttach"><font color="green">添加附件</font></a>
								</center></td>
						</tr>
						<tr>
							<td></td>
							<td><div id="dvReadAttach">
									<c:if test="${!empty requestScope.boAccessoryOwner }">
										<c:forEach items="${requestScope.boAccessoryOwner }" var="materials">
											<a href="javascript:void();" id="${materials.id }" title="点击下载"
												onclick="uploadMaterials(this.id);"> <font color="blue">${materials.name }</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:void();" id="${materials.id }" onclick="deleteMaterials(this.id)">删除</a>
											<br>
										</c:forEach>
									</c:if>
								</div></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<center>
									<input onclick="addOwner_submit()" type="button" value="确定" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> <input
										name="sumselect" type="reset" value="重置" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
									<c:if test="${!empty requestScope.ownerVO.ownerId }">
										<input onclick="javascript:history.back(-1);" type="button" value="返回" class="button"
											onmouseout="this.className='button';" onmouseover="this.className='buttondown';" />
									</c:if>
								</center>
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
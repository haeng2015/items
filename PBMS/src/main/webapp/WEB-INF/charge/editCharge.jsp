<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/charge/editCharge.js"></script>
</head>
<body>
	<div id="main">
		<form id="editCharge_form" method="post">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>添加收费项目</caption>
					<input id="chargeId" name="chargeId" type="hidden" value="${requestScope.chargeVO.chargeId }" />
					<tbody>
						<tr>
							<td>项目名称</td>
							<td><input name="chargeName" id="chargeName" type="text"
								data-options="required:true,validType:['name','length[1,30]']" class="easyui-textbox num"
								value="${requestScope.chargeVO.chargeName }" /></td>
						</tr>
						<tr>
							<td>收费标准/元/m/月</td>
							<td><input name="chargePrice" id="chargePrice" type="text"
								value="${requestScope.chargeVO.chargePrice }"
								data-options="required:true,validType:['money','length[1,10]']" class="easyui-textbox num" /></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<center>
									<input onclick="addCharge()" type="button" value="确定" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /> 
										<input type="reset" value="重置" class="button" onmouseout="this.className='button';"
										onmouseover="this.className='buttondown';" />
									<c:if test="${!empty requestScope.chargeVO.chargeId }">
										<input type="button" value="返回" class="button" onmouseout="this.className='button';"
										onmouseover="this.className='buttondown';" onclick="javascript:window.back(-1)" />
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
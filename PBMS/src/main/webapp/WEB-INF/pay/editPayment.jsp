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
		<form id="editPayment_form" method="post">
			<div id="index"></div>
			<div id="table">
				<table>
					<caption>生 成 催 费 单</caption>
					<tbody>
						<tr>
							<td>收费项目</td>
							<td><select name="chargeId">
									<option selected>--请选择--</option>
									<option value=""></option>
							</select></td>
							<td>请选择收费项目</td>
						</tr>
						<tr>
							<td>应收金额</td>
							<td><input name="payMoney" type="text" value="" /></td>
							<td>请输入应收金额，数据为浮点型</td>
						</tr>
						<tr>
							<td>实收金额</td>
							<td><input name="payReceive" type="text" value="" /></td>
							<td>生成催费单时实收金额应为0</td>
						</tr>
						<tr>
							<td>欠费金额</td>
							<td><input name="payLack" type="text" value="" /></td>
							<td>生成催费单时应与应收金额相等</td>
						</tr>
						<tr>
							<td>收费时间</td>
							<td><input name="payDate" type="text" value=""
								onfocus="show_cele_date(payDate,'','',payDate)" /></td>
							<td>请填写生成催费单日期</td>
						</tr>
						<tr>
							<td>用户名</td>
							<td><select name="ownerId">
									<option selected>--请选择--</option>
									<option value=""></option>
							</select></td>
							<td>请选择缴费业主</td>
						</tr>
						<tr>
							<td>收费状态</td>
							<td><select name="payState">
									<option value="0">未缴费</option>
									<option value="1">已缴费</option>
							</select></td>
							<td>生成催费单时默认为未缴费</td>
						</tr>
						<tr>
							<td>缴费月份</td>
							<td><input name="payMonth" type="text" value=""
								onfocus="show_cele_date(payMonth,'','',payMonth)" /></td>
							<td>请输入需缴费的月份，格式为****-**-**</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<ul>
									<li><input name="addnews" type="submit" value="确定" class="button"
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
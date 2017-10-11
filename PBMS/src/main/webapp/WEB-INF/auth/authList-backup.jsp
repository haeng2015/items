<%@ page language="java" pageEncoding="UTF-8" errorPage="error.do"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/header.do" />
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user/authList.js"></script>
</head>
<body>
	<div id="main">
		<form name="authList_form" method="post">
			<div id="index">
				<span class="style3">查询条件：</span> <select name="oneway">
					<option value="0" selected>--请选择--</option>
					<option value="1">按权限名</option>
				</select> <input type="text" name="onetext" value="请输入关键字" id="txt1" onfocus="selectText();" /> <input
					type="submit" name="Submit" value="查询" />
			</div>
		</form>
		<form name="form2" method="post" action="#" onsubmit="return befSubmit();">
			<div id="table">
				<table id="ec_table">
					<caption>权限信息表</caption>
					<thead>
						<tr>
							<th>选中</th>
							<th onclick="sortTable('ec_table',1,'string')">权限名</th>
							<th>修改</th>
						</tr>
					</thead>
					<tbody>
						<tr class="lightdown" onmousemove="this.className='lightup';"
							onmouseout="this.className='lightdown';">
							<td><input name="checkone" type="checkbox" value="${dto.powerId}" /></td>
							<td></td>
							<td><a href="PowerPreUpdate.do?powerId=${dto.powerId}">edit</a></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<h5>共4条信息</h5>
								<h5>当前第1页</h5>
								<ul>
									<li>首页</li>
									<li>前页</li>
									<li>后页</li>
									<li>末页</li>
									<li><span>转到</span> <select name="" id="pagenumber" onchange="build();">
											<option value="">第1页</option>
									</select></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<ul>
									<li><input name="add" type="button" value="添加权限"
										onclick="window.location='powerAdd.html'" class="button"
										onmouseout="this.className='button';" onmouseover="this.className='buttondown';" /></li>
									<li><input name="delete" type="submit" value="删除权限" class="button"
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
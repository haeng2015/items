/**
 * @author Hehaipeng
 * @date 2017年3月28日
 * @todo TODO 加载role列表
 */
$(document).ready(function() {

			loadAuthList();

		});

/**
 * 加载role列表
 */
function loadAuthList(pageNo) {
	// 往map中填值（在javascript语言中，key的值只能是字符串，不能是其它的。）
	// 或者map.key3='12';
	var map = {
		authName : $("#authName").val()
	};

	$.ajax({
		url : "loadAuthList.do",
		data : {
			params : map,
			orderColumn : 'AUTH_NAME', // 要排序的列
			orderTurn : "DESC", // 逆序排列
			pageNo : pageNo
		},
		type : "post",
		dataType : "json",
		success : function(json) {
			var resultList = json.resultList; // 结果集对象
			var totalSize = json.totalSize; // 总共记录条数
			var totalPage = json.totalPage; // 总页数
			var currentPage = json.currentPage; // 当前页
			var roleIds = new Array();
			$("#main").empty();
			var element = '';
			element += '<form name="authList_form" method="post">';
			element += '<div id="index">角色名:<input type="text" name="authName" id="authName" />';
			element += '<input type="button" value="查询" onclick="loadAuthList(null);" /></div>';
			element += '<div id="table"><table id="ec_table"><caption>权限信息表</caption>';
			element += '<thead><tr><td>全选/全不选<input type="checkbox" id="authAllCheckbox" onclick="checkAllCheckbox();"</td>'
					+ '<td>权限名</td></tr></thead>';

			element += '<tbody>';
			for (var index = 0; index < resultList.length; index++) {
				element += '<tr class="lightdown" onmousemove="this.className=\'lightup\';"onmouseout="this.className=\'lightdown\';">';
				element += '<td><input type="checkbox" name="authCheckbox" id="authCheckbox'
						+ index
						+ '" onclick="selChk(frm, chkVal, this.id)"></td>';
				element += '<td>' + resultList[index].authName + '</td>';
			}
			element += '</tbody>';
			element += '<tfoot><tr><td colspan="2"><h5>共搜素到' + totalSize
					+ '条信息</h5><ul>';
			element += '<li><a href="javascipt:void(0);" onclick="loadAuthList(1)" class="style1">首页</a></li>';

			if (currentPage > 1) {
				element += '<li><a href="javascipt:void(0);" onclick="loadAuthList('
						+ (currentPage - 1) + ')" >上一页</a></li>';
			} else {
				element += '<li><a href="javascipt:void(0);" >上一页</a></li>';
			}

			if (currentPage < totalPage) {
				element += '<li><a href="javascipt:void(0);" onclick="loadAuthList('
						+ (currentPage + 1) + ')">下一页</a></li>';
			} else {
				element += '<li><a href="javascipt:void(0);" >下一页</a></li>';
			}
			// 获得role主键

			element += '<li><a href="javascipt:void(0);" onclick="loadAuthList('
					+ totalPage + ')">末页</a></li>';
			element += '<li><span>转到</span><input type="text" style="width: 50px" name="pageNo" id="pageNo" onchange="confirOnchange(this.value,'
					+ totalPage + ')">页</li>';
			element += '</ul><h5>共' + totalPage + '页/第' + currentPage
					+ '页</h5></td></tr>';
			element += '</tfoot></table></div></form>';
			$("#main").append(element);
		},
		error : function(info) {
			alert("error" + info);
		}
	});
}

/**
 * 输入数字跳转页
 * 
 * @param {}
 *            val
 * @param {}
 *            totalPage
 */
function confirOnchange(val, totalPage) {
	if (val > totalPage || val < 1) {
		alert("请输入1至" + totalPage + "之间的数字！");
	} else {
		loadRoleList(val);
	}
}

$(document).ready(function() {
	$('#roleList_table').datagrid({
		title : '角色信息列表',
		iconCls : 'icon-edit',// 图标
		fit : true, // 自动大小
		autoRowHeight : false,
		pagination : true,
		border : false,
		loadMsg : '数据加载中，请稍候……',
		pageSize : 10,
		collapsible : true, // 是否可折叠的
		idField : 'roleId', // id字段,加上以区分
		fitColumns : true, // 列自适应，影响性能
		rownumbers : true,// 行号
		url : 'loadRoleList.do',
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'roleName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'roleName',
							colspan : 2
						});
				$('#roleList_table').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				// title功能提示
				$('#roleList_table').datagrid('doCellTip', {
							cls : {
								'background-color' : 'pink'
							},
							delay : 100
						});
				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#roleList_table').datagrid('hideColumn', 'ck');
				} else {
					$('#roleList_table').datagrid('showColumn', 'ck');
				}
			}
		},
		/*---------初始化表格工具栏，用于鼠标在正整行任意处选中对象----------*/
		toolbar : '#userList_pager',
		columns : [[{
					field : 'ck',
					title : '',
					checkbox : true
				}, {
					field : 'roleName',
					title : '角色名',
					width : 100,
					align : "left"
				}, {
					field : 'roleType',
					title : '角色类型',
					width : 100,
					align : "left",
					formatter : function(value, rec) {
						if (rec.roleType == 1) {
							return '超级角色';
						} else if (rec.roleType == 2) {
							return '普通角色';
						}
					}
				}]]
	});
	$('#roleList_table').datagrid('getPager')// 分页布局选项
			.pagination({ // 分页布局定义(需要哪些元素)
				layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next',
						'last', 'sep']
			});
});

function userList_search() {
	var map = {
		"roleName" : $("#roleName").val()
	}
	$("#roleList_table").datagrid('load', {
				params : map
			});
}

/**
 * @author Hehaipeng
 * @date 2017年3月28日
 * @todo TODO 加载role列表
 */
$(document).ready(function() {

	$("#addRole").click(function() {
				window.location.href = "toEditRole.do";
			});

	$("#addAuth").click(function() {
		var row = $('#roleList_table').datagrid("getSelected");
		var rows = $('#roleList_table').datagrid('getSelections'); // 选多行
		if (row == null || rows.length < 1 || rows[0].roleName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			if(rows.length >1){
				top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
			}else{
				window.location.href = "addAuthForRole.do?roleId=" +row.roleId;
			}
		}
	});

	$("#deleteRole").click(function() {
		var row = $('#roleList_table').datagrid("getSelected");
		var rows = $('#roleList_table').datagrid('getSelections'); // 选多行
		if (row == null || rows.length < 1 || rows[0].roleName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			var roleIds = new Array();
			if (rows.length > 1) {
				for (var index = 0; index < rows.length; index++) {
					roleIds[index] = rows[index].roleId;
				}
			} else {
				roleIds[0] = row.roleId;
			}
		}
		var confirm = function() {
			$.ajax({
						url : 'deleteRole.do?roleIds=' + roleIds, // 这种形式传入的为字符串形式
						dataType : 'JSON',
						type : 'POST',
						success : function(json) {
							if (json.reflag == true) {
								top.window.showMsg("成功信息", json.infoMsg);
								// 重新加载页面
								$('#roleList_table').datagrid('reload');
								// setTimeout("window.location.href='toBuildingList.do';",1500);
							} else {
								top.window.alertMsg("错误信息", json.infoMsg,
										"warning");
							}
						},
						error : function(data) {
							top.window.alertMsg("错误信息", "执行失败！" + data,
									"warning");
						}
					});

		}
		top.window.confirmMsg('信息确认', "确定删除所选产品？", confirm);
	});
});

/**
 * 加载role列表
 */
function loadRoleList(pageNo) {
	// 往map中填值（在javascript语言中，key的值只能是字符串，不能是其它的。）
	// 或者map.key3='12';
	var map = {
		roleName : $("#roleName").val()
	};

	$.ajax({
		url : ".do",
		data : {
			params : map,
			orderColumn : 'ROLE_NAME', // 要排序的列
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
			element += '<form name="roleList_form" method="post">';
			element += '<div id="index">角色名:<input type="text" name="roleName" id="roleName" />';
			element += '<input type="button" name="Submit" value="查询" onclick="loadRoleList(null);" /></div>';
			element += '<div id="table"><table id="ec_table"><caption>角色信息表</caption>';
			element += '<thead><tr><td>全选/全不选<input type="checkbox" id="roleAllCheckbox" onclick="checkAllCheckbox();"</td>'
					+ '<td>角色名</td><td>修改</td></tr></thead>';

			element += '<tbody>';
			for (var index = 0; index < resultList.length; index++) {
				element += '<tr class="lightdown" onmousemove="this.className=\'lightup\';"onmouseout="this.className=\'lightdown\';">';
				element += '<td><input type="checkbox" name="roleCheckbox" id="roleCheckbox'
						+ index
						+ '" onclick="selChk(frm, chkVal, this.id)"></td>';
				element += '<td>' + resultList[index].roleName + '</td>';
				element += '<td><a href="/user/toEditRole.do">编辑</a></td></tr>';
			}
			element += '</tbody>';
			element += '<tfoot><tr><td colspan="3"><h5>共搜素到' + totalSize
					+ '条信息</h5><ul>';
			element += '<li><a href="javascipt:void(0);" onclick="loadRoleList(1)" class="style1">首页</a></li>';

			if (currentPage > 1) {
				element += '<li><a href="javascipt:void(0);" onclick="loadRoleList('
						+ (currentPage - 1) + ')" >上一页</a></li>';
			} else {
				element += '<li><a href="javascipt:void(0);" >上一页</a></li>';
			}

			if (currentPage < totalPage) {
				element += '<li><a href="javascipt:void(0);" onclick="loadRoleList('
						+ (currentPage + 1) + ')">下一页</a></li>';
			} else {
				element += '<li><a href="javascipt:void(0);" >下一页</a></li>';
			}
			// 获得role主键

			element += '<li><a href="javascipt:void(0);" onclick="loadRoleList('
					+ totalPage + ')">末页</a></li>';
			element += '<li><span>转到</span><input type="text" style="width: 50px" name="pageNo" id="pageNo" onchange="confirOnchange(this.value,'
					+ totalPage + ')">页</li>';
			element += '</ul><h5>共' + totalPage + '页/第' + currentPage
					+ '页</h5></td></tr>';
			element += '<tr><td colspan="3"><ul>';
			element += '<li><input name="addRoles" type="button" value="添加角色" onclick="window.location.href=\'toEditRole.do\'" class="button"'
					+ 'onmouseout="this.className=\'button\';" onmouseover="this.className=\'buttondown\';" /></li>';
			element += '<li><input name="delete" type="button" value="删除角色" class="button"'
					+ 'onmouseout="this.className=\'button\';" onmouseover="this.className=\'buttondown\';"'
					+ ' onclick="deleteRoles(' + roleIds + ')" /></li>';
			element += '</ul></td></tr></tfoot></table></div></form>';
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

/**
 * 删除角色
 * 
 * @param {}
 *            val
 */
function deleteRoles(val) {
	var confirm = function() {
		$.ajax({
					url : 'deleteRole.do',
					data : {
						roleIds : val
					},
					dataType : 'json',
					type : 'POST',
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							$('#biddingList').datagrid('reload');
						} else {
							top.window
									.alertMsg("错误信息", json.infoMsg, "warning");
						}
					},
					error : function(data) {
						top.window.alertMsg("错误信息", "执行失败！" + data, "warning");
					}
				});
	};
	top.window.confirmMsg('信息确认', "确定删除该角色？", confirm);
}

function checkAllCheckbox() {
	// $("input[type='checkbox']").is(':checked')
	if ($(this).is(":checked")) {
		$("input[name=roleCheckbox]").each(function() {
					$(this).attr("checked", false);
				});
	} else {
		$("input[name=roleCheckbox]").each(function() {
					$(this).attr("checked", true);
				});
	}
}

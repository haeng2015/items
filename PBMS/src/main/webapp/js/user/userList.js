/**
 * @author Hehaipeng
 * @date 2017年3月28日
 * @todo TODO 加载user表格数据(运用拼接方式展现数据)
 */
$(document).ready(function() {
	$('#userList_table').datagrid({
		title : '用户信息列表',
		iconCls : 'icon-edit',// 图标
		fit : true, // 自动大小
		autoRowHeight : true,
		pagination : true,
		border : false,
		loadMsg : '数据加载中，请稍候……',
		pageSize : 10,
		collapsible : true, // 是否可折叠的
		idField : 'userId', // id字段,加上以区分
		fitColumns : true, // 列自适应，影响性能
		// rownumbers : true,// 行号
		url : 'loadUserList.do',
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'userName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'userName',
							colspan : 7
						});
				$('#userList_table').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				// title功能提示
				// $('#userList_table').datagrid('doCellTip', {
				// cls : {
				// 'background-color' : 'pink'
				// },
				// delay : 100
				// });

				// 增加tooltip效果，将图放大(此处不能通过id取，否则只能放大一张照片，只能通过class取值)
				$('.userImg').tooltip({
					content : function() {
						return "<div>"
								+ "<img style='width:250px;height:200px;margin:5px;border:1px solid silver;' src='"
								+ this.src + "'>" + "</p>" + "</div>";
					},
					onShow : function() {
						$(this).tooltip('tip').css({
									backgroundColor : '#666',
									borderColor : '#666'
								});
					},
					position : 'left'
				});

				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#userList_table').datagrid('hideColumn', 'ck');
				} else {
					$('#userList_table').datagrid('showColumn', 'ck');
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
					field : 'userName',
					title : '用户名',
					width : 80,
					align : "left"
				}, {
					field : 'roleName',
					title : '角色',
					width : 70,
					align : "left",
					formatter : function(value, rec) {
						if (value) {
							return value;
						} else {
							return "<font color='red'>未分配角色</font>";
						}
					}
				}, {
					field : 'userSex',
					title : '性别',
					width : 40,
					align : "left",
					formatter : function(value, rec) {
						if (rec.userSex == 1) {
							return '男';
						} else if (rec.userSex == 2) {
							return '女';
						}
					}
				}, {
					field : 'userType',
					title : '用户类型',
					width : 70,
					align : "left",
					formatter : function(value, rec) {
						if (rec.userType == 1) {
							return '超级管理员';
						} else if (rec.userType == 2) {
							return '普通管理员';
						}
					}
				}, {
					field : 'userPhone',
					title : '电话',
					width : 70,
					align : "left"
				}, {
					field : 'userMsn',
					title : '用户MSN',
					width : 80,
					align : "left"
				}, {
					field : 'userQq',
					title : '用户QQ',
					width : 50,
					align : "left"
				}, {
					field : 'userPhoto',
					title : '用户图片',
					width : 50,
					align : "left",
					formatter : function(value, index) {
						if (value) {
							// return "<font title=" + value + " >"+value;
							return "<img class='userImg' style='width:96px;height:54px;' src='../" + value + "' />";
						} else {
							return "<img id='userImg' style='width:96px;height:54px;' src='../images/defaul.jpg' />";
						}
					}
				}]]
	});
	$('#userList_table').datagrid('getPager')// 分页布局选项
			.pagination({ // 分页布局定义(需要哪些元素)
				layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next',
						'last', 'sep']
			});
});

function userList_search() {
	var map = {
		"userName" : $("#userName").val()
	}
	$("#userList_table").datagrid('load', {
				params : map
			});
}

$(function() {
			$("#addUser_button").click(function() {
						window.location.href = "editUser.do";
					});
			$("#deleteUser_button").click(function() {
				var row = $('#userList_table').datagrid("getSelected");
				var rows = $('#userList_table').datagrid('getSelections'); // 选多行
				if (row == null || rows.length < 1
						|| rows[0].userName.indexOf("没有相关记录") > 0) {
					top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
				} else {
					var userIds = new Array();
					if (rows.length > 1) {
						for (var index = 0; index < rows.length; index++) {
							userIds[index] = rows[index].userId;
						}
					} else {
						userIds[0] = row.userId;
					}
					var confirm = function() {
						$.ajax({
									url : 'deleteUser.do?userIds=' + userIds, // 这种形式传入的为字符串形式
									// data : {
									// buildIds : buildIds
									// },
									dataType : 'JSON',
									type : 'POST',
									success : function(json) {
										if (json.reflag == true) {
											top.window.showMsg("成功信息",
													json.infoMsg);
											// 重新加载页面
											$('#userList_table').datagrid('load');
										} else {
											top.window.alertMsg("温馨提示",
													json.infoMsg, "warning");
										}
									},
									error : function(data) {
										top.window.alertMsg("错误信息", "执行失败！"
														+ data, "warning");
									}
								});

					}
					top.window.confirmMsg('信息确认', "确定删除所选产品？", confirm);
				}
			});
		});

function editUserInfo(val) {
	var row = $('#userList_table').datagrid("getSelected");
	var rows = $('#userList_table').datagrid('getSelections'); // 选多行
	if (row == null || rows.length < 1
			|| rows[0].userName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
	} else {
		if (rows.length != 1) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			if (val == 1) {
				window.location.href = "editUser.do?mark=1&userId="
						+ row.userId;
			} else {
				window.location.href = "editUser.do?mark=2&userId="
						+ row.userId;
			}
		}
	}
}

/**
 * 编辑用户角色
 */
function editUserRole() {
	var row = $('#userList_table').datagrid("getSelected");
	var rows = $('#userList_table').datagrid('getSelections'); // 选多行
	if (row == null || rows.length < 1
			|| rows[0].userName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
	} else {
		if (rows.length != 1) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			// top.window.openDialog("editUserRole", 300, 450, "编辑用户角色", false,
			// "editUserRole.do?userId=" + row.userId, false);
			window.location.href = "editUserRole.do?userId=" + row.userId;
		}
	}
}

function viewPhoto(val) {

	var pho = document.getElementById("photo");
	var pic = document.getElementById("pic");
	{
		var masker = document.getElementById("mask");

		masker.style.left = e.clientX - 25 + "px";
		masker.style.top = e.clientY - 25 + "px";
		pho.style.backgroundPosition = ((50 - e.clientX * 2) + "px "
				+ (50 - e.clientY * 2) + "px");
	}
}
$(function() {
	/**
	 * 初始化树形表格
	 */
	$("#deviceListEasyUI").treegrid({
		fit : true,
		pagination : true,
		loadMsg : '数据加载中，请稍候……',
		method : 'post',
		collapsible : true,
		singleSelect : false,
		treeField : 'userName', // 树节点标示列
		lines : true, // 加树形条
		collapsible : true,// 是否可折叠
		idField : 'userDeviceId', // id字段,加上以区分
		fitColumns : true,// 列自适应，影响性能
		onLoadSuccess : function(row, param) {
			if (!row) {
				if (param.total == 0) {
					// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
					$(this).treegrid('appendRow', {
						'userName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
					}).treegrid('mergeCells', {
								index : 0,
								field : 'userName',
								colspan : 7
							});
					$('#deviceListEasyUI').treegrid('hideColumn', 'ck');
					// 隐藏分页导航条，这个需要熟悉treegrid的html结构，直接用jquery操作DOM对象，easyui
					// treegrid没有提供相关方法隐藏导航条
					$(this).closest('div.treegrid-wrap')
							.find('div.treegrid-pager').hide();
				} else {
					// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
					$(this).closest('div.treegrid-wrap')
							.find('div.treegrid-pager').show();
					if ($("#flag").val() == "3") { // 已取消
						$('#deviceListEasyUI').treegrid('hideColumn', 'ck');
					} else {
						$('#deviceListEasyUI').treegrid('showColumn', 'ck');
					}
				}
			}
		},
		columns : [[{
					field : 'ck',
					title : '',
					checkbox : true
				}, {
					field : 'userName',
					title : '用户名称',
					width : 100,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}, {
					field : 'deviceName',
					title : '设备名称',
					width : 100,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}, {
					field : 'deviceId',
					title : '设备ID',
					width : 70,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}, {
					field : 'deviceNum',
					title : '设备编号',
					width : 70,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}, {
					field : 'createDate',
					title : '创建时间',
					width : 70,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}, {
					field : 'updateDate',
					title : '更新时间',
					width : 70,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}, {
					field : 'devicePlace',
					title : '设备位置',
					width : 150,
					formatter : function(value, rec) {
						return '<p title=' + value + '>' + value + '</p>';
					}
				}]],
		url : '../device/loadDeviceList.do'
	});
	$('#deviceListEasyUI').treegrid('getPager').pagination({ // 分页布局定义(需要哪些元素)
		layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next', 'last',
				'sep']
	});
});

/**
 * @author Hehaipeng
 * @date 2017年3月10日
 * @todo TODO条件查询
 */
function search_device() {

	var deviceId = $("#deviceId").val();
	var userName = $("#userName").val();
	var deviceName = $("#deviceName").val();

	$("#deviceListEasyUI").treegrid('load', {
				'deviceName' : deviceName,
				'userName' : userName,
				'deviceId' : deviceId
			});
}

/**
 * 新增、删除或修改
 * 
 * @param tag
 */
function deviceInfoList(tag) {
	var row = $('#deviceListEasyUI').treegrid("getSelected");
	var rows = $('#deviceListEasyUI').treegrid('getSelections'); // 选多行

	// 新增
	if (tag == 0) {
		if (row == null || rows.length < 1
				|| rows[0].userName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选择用户！', 'warning');
			return;
		}

		if (rows.length != 1) {
			top.window.alertMsg('温馨提示', '请选择一个用户！', 'warning');
		} else {
			if (row.userName == "") {
				top.window.alertMsg('温馨提示', '请选择用户！', 'warning');
				return;
			}
			window.location.href = "../device/toDeviceEdit.do?userName="
					+ row.userName;
		}
	}

	// 修改
	if (tag == 1) {
		if (row == null || rows.length < 1
				|| rows[0].userName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			if (rows.length != 1) {
				top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
			} else {
				window.location.href = "../device/toDeviceEdit.do?userDeviceId="
						+ row.userDeviceId;
			}
		}
	}

	// 删除
	if (tag == 2) {
		if (row == null || rows.length < 1
				|| rows[0].userName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			var userDeviceIds = new Array();
			if (rows.length != 1) {
				for (var int = 0; int < rows.length; int++) {
					userDeviceIds[int] = rows[int].userDeviceId;
				}

			} else {
				userDeviceIds[0] = row.userDeviceId;
			}

			var confirm = function() {
				// 调用父窗口的滚动条
				top.window.openProgressBar();
				$.ajax({
							url : "../device/deleteDevice.do",
							data : "userDeviceIds=" + userDeviceIds,
							dataType : 'json',
							type : 'POST',
							success : function(json) {
								// 关闭父窗口的滚动条
								top.window.closeProgressBar();
								if (json.reflag == true) {
									top.window.showMsg("成功信息", json.infoMsg);
									$('#deviceListEasyUI').treegrid('reload');
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
			};
			if (rows.length != 1) {
				top.window.confirmMsg('信息确认', "确定删除所选设备？", confirm);
			} else {
				if (row.userName == '') {
					top.window.confirmMsg('信息确认', "确定删除设备\"ID:" + row.deviceId
									+ "\"？", confirm);
				} else {
					top.window.confirmMsg('信息确认', "此操作将会删除用户\"" + row.userName
									+ "\"下的所有设备，请谨慎操作，确定删除？", confirm);
				}
			}
		}
	}

}

/**
 * 为设备推送资源
 */
function pushResourceToDevice() {
	var row = $('#deviceListEasyUI').treegrid("getSelected");
	var rows = $('#deviceListEasyUI').treegrid('getSelections'); // 选多行

	if (row == null || rows.length < 1
			|| rows[0].userName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请至少选择一个设备！', 'warning');
		return
	}

	var resourceId = $("#resourceId").val();
	var userDeviceIds = new Array();

	if (rows.length > 1) {
		for (var int = 0; int < rows.length; int++) {
			userDeviceIds[int] = rows[int].userDeviceId;
		}
	} else {
		userDeviceIds[0] = row.userDeviceId;
	}

	$.ajax({
				url : "../device/pushResourceToDevice.do",
				data : "userDeviceIds=" + userDeviceIds + "&resourceId="
						+ resourceId,
				dataType : 'json',
				type : 'POST',
				success : function(json) {
					// 关闭父窗口的滚动条
					top.window.closeProgressBar();
					if (json.reflag == true) {
						top.window.showMsg("成功信息", json.infoMsg);
						$('#deviceListEasyUI').treegrid('load');
					} else {
						top.window.alertMsg("错误信息", json.infoMsg, "warning");
					}
				},
				error : function(data) {
					top.window.alertMsg("错误信息", "执行失败！" + data, "warning");
				}
			});

}

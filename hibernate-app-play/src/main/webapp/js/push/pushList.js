$(function() {
	/**
	 * 初始化树形表格
	 */
	$("#pushListEasyUI").datagrid({
		fit : true,
		pagination : true,
		loadMsg : '数据加载中，请稍候……',
		method : 'post',
		collapsible : true,
		singleSelect : false,
		// 带参数到后台，条件查询
		queryParams : {
			mark : '1'
		},
		idField : 'userDeviceId', // id字段,加上以区分
		fitColumns : true,// 列自适应，影响性能
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'userName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'userName',
							colspan : 8
						});
				$('#pushListEasyUI').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				$('#pushListEasyUI').datagrid('doCellTip', {
							cls : {
								'background-color' : 'pink'
							},
							delay : 100
						});
				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#pushListEasyUI').datagrid('hideColumn', 'ck');
				} else {
					$('#pushListEasyUI').datagrid('showColumn', 'ck');
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
					width : 80
				}, {
					field : 'deviceNum',
					title : '设备编号',
					width : 60
				}, {
					field : 'deviceName',
					title : '设备名称',
					width : 80
				}, {
					field : 'deviceId',
					title : '设备ID',
					width : 70
				}, {
					field : 'devicePlace',
					title : '设备位置',
					width : 90
				}, {
					field : 'fileName',
					title : '文件名称',
					width : 70
				}, {
					field : 'type',
					title : '文件类型',
					width : 60,
					formatter : function(value, rec) {
						if (rec.type == '1') {
							return '<font color="blue">H5</font>';
						} else if (rec.type == '2') {
							return '<font color="pink">视频</font>';
						}
					}
				}, {
					field : 'fileSize',
					title : '文件大小',
					width : 60
				}]],
		url : '../device/loadDeviceList.do'
	});
	$('#pushListEasyUI').datagrid('getPager').pagination({ // 分页布局定义(需要哪些元素)
		layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next', 'last',
				'sep']
	});
});

/**
 * @author Hehaipeng
 * @date 2017年3月10日
 * @todo TODO条件查询
 */
function search_push() {

	var deviceId = $("#deviceId").val();
	var userName = $("#userName").val();
	var deviceName = $("#deviceName").val();

	$("#pushListEasyUI").datagrid('load', {
				'mark' : '1',
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
function pushInfoList(tag) {
	var row = $('#pushListEasyUI').datagrid("getSelected");
	var rows = $('#pushListEasyUI').datagrid('getSelections'); // 选多行

	if (row == null || rows.length < 1
			|| rows[0].userName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		return;
	}

	var userDeviceIds = new Array();
	if (rows.length > 1) {
		for (var int = 0; int < rows.length; int++) {
			userDeviceIds[int] = rows[int].userDeviceId;
		}
	} else {
		userDeviceIds[0] = row.userDeviceId;
	}

	// 修改
	if (tag == 1) {

		window.location.href = "../resource/toResourceList.do?userDeviceIds="
				+ userDeviceIds
	}

	// 删除
	if (tag == 2) {

		var confirm = function() {
			// 调用父窗口的滚动条
			top.window.openProgressBar();
			$.ajax({
						url : "../device/updateResourceToDevice.do",
						data : "mark=2&userDeviceIds=" + userDeviceIds,
						dataType : 'json',
						type : 'POST',
						success : function(json) {
							// 关闭父窗口的滚动条
							top.window.closeProgressBar();
							if (json.reflag == true) {
								top.window.showMsg("成功信息", json.infoMsg);
								$('#pushListEasyUI').datagrid('reload');
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

		top.window.confirmMsg('信息确认', "确定删除所选设备推送的资源文件？", confirm);
	}

}

/**
 * 异步文件下载
 */
function downloadResource() {

	$.ajaxFileUpload({
				url : "../device/downloadResourceByDeviceId.do",
				data : {
					'deviceId' : 'huig798'
				},
				dataType : 'json',
				secureuri : false,
				cache : false,
				type : 'POST',
				async : true, // 是否使用异步发送
				success : function(json) { // json.returnObj.type=1 H5
					// 关闭父窗口的滚动条
					top.window.closeProgressBar();
					if (json.reflag == false) {
						top.window.alertMsg("错误信息", json.infoMsg, "warning");
					}
				},
				error : function(data) {
					top.window.alertMsg("错误信息", "执行失败！" + data, "warning");
				}
			});

}

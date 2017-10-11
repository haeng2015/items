$(function() {
	/**
	 * 初始化树形表格
	 */
	$("#resourceListEasyUI").datagrid({
		fit : true,
		pagination : true,
		loadMsg : '数据加载中，请稍候……',
		method : 'post',
		collapsible : true,
		idField : 'resourceId', // id字段,加上以区分
		fitColumns : true,// 列自适应，影响性能
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'fileName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'fileName',
							colspan : 5
						});
				$('#resourceListEasyUI').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				$('#resourceListEasyUI').datagrid('doCellTip', {
							cls : {
								'background-color' : 'pink'
							},
							delay : 100
						});
				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#resourceListEasyUI').datagrid('hideColumn', 'ck');
				} else {
					$('#resourceListEasyUI').datagrid('showColumn', 'ck');
				}
			}
		},
		columns : [[{
					field : 'ck',
					title : '',
					checkbox : true
				}, {
					field : 'uniqueName',
					hidden : true
				}, {
					field : 'fileName',
					title : '文件名称',
					sortable : true,
					width : 100
				}, {
					field : 'type',
					title : '文件类型',
					width : 80,
					sortable : true,
					formatter : function(value, rec) {
						if (rec.type == '1') {
							return '<font color="blue">H5</font>';
						} else if (rec.type == '2') {
							return '<font color="pink">视频</font>';
						}
					}
				}, {
					field : 'createDate',
					title : '上传日期',
					sortable : true,
					width : 70
				}, {
					field : 'updateDate',
					title : '更新日期',
					sortable : true,
					width : 70
				}, {
					field : 'fileSize',
					title : '文件大小',
					sortable : true,
					width : 60
				}]],
		url : 'loadResourceList.do'
	});
	$('#resourceListEasyUI').datagrid('getPager').pagination({ // 分页布局定义(需要哪些元素)
		layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next', 'last',
				'sep']
	});
});

/**
 * @author Hehaipeng
 * @date 2017年3月10日
 * @todo TODO条件查询
 */
function search_resource() {
	var searchBeginTime = $('#searchBeginTime').datebox('getValue');
	var searchEndTime = $('#searchEndTime').datebox('getValue');
	var type = $("#type").val();
	var fileName = $("#fileName").val();

	$("#resourceListEasyUI").datagrid('load', {
				'searchBeginTime' : searchBeginTime,
				'searchEndTime' : searchEndTime,
				'type' : type,
				'fileName' : fileName
			});
}

/**
 * 新增、删除或修改
 * 
 * @param tag
 */
function resourceInfoList(tag) {
	var row = $('#resourceListEasyUI').datagrid("getSelected");
	var rows = $('#resourceListEasyUI').datagrid('getSelections'); // 选多行

	// 新增
	if (tag == 0) {
		window.location.href = "toResourceEdit.do";
	}

	// 修改
	if (tag == 1) {
		if (row == null || rows.length < 1
				|| rows[0].fileName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			if (rows.length != 1) {
				top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
			} else {
				window.location.href = "toResourceEdit.do?resourceId="
						+ row.resourceId;
			}
		}
	}

	// 删除
	if (tag == 2) {
		if (row == null || rows.length < 1
				|| rows[0].fileName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			var resourceIds = new Array();
			if (rows.length != 1) {
				for (var int = 0; int < rows.length; int++) {
					resourceIds[int] = rows[int].resourceId;
				}

			} else {
				resourceIds[0] = row.resourceId;
			}

			var confirm = function() {
				// 调用父窗口的滚动条
				top.window.openProgressBar();
				$.ajax({
							url : "deleteResource.do",
							data : "resourceIds=" + resourceIds,
							dataType : 'json',
							type : 'POST',
							success : function(json) {
								// 关闭父窗口的滚动条
								top.window.closeProgressBar();
								if (json.reflag == true) {
									top.window.showMsg("成功信息", json.infoMsg);
									$('#resourceListEasyUI').datagrid('reload');
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
			top.window.confirmMsg('信息确认', "确定删除所选产品？", confirm);
		}
	}

	// 下载
	if (tag == 3) {
		if (row == null || rows.length < 1
				|| rows[0].fileName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			if (rows.length != 1) {
				top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
			} else {
				window.location.href = "downloadResourceByName.do?uniqueName="
						+ row.uniqueName;
			}
		}
	}

	// 推送
	if (tag == 4) {
		if (row == null || rows.length < 1
				|| rows[0].fileName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请至少选择一个资源！', 'warning');
			return;
		}
		if (rows.length != 1) {
			top.window.alertMsg('温馨提示', '请选择一个资源推送！', 'warning');
		} else {
			window.location.href = "pushResourceToDevice.do?resourceId="
					+ row.resourceId;
		}
	}
}

/**
 * 推送资源到设备
 */
function pushResourceToDevice() {
	var row = $('#resourceListEasyUI').datagrid("getSelected");
	var rows = $('#resourceListEasyUI').datagrid('getSelections'); // 选多行

	if (row == null || rows.length < 1
			|| rows[0].fileName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选择一个资源进行推送！', 'warning');
		return;
	}
	if (rows.length != 1) {
		top.window.alertMsg('温馨提示', '请选择一个资源进行推送！', 'warning');
	} else {

		$.ajax({
			url : "../device/updateResourceToDevice.do",
			data : "mark=3&userDeviceIdStrs=" + $("#userDeviceIdStrs").val()
					+ "&resourceId=" + row.resourceId,
			dataType : 'json',
			type : 'POST',
			success : function(json) {
				// 关闭父窗口的滚动条
				top.window.closeProgressBar();
				if (json.reflag == true) {
					top.window.showMsg("成功信息", json.infoMsg);
					setTimeout(
							'window.location.href = "../device/toDeviceList.do?mark=1',
							700);
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
}

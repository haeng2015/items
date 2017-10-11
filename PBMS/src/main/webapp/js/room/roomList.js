/**
 * @author Hehaipeng
 * @date 2017年4月1日
 * @todo TODO
 */

$(document).ready(function() {
	/*----------初始化表格参数-----------------*/
	$('#roomList_table').datagrid({
		title : '房间信息列表',
		iconCls : 'icon-edit',// 图标
		fit : true, // 自动大小
		autoRowHeight : false,
		pagination : true,
		border : false,
		loadMsg : '数据加载中，请稍候……',
		pageSize : 10,
		sortable : true,  //列排序
		collapsible : true, // 是否可折叠的
		idField : 'roomId', // id字段,加上以区分
		fitColumns : true, // 列自适应，影响性能
		rownumbers : true,// 行号
		url : 'loadRoomingList.do',
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'roomName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'roomName',
							colspan : 7
						});
				$('#roomList_table').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				// title功能提示
				$('#roomList_table').datagrid('doCellTip', {
							cls : {
								'background-color' : 'pink'
							},
							delay : 100
						});
				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#roomList_table').datagrid('hideColumn', 'ck');
				} else {
					$('#roomList_table').datagrid('showColumn', 'ck');
				}
			}
		},
		/*---------初始化表格工具栏，用于鼠标在正整行任意处选中对象----------*/
		toolbar : '#roomList_table_TypedatagridToolbar',
		columns : [[{
					field : 'ck',
					title : '',
					checkbox : true
				}, {
					field : 'buildId',
					title : '楼栋id',
					hidden : true
				}, {
					field : 'buildName',
					title : '楼栋名称',
					width : 100,
					align : "left"
				}, {
					field : 'roomName',
					title : '房间名称',
					width : 100,
					align : "left"
				}, {
					field : 'ownerId',
					title : '业主id',
					hidden : true
				}, {
					field : 'ownerName',
					title : '居住者',
					width : 80,
					align : "left"
				}, {
					field : 'roomStartDate',
					title : '入住时间',
					width : 80,
					align : "left"
				}, {
					field : 'roomEndDate',
					title : '到期时间',
					width : 80,
					align : "left"
				}, {
					field : 'roomType',
					title : '房型',
					width : 80,
					align : "left"
				}, {
					field : 'roomUse',
					title : '用途',
					width : 80,
					align : "left"
				}, {
					field : 'roomArea',
					title : '建筑面积',
					width : 50,
					align : "left"
				}, {
					field : 'roomProb',
					title : '得房率',
					width : 50,
					align : "left"
					// formatter : function(value, rec) {
				// if (rec.issueState == 1) {
				// return '未发布';
				// } else if (rec.issueState == 2) {
				// return '已发布';
				// } else if (rec.issueState == 3) {
				// return '已关闭';
				// }
				// }
			}]]
	});
	$('#roomList_table').datagrid('getPager')// 分页布局选项
			.pagination({ // 分页布局定义(需要哪些元素)
				layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next',
						'last', 'sep']
			});

	/**
	 * 删除楼栋
	 */
	$("#deleteRoom").click(function() {
		var row = $('#roomList_table').datagrid("getSelected");
		var rows = $('#roomList_table').datagrid('getSelections'); // 选多行
		if (row == null || rows.length < 1
				|| rows[0].roomName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			var roomIdArr = new Array();
			var roomIds = new Array(); // 移除空值后的id数组
			// 如果该房间有楼栋、业主则不能删除
			if (rows.length > 1) {
				for (var index = 0; index < rows.length; index++) {
					if (rows[index].buildId == "" || rows[index].buildId == 0) {
						if (rows[index].ownerId == "" || rows[index].ownerId == 0) {
							roomIdArr[index] = rows[index].roomId;
						}
					}
				}

				// 需要移除roomIdArr中的空值
				for (var index = 0; index < roomIdArr.length; index++) {
					if (roomIdArr[index] != null && roomIdArr[index] != "") {
						roomIds[roomIds.length] = roomIdArr[index];
					}
				}

				// 再比较长度
				if (roomIds.length == rows.length) {
					ajaxRequestForDelete(roomIds);
				}else{
					top.window.alertMsg("错误信息", "请选择没有业主和楼栋的房间删除!", "warning");
				}

			} else {
				if (row.buildId == "" || row.buildId == 0) {
					if (row.ownerId == "" || row.ownerId == 0) {
						roomIds[0] = row.roomId;
						ajaxRequestForDelete(roomIds);
					} else {
						top.window.alertMsg("错误信息", "该房间有业主不能删除!", "warning");
					}
				} else {
					top.window.alertMsg("错误信息", "请先移除所属楼栋!", "warning");
				}
			}

		}
	});

});

/**
 * @author Hehaipeng
 * @date 2017年3月10日
 * @todo TODO 条件查询
 */
function roomList_search() {
	var optionsVal = $("#getOneway option:selected").val(); // 获取选中的项
	if (optionsVal == 1) {
		var buildName = $("#selectRoomSearch").val();
	} else if (optionsVal == 2) {
		var roomName = $("#selectRoomSearch").val();
	} else if (optionsVal == 3) {
		var roomType = $("#selectRoomSearch").val();
	} else if (optionsVal == 4) {
		var roomUse = $("#selectRoomSearch").val();
	} else {
		var roomStartDate = $("#roomStartDate").datebox('getValue');
		var roomEndDate = $("#roomEndDate").datebox('getValue');
	}
	var map = {
		'buildName' : buildName,
		'roomName' : roomName,
		'roomType' : roomType,
		'roomUse' : roomUse,
		'roomStartDate' : roomStartDate,
		'roomEndDate' : roomEndDate
	};

	$("#roomList_table").datagrid('load', {
				params : map
			});
}

function ajaxRequestForDelete(roomIds) {
	var confirm = function() {
		$.ajax({
					url : 'deleteRoom.do', // 这种形式传入的为字符串形式
					data : 'roomIds=' + roomIds,
					dataType : 'JSON',
					type : 'POST',
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 重新加载页面
							$('#roomList_table').datagrid('reload');
							// setTimeout("window.location.href='toBuildingList.do';",1500);
						} else {
							top.window
									.alertMsg("错误信息", json.infoMsg, "warning");
						}
					},
					error : function(data) {
						top.window.alertMsg("错误信息", "执行失败！" + data, "warning");
					}
				});

	}
	top.window.confirmMsg('信息确认', "确定删除所选产品？", confirm);
}

/**
 * //详细页进入修改页面
 */
function updateRoom_submit() {
	window.location.href = "editRoom.do?mark=2&roomId=" + $("#roomId").val();
}

/**
 * 查看详细1，修改2
 */
function roomList_button(val) {
	var row = $('#roomList_table').datagrid("getSelected");
	var rows = $('#roomList_table').datagrid('getSelections'); // 选多行
	if (row == null || rows.length < 1
			|| rows[0].roomName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
	} else {
		if (rows.length != 1) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			window.location.href = "editRoom.do?mark=" + val + "&roomId="
					+ row.roomId;
		}
	}
}

/**
 * @author Hehaipeng
 * @date 2017年3月15日
 * @todo TODO 文件下载
 * @param val
 */
function uploadMaterials(val) {
	var confirm = function() {
		window.location.href = "uploadAttachmentForRoom.do?id=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此附件?", confirm);
}

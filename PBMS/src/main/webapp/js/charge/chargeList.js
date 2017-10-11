/**
 * @author Hehaipeng
 * @date 2017年4月1日
 * @todo TODO
 */

$(document).ready(function() {
	/*----------初始化表格参数-----------------*/
	$('#chargeList_table').datagrid({
		title : '收费项目列表',
		iconCls : 'icon-edit',// 图标
		fit : true, // 自动大小
		autoRowHeight : false,
		pagination : true,
		border : false,
		loadMsg : '数据加载中，请稍候……',
		pageSize : 10,
		collapsible : true, // 是否可折叠的
		idField : 'chargeId', // id字段,加上以区分
		fitColumns : true, // 列自适应，影响性能
		rownumbers : true,// 行号
		url : 'loadChargeList.do',
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'chargeName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'chargeName',
							colspan : 2
						});
				$('#chargeList_table').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				// title功能提示
				$('#chargeList_table').datagrid('doCellTip', {
							cls : {
								'background-color' : 'pink'
							},
							delay : 100
						});
				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#chargeList_table').datagrid('hideColumn', 'ck');
				} else {
					$('#chargeList_table').datagrid('showColumn', 'ck');
				}
			}
		},
		/*---------初始化表格工具栏，用于鼠标在正整行任意处选中对象----------*/
		toolbar : '#chargeList_table_TypedatagridToolbar',
		columns : [[{
					field : 'ck',
					title : '',
					checkbox : true
				}, {
					field : 'chargeName',
					title : '姓名',
					width : 100,
					align : "left"
				}, {
					field : 'chargePrice',
					title : '价格(元)',
					width : 100,
					align : "left"
				}]]
	});
	$('#chargeList_table').datagrid('getPager')// 分页布局选项
			.pagination({ // 分页布局定义(需要哪些元素)
				layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next',
						'last', 'sep']
			});
});

/**
 * @author Hehaipeng
 * @date 2017年3月10日
 * @todo TODO 条件查询
 */
function chargeList_search() {
	var chargeName = $("#chargeName").val();
	var map = {
		'chargeName' : chargeName
	};
	$("#chargeList_table").datagrid('load', {
				params : map
			});
}

/**
 * 查看修改1，删除2
 */
function chargeList_button(val) {
	var row = $('#chargeList_table').datagrid("getSelected");
	var rows = $('#chargeList_table').datagrid('getSelections'); // 选多行
	if (row == null || rows.length < 1
			|| rows[0].chargeName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
	} else {
		if (val == 1) {
			if (rows.length != 1) {
				top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
			} else {
				window.location.href = "editCharge.do?chargeId=" + row.chargeId;
			}
		}

		if (val == 2) {
			var chargeIds = new Array();
			// 如果该房间有楼栋、业主则不能删除
			if (rows.length > 1) {
				for (var index = 0; index < rows.length; index++) {
					chargeIds[index] = rows[index].chargeId;
				}
				ajaxRequestForDelete(chargeIds);
			} else {
				chargeIds[0] = row.chargeId;
				ajaxRequestForDelete(chargeIds);
			}
		}

	}
}

function ajaxRequestForDelete(chargeIds) {
	var confirm = function() {
		$.ajax({
					url : 'deleteCharge.do', // 这种形式传入的为字符串形式
					data : 'chargeIds=' + chargeIds,
					dataType : 'JSON',
					type : 'POST',
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 重新加载页面
							$('#chargeList_table').datagrid('reload');
							// setTimeout("window.location.href='toBuildingList.do';",1500);
						} else {
							top.window.alertMsg("错误信息", json.infoMsg, "warning");
						}
					},
					error : function(data) {
						top.window.alertMsg("错误信息", "执行失败！" + data, "warning");
					}
				});

	}
	top.window.confirmMsg('信息确认', "确定删除所选产品？", confirm);
}

/**
 * @author Hehaipeng
 * @date 2017年4月1日
 * @todo TODO
 */

$(document).ready(function() {
	/*----------初始化表格参数-----------------*/
	$('#buildList_table').datagrid({
		title:'楼栋信息列表', 
        iconCls:'icon-edit',//图标 
		fit : true, //自动大小 
		autoRowHeight : false,
		pagination : true,
		border : false,
		loadMsg : '数据加载中，请稍候……',
		pageSize : 10,
		collapsible : true, //是否可折叠的 
		idField : 'buildId', // id字段,加上以区分
		fitColumns : true, // 列自适应，影响性能
		rownumbers:true,//行号 
		url : 'loadBuildingList.do',
		onLoadSuccess : function(data) {
			if (data.total == 0) {
				// 添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
				$(this).datagrid('appendRow', {
					'buildName' : '<div style="text-align:center;color:red">没有相关记录！</div>'
				}).datagrid('mergeCells', {
							index : 0,
							field : 'buildName',
							colspan : 4
						});
				$('#buildList_table').datagrid('hideColumn', 'ck');
				// 隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui
				// datagrid没有提供相关方法隐藏导航条
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.hide();
			} else {
				// title功能提示
				$('#buildList_table').datagrid('doCellTip', {
							cls : {
								'background-color' : 'pink'
							},
							delay : 100
						});
				// 如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
				$(this).closest('div.datagrid-wrap').find('div.datagrid-pager')
						.show();
				if ($("#flag").val() == "3") { // 已取消
					$('#buildList_table').datagrid('hideColumn', 'ck');
				} else {
					$('#buildList_table').datagrid('showColumn', 'ck');
				}
			}
		},
		/*---------初始化表格工具栏，用于鼠标在正整行任意处选中对象----------*/
		toolbar : '#buildList_table_TypedatagridToolbar',
		columns : [[{
					field : 'ck',
					title : '',
					checkbox : true
				}, {
					field : 'buildName',
					title : '楼栋名称',
					width : 100,
					align : "left"
				}, {
					field : 'buildStartDate',
					title : '开工时间',
					width : 80,
					align : "left"
				}, {
					field : 'buildEndDate',
					title : '竣工时间',
					width : 80,
					align : "left"
				}, {
					field : 'buildInfo',
					title : '楼栋信息',
					width : 150,
					align : "left"
				}, {
					field : 'buildArea',
					title : '建筑面积',
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
	$('#buildList_table').datagrid('getPager')// 分页布局选项
			.pagination({ // 分页布局定义(需要哪些元素)
				layout : ['first', 'prev', 'sep', 'manual', 'sep', 'next',
						'last', 'sep']
			});

	/**
	 * 删除楼栋
	 */
	$("#deleteBuild").click(function() {
		var row = $('#buildList_table').datagrid("getSelected");
		var rows = $('#buildList_table').datagrid('getSelections'); // 选多行
		if (row == null || rows.length < 1
				|| rows[0].buildName.indexOf("没有相关记录") > 0) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			var buildIds = new Array();
			if (rows.length > 1) {
				for (var index = 0; index < rows.length; index++) {
					buildIds[index] = rows[index].buildId;
				}
			} else {
				buildIds[0] = row.buildId;
			}
			// alert(typeof(buildIds[0]))
			var confirm = function() {
				$.ajax({
							url : 'deleteBuilding.do?buildIds=' + buildIds, // 这种形式传入的为字符串形式
							// data : {
							// buildIds : buildIds
							// },
							dataType : 'JSON',
							type : 'POST',
							success : function(json) {
								if (json.reflag == true) {
									top.window.showMsg("成功信息", json.infoMsg);
									// 重新加载页面
									$('#buildList_table').datagrid('reload');
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
		}
	});

});

/**
 * //详细页进入修改页面
 */
function updateBuild_submit() {
	window.location.href = "editBuilding.do?mark=2&buildId="
			+ $("#buildId").val();
}

/**
 * 查看详细1，修改2
 */
function buildList_button(val) {
	var row = $('#buildList_table').datagrid("getSelected");
	var rows = $('#buildList_table').datagrid('getSelections'); // 选多行
	if (row == null || rows.length < 1
			|| rows[0].buildName.indexOf("没有相关记录") > 0) {
		top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
	} else {
		if (rows.length != 1) {
			top.window.alertMsg('温馨提示', '请选中一行信息后再进行操作！', 'warning');
		} else {
			window.location.href = "editBuilding.do?mark=" + val + "&buildId="
					+ row.buildId;
		}
	}
}

/**
 * 当选择时间条件时，该变为时间插件
 */
function changeInputAttr() {
	var optionsVal = $("#getOneway option:selected").val(); // 获取选中的项

	if (optionsVal == 2 || optionsVal == 3) {
		// 为input标签移除属性type
		$("#selectBuildSearch").removeAttr("type");

		// 为input标签添加属性class和data-options（移一次添加两个属性）
		// $("#selectBuildSearch").attr({
		// "class" : 'easyui-datebox',
		// "data-options" : 'editable:false'
		// });

		// 单个属性添加
		$("#selectBuildSearch").attr("class", 'easyui-datebox');
		$("#selectBuildSearch").attr("data-options", 'editable:false');

		// $("#selectBuildSearch").innerHTML = '<input name="selectBuildSearch"
		// id="selectBuildSearch" value="22" class="easyui-datebox"
		// data-options="editable:false" />';
	}

	// if (optionsVal == 1 || optionsVal == 4 || optionsVal == 5) {
	// $("#selectBuildSearch").attr("type","text");
	// $("#selectBuildSearch").removeAttr("class");
	// $("#selectBuildSearch").removeAttr("data-options");
	// }
}

/**
 * @author Hehaipeng
 * @date 2017年3月10日
 * @todo TODO 条件查询
 */
function buildList_search() {
	var optionsVal = $("#getOneway option:selected").val(); // 获取选中的项
	if (optionsVal == 1) {
		var buildName = $("#selectBuildSearch").val();
	} else if (optionsVal == 2) {
		// 获得选中的值
		var buildStartDate = $("#selectBuildSearch").datebox('getValue');
	} else if (optionsVal == 3) {
		var buildEndDate = $("#selectBuildSearch").datebox('getValue');
		var buildEndDate = $("#selectBuildSearch").datebox('getValue');
	} else if (optionsVal == 4) {
		var buildInfo = $("#selectBuildSearch").val();
	} else if (optionsVal == 5) {
		var buildArea = $("#selectBuildSearch").val();
	}else{
		var buildStartDate = $("#buildStartDate").datebox('getValue');
		var buildEndDate = $("#buildEndDate").datebox('getValue');
	}
	var map = {
		'buildName' : buildName,
		'buildStartDate' : buildStartDate,
		'buildEndDate' : buildEndDate,
		'buildInfo' : buildInfo,
		'buildArea' : buildArea
	};

	$("#buildList_table").datagrid('load', {
				params : map
			});
}

/**
 * @author Hehaipeng
 * @date 2017年3月15日
 * @todo TODO 文件下载
 * @param val
 */
function uploadMaterials(val) {
	var confirm = function() {
		window.location.href = "uploadAttachmentForBuild.do?id=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此附件?", confirm);
}

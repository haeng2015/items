
$(function() {
			$("#addBuilding_form").validate({
				onkeyup : false,
				// 错误信息提示位置
				errorPlacement : function(error, element) {
					error.appendTo(element.parent("div")
							.next("p.release_error_word"));
				},

				// 条件
				rules : {
					buildName : {
						required : true,
						english_chinese : true,
						minlength : 2,
						maxlength : 50
					},
					buildArea : {
						required : true,
						figure : true
					}
				},
				// 错误信息
				messages : {
					buildName : {
						required : "请输入楼栋名称",
						minlength : "请输入至少2个字符",
						maxlength : "请不要超过50个字符"
					},
					buildArea : {
						required : "请输入建筑面积"
					}
				},
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				}
			});
			// 当修改时为data日期插件赋值
			// var buildId = $('#buildId').val();
			// var startDate = $('#startDate').val();
			// var endDate = $('#endDate').val();
			// if (buildId != "") {
			// $('#buildStartDate').datebox('setValue', startDate);
			// $('#buildEndDate').datebox('setValue', endDate);
			// }

			// w文件上传：：初始化，获得所有file类型的id(name)值,放入数组中this.id this.name
			window.fileArr = []; // 定义为全局变量
			$("input[type='file']").each(function() {
						if (this.id != '' && this.id != undefined) {
							if (this.value != null && this.value != "") {
								fileArr.push(this.id);
							}
						}
					});
		});

$(document).ready(function() {

	$("#addBuilding_submit").click(function() {
		// 初始化，获得所有file类型的id(name)值,放入数组中this.id this.name
		window.fileArr = []; // 定义为全局变量
		$("input[type='file']").each(function() {
					if (this.id != '' && this.id != undefined) {
						if (this.value != null && this.value != "") {
							fileArr.push(this.id);
						}
					}
				});
		
		var buildId = $('#buildId').val();
		var buildName = $('#buildName').val();
		var buildStartDate = $('#buildStartDate').datebox('getValue');
		var buildEndDate = $('#buildEndDate').datebox('getValue');
		var buildArea = $('#buildArea').val();
		var buildInfo = $('#buildInfo').val();

		if (buildStartDate != null && buildStartDate != "") { // 判断截止日期
			if (buildEndDate != null && buildEndDate != "") {
				if ($("#addBuilding_form").valid()) { // 通过验证
					$.ajaxFileUpload({
						url : 'saveOrUpdateBuilding.do',
						fileElementId : fileArr, // 文件上传（多个）
						secureuri : false, // 是否需要安全协议，一般设置为false
						data : {
							buildId : buildId,
							buildName : buildName,
							buildStartDate : buildStartDate,
							buildEndDate : buildEndDate,
							buildArea : buildArea,
							buildInfo : buildInfo
						},
						type : "POST",
						dataType : "json",
						success : function(json) {
							// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
							if (json.reflag == true) {
								top.window.showMsg("成功信息", json.infoMsg);
								// if (confirm(json.infoMsg +
								// "进入楼栋列表查看")) {
								setTimeout(
										"window.location.href='toBuildingList.do';",
										1500);
								// setTimeout("window.location.href='editBuilding.do?mark=2&buildId="+
								// buildId +"';",1500);
								// }
							} else {
								top.window.alertMsg("错误信息", json.infoMsg,
										"warning");
							}
						}
					});
				} else { // 拒绝提交
					// 收到返回消息之后先隐藏滚动条
					top.window.closeProgressBar();
				}
			} else {
				top.window.alertMsg('温馨提示', "请选择竣工时间!", 'warning');
			}
		} else {
			top.window.alertMsg('温馨提示', "请选择开工时间!", 'warning');
		}
	});
});

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 修改竞价产品时，删除上传的文件
 */
function deleteMaterials(val) {
	var confirm = function() {
		$.ajax({
					url : "deleteAttachmentForBidding.do",
					data : {
						attId : val
					},
					dataType : "JSON",
					type : "POST",
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 刷新页面
							setTimeout(
									"window.location.href = 'findBiddingByID.do?mark=1&productID="
											+ $("#productID").val() + "';",
									1500);
						} else {
							top.window.showMsg("错误信息", result.infoMsg);
						}
					}
				});
	}
	top.window.confirmMsg('信息确认', "确定删除此附件?", confirm);
}

/**
 * @author Hehaipeng
 * @date 2017年3月14日
 * @todo TODO 下载文件
 */
function uploadMaterials(val) {
	var confirm = function() {
		window.location.href = "uploadAttachmentForBuild.do?id=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此文件?", confirm);
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 修改竞价产品时，删除上传的文件
 */
function deleteMaterials(val) {
	var confirm = function() {
		$.ajax({
					url : "deleteAttachmentForBuild.do",
					data : {
						id : val
					},
					dataType : "JSON",
					type : "POST",
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 刷新页面
							setTimeout("window.location.href='editBuilding.do?buildId="+ $("#buildId").val() + "';", 1000);
						} else {
							top.window.showMsg("错误信息", result.infoMsg);
						}
					}
				});
	}
	top.window.confirmMsg('信息确认', "确定删除此附件?", confirm);
}


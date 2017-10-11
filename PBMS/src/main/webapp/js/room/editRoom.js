/**
 * @author Hehaipeng
 * @date 2017年4月11日
 * @todo TODO
 */

$(function() {
	$("#addRoom_form").validate({
				onkeyup : false,
				// 错误信息提示位置
				errorPlacement : function(error, element) {
					error.appendTo(element.parent("div")
							.next("p.release_error_word"));
				},

				// 条件
				rules : {
					roomName : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					buildId : {
						required : true
					},
					roomType : {
						required : true
					},
					roomUse : {
						required : true
					},
					roomArea : {
						required : true,
						figure : true
					},
					roomProb : {
						required : true,
						figure : true
					}
				},
				// 错误信息
				messages : {
					buildId : {
						required : "请选择楼栋"
					},
					roomName : {
						required : "请输入房间名称",
						minlength : "请输入至少2个字符",
						maxlength : "请不要超过50个字符"
					},
					roomType : {
						required : "请选择房型"
					},
					roomUse : {
						required : "请选择用途"
					},
					roomArea : {
						required : "请输入建筑面积"
					},
					roomProb : {
						required : "请输入得房率"
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

	/**
	 * 保存对象
	 */
	$("#addRoom_submit").click(function() {
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
		var roomId = $('#roomId').val();
		var roomName = $('#roomName').val();
		var roomStartDate = $('#roomStartDate').datebox('getValue');
		var roomEndDate = $('#roomEndDate').datebox('getValue');
		var roomType = $('#roomType').val();
		var roomUse = $('#roomUse').val();
		var roomArea = $('#roomArea').val();
		var roomProb = $('#roomProb').val();

		if (roomStartDate != null && roomStartDate != "") {
			if (roomEndDate != null && roomEndDate != "") {
				if ($("#addRoom_form").valid()) { // 通过验证
					$.ajaxFileUpload({
						url : 'saveOrUpdateRoom.do',
						fileElementId : fileArr, // 文件上传（多个）
						secureuri : false, // 是否需要安全协议，一般设置为false
						data : {
							buildId : buildId,
							roomId : roomId,
							roomName : roomName,
							roomStartDate : roomStartDate,
							roomEndDate : roomEndDate,
							roomType : roomType,
							roomUse : roomUse,
							roomArea : roomArea,
							roomProb : roomProb
						},
						type : "POST",
						dataType : "json",
						success : function(json) {
							// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
							if (json.reflag == true) {
								top.window.showMsg("成功信息", json.infoMsg);
								if (json.dlgType == 2) {
									setTimeout(
											"window.location.href='toRoomList.do';",
											1500);
								} else {

									setTimeout(
											"window.location.href='editRoom.do?roomId="
													+ roomId + "';", 1500);
								}
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
				top.window.alertMsg('温馨提示', "请选择到期时间!", 'warning');
			}
		} else {
			top.window.alertMsg('温馨提示', "请选择入住时间!", 'warning');
		}
	});
});

/**
 * @author Hehaipeng
 * @date 2017年3月14日
 * @todo TODO 下载文件
 */
function uploadMaterials(val) {
	var confirm = function() {
		window.location.href = "uploadAttachmentForRoom.do?id=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此文件?", confirm);
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 删除文件
 */
function deleteMaterials(val) {
	var confirm = function() {
		$.ajax({
					url : "deleteAttachmentForRoom.do",
					data : {
						id : val
					},
					dataType : "JSON",
					type : "POST",
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 刷新页面
							setTimeout(
									"window.location.href='editRoom.do?roomId="
											+ $("#roomId").val() + "';", 1000);
						} else {
							top.window.showMsg("错误信息", result.infoMsg);
						}
					}
				});
	}
	top.window.confirmMsg('信息确认', "确定删除此附件?", confirm);
}

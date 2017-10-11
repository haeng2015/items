$(function() {

			// 检测设备id是否已经存在 deviceId
			$.extend($.fn.validatebox.defaults.rules, {
						checkDeviceId : {
							validator : function(value, param) {
								var reflag = false;
								$.ajax({
											url : 'checkDeviceId.do',
											dataType : 'json',
											async : false,
											data : {
												deviceId : value
											},
											success : function(msg, status) {
												reflag = msg.reflag;
											}
										});
								return reflag;
							},
							message : "设备ID已存在！"
						}
					});
		});

/**
 * 保存设备
 */
function saveOrUpdateDevice() {

	if ($('#device_table').form('validate')) {
		// 判断是否有照片
		$.ajax({
					url : 'saveOrUpdateDevice.do',
					secureuri : false,
					dataType : 'json',
					data : {
						userDeviceId : $("#userDeviceId").val(),
						userName : $("#userName").val(),
						deviceNum : $("#deviceNum").val(),
						devicePlace : $("#devicePlace").val(),
						deviceName : $("#deviceName").val(),
						deviceId : $("#deviceId").val(),
						remarks : $("#remarks").val()
					},
					success : function(msg, status) {
						if (msg.reflag == true) {
							top.window.showMsg("成功信息", msg.infoMsg);
							if ($("#userDeviceId").val() != '') {
								setTimeout(
										"window.location.href='toDeviceEdit.do?userDeviceId="
												+ $("#userDeviceId").val() + "';", 1000);
							} else {
								setTimeout("window.location.reload(false);",
										1000);
							}

						} else {
							top.window.alertMsg("信息提示", msg, "warning");
							setTimeout("window.location.reload(false);", 2000);
						}
					},
					error : function(data, status, e) {
						top.window
								.alertMsg("提示信息", "上传文件失败，请刷新页面后再试！", "error");
						setTimeout("window.location.reload(false);", 2000);
					}
				});
	}
}

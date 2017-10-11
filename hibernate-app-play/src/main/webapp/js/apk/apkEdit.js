$(function() {

			// 检测版本是否已经存在 versionCode
			$.extend($.fn.validatebox.defaults.rules, {
						checkVersionNumber : {
							validator : function(value, param) {
								var reflag = false;
								$.ajax({
											url : 'checkApkVersionNum.do',
											dataType : 'json',
											async : false,
											data : {
												versionCode : value
											},
											success : function(msg, status) {
												reflag = msg.reflag;
											}
										});
								return reflag;
							},
							message : "版本号已存在！"
						}
					});
		});

function saveOrUpdateApk() {
	var apkId = $("#apkId").val();
	var validate = $('#apk_table').form('validate');

//	if (apkId == "") {
//		if ($("#apkFileUpload").val() == "") {
//			validate = false;
//			top.window.alertMsg("信息提示", "请选择apk文件！", "warning");
//			return;
//		}
//	}

	if (validate) {
		$.ajaxFileUpload({
					url : 'saveOrUpdateApk.do',
					secureuri : false,
					fileElementId : ['apkFileUpload'],
					dataType : 'json',
					data : {
						apkId : apkId,
						versionCode : $("#versionCode").val(),
						remarks : $("#remarks").val()
					},
					success : function(msg, status) {
						if (msg.reflag == true) {
							top.window.showMsg("成功信息", msg.infoMsg);
							if ($("#apkId").val()) {
								setTimeout("window.location.reload(false);",
										1000);
							} else {
								setTimeout(
										"window.location.href='toApkEdit.do?apkId="
												+ apkId + "';", 1000);
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

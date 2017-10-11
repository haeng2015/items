/**
 * @author Hehaipeng
 * @date 2017年5月18日
 * @todo TODO
 */
function addCharge() {
	var chargeId = $('#chargeId').val();
	var chargeName = $('#chargeName').val();
	var chargePrice = $('#chargePrice').val();

	// if ($("#editCharge_form").valid()) { // 通过验证
	if ($('#editCharge_form').form('validate')) { // 通过验证输入框验证方式
		$.ajax({
					url : 'saveOrUpdateCharge.do',
					secureuri : false, // 是否需要安全协议，一般设置为false
					data : {
						chargeId : chargeId,
						chargeName : chargeName,
						chargePrice : chargePrice
					},
					type : "POST",
					dataType : "json",
					success : function(json) {
						// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							if (chargeId != null && chargeId != "") {
								setTimeout(
										"window.location.href='toChargeList.do';",
										1000);
							} else {
								setTimeout(
										"window.location.href='editCharge.do';",
										1000);
							}
						} else {
							top.window
									.alertMsg("温馨提示", json.infoMsg, "warning");
						}
					}
				});
	} else { // 拒绝提交
		// 收到返回消息之后先隐藏滚动条
		top.window.closeProgressBar();
	}
}
/**
 * @author Hehaipeng
 * @date 2017年4月22日
 * @todo TODO 为用户编辑角色
 */
function saveUserRole() {
	var userRoleId = $('#userRoleId').val();
	var roleId = $("input[name='userRole']:checked").val();
	var userId = $('#userId').val();

	$.ajax({
				url : 'saveUserRole.do',
				secureuri : false, // 是否需要安全协议，一般设置为false
				data : {
					userRoleId : userRoleId,
					userId : userId,
					roleId : roleId
				},
				type : "POST",
				dataType : "json",
				success : function(json) {
					// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
					if (json.reflag == true) {
						top.window.showMsg("成功信息", json.infoMsg);
						setTimeout("window.location.href='toUserList.do';", 1500);
					} else {
						top.window.alertMsg("错误信息", json.infoMsg, "warning");
					}
				}
			});

}

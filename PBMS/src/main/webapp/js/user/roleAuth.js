/**
 * @author Hehaipeng
 * @date 2017年4月22日
 * @todo TODO 为用户编辑角色
 */
function saveRoleAuth() {
	var roleId = $('#roleId').val();
	var authIds = new Array();
	// 获得所有权限id
	var roleAuth = document.getElementsByName("roleAuth");
	for (var i = 0; i < roleAuth.length; i++) {
		if (roleAuth[i].checked) {
//			alert(roleAuth[i].value + "," + roleAuth[i].nextSibling.nodeValue);
			authIds[i] = roleAuth[i].value;
		}
	}

	$.ajax({
				url : 'saveOrUpdateRoleAuth.do',
				secureuri : false, // 是否需要安全协议，一般设置为false
				data : "roleId="+roleId+"&authIds="+authIds,
				//不能通过以下方式传递数组参数(报错：for input string "")
//				data : {
//					roleId : roleId,
//					authIds : authIds
//				},
				type : "POST",
				dataType : "json",
				success : function(json) {
					// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
					if (json.reflag == true) {
						top.window.showMsg("成功信息", json.infoMsg);
						setTimeout("window.location.href = 'addAuthForRole.do?roleId=" +roleId +"';", 1500);
					} else {
						top.window.alertMsg("错误信息", json.infoMsg, "warning");
					}
				}
			});

}

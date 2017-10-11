
function spanNav(b) {
	b.click(function() {
				b.removeClass("current");
				$(this).addClass("current");
			});
}
$(document).ready(function() {
			spanNav($(".power_div li"));
			$(window.parent.document).find("#iframepage").load(function() {
						var main = $(window.parent.document)
								.find("#iframepage");
						thisBodyHeight = $("html").height();
						main.height(thisBodyHeight);
					});

			privilegeList_getPrivilegeByRoleId();
		});

/*---------------------树上所有勾选的项目都清空----------------*/
function privilegeList_clearTree() {
	// 所有选项勾选框都清空
	var nodes = $('#roleAuthList_tree').tree('getChecked');
	$(nodes).each(function(index) {
				$('#roleAuthList_tree').tree('uncheck', this.target);
			});
}

/*--------------------- 保 存 角色权 限 ----------------*/
function saveRoleAuth() {
	var roleId = $("#roleId").val();
	if (roleId == null || roleId == '') {
		top.window.alertMsg("提示信息", "未选中角色，不能保存权限！", "warning");
		return;
	}
	// 调用顶层父窗口的滚动条
	top.window.openProgressBar();
	// 获取选中、半选中的节点
	var nodes = $('#roleAuthList_tree').tree('getChecked',
			['checked', 'indeterminate']);
	var checkedId = "";
	$(nodes).each(function(index) {
				checkedId += this.id + ",";
			});
	$.ajax({
				url : "saveOrUpdateRoleAuth.do",
				data : {
					authIds : checkedId,
					roleId : roleId
				},
				dataType : "json",
				type : "post",
				success : function(result) {
					// 关闭顶层父窗口的滚动条
					top.window.closeProgressBar();
					if (result.reflag == true) {
						top.window.showMsg("成功信息", result.infoMsg);
					} else {
						top.window.alertMsg("提示信息", result.infoMsg, "warning");
					}
				}
			});
}

/*---------------------加载角色权限数据----------------*/
function privilegeList_getPrivilegeByRoleId() {
	// 调用顶层父窗口的滚动条
	top.window.openProgressBar();
	privilegeList_clearTree();
	// 获取角色链接的id
	var roleId = $("#roleId").val();
	$.ajax({
		url : "getRoleAuthByRole.do",
		data : {
			roleId : roleId
		},
		dataType : "json",
		type : "post",
		success : function(result) {
			// 关闭顶层父窗口的滚动条
			top.window.closeProgressBar();
			if (result.reflag == true) {
				// 遍历获得的权限集合
				$.each(result.returnObj, function() {
							// 获取权限id
							var id = this.auth.authId;
							// 获取对应树节点
							var node = $('#roleAuthList_tree').tree('find', id);
							// 打钩
							$('#roleAuthList_tree').tree('check', node.target);
						});
			} else {
//				top.window.alertMsg("警告提示", result.infoMsg, "warning");
			}
		}
	});
}

function resetRoleAuth(){
	privilegeList_getPrivilegeByRoleId();
}

/*-----------------------------重 置 权 限----------------------------------*/
function privilegeList_initPrivilege() {
	if (currentRoleId == null || currentRoleId == '') {
		top.window.alertMsg("提示信息", "未选中角色，不能重置权限！", "warning");
		return;
	}
	// 调用顶层父窗口的滚动条
	top.window.openProgressBar();
	// 所有选项勾选框都清空
	privilegeList_clearTree();

	// 加载当前角色
	$.ajax({
		url : "getCurrentRoleMenus.do",
		data : {
			boRoleId : currentRoleId
		},
		dataType : "json",
		type : "post",
		success : function(result) {
			// 关闭顶层父窗口的滚动条
			top.window.closeProgressBar();
			if (result.reflag == true) {
				// 遍历获得的权限集合
				$(result.returnObj).each(function(index) {
							// 获取权限id
							var id = this.boMenuId;
							// 获取对应树节点
							var node = $('#roleAuthList_tree').tree('find', id);
							// 打钩
							$('#roleAuthList_tree').tree('check', node.target);
						});
			} else {
				top.window.alertMsg("警告提示", result.infoMsg, "warning");
			}
		}
	});
}

/*-----------------------------清 空 权 限----------------------------------*/
function deleteRoleAuth() {
	// 所有选项勾选框都清空
	privilegeList_clearTree();
}
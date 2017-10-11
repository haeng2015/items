$(function() {
			$("#updateUserPWD_form").validate({
				onkeyup : false,
				// 错误信息提示位置
				errorPlacement : function(error, element) {
					error.appendTo(element.parent("div")
							.next("p.release_error_word"));
				},

				// 条件
				rules : {
					userPwd1 : {
						required : true,
						minlength : 4,
						maxlength : 16
					},
					userPwd : {
						required : true,
						minlength : 4,
						maxlength : 16
					}
				},
				// 错误信息
				messages : {
					userPwd1 : {
						required : "请输入登录密码",
						minlength : "请输入至少4个字符",
						maxlength : "请不要超过16个字符"
					},
					userPwd : {
						required : "请输入确认密码",
						minlength : "请输入至少4个字符",
						maxlength : "请不要超过16个字符"
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
		});
		
function showMsg(title, msg) {
	$.messager.show({
				title : title,
				msg : '<h4>' + msg + '</h4>',
				width : 300,
				height : 125,
				showType : 'show',
				timeout : 2000
			});
}


/**
 * @author Hehaipeng
 * @date 2017年5月4日
 * @todo TODO修改自己账号的密码等信息
 */
function updateUserPWD_submit() {

	var userId = $('#userId').val();
	var userPwd1 = $('#userPwd1').val();
	var userPwd = $('#userPwd').val();

	var checkpassword = document.getElementById("checkpassword");
	if (userPwd1 != userPwd) {
		checkpassword.innerHTML = '<p class="release_error_word" id="checkpassword">两次输入密码不一致!</p>';
		return null;
	}

	if ($("#updateUserPWD_form").valid()) { // 通过验证
		$.ajax({
					url : 'saveUpdateUserInfo.do',
					secureuri : false, // 是否需要安全协议，一般设置为false
					data : {
						userId : userId,
						userPwd : userPwd
					},
					type : "POST",
					dataType : "json",
					success : function(json) {
						// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
						if (json.reflag == true) {
							if(window.confirm("密码修改成功，去登录?")){
								setTimeout("window.location.href='login.do'", 500); // 0.5秒后跳转
							}
						} else {
							top.window
									.alertMsg("错误信息", json.infoMsg, "warning");
						}
					}
				});
	} else { // 拒绝提交
		// 收到返回消息之后先隐藏滚动条
		top.window.closeProgressBar();
	}
}

/**
 * 检查两次密码是否一致
 */
function checkPassword(val) {
	var userPwd1 = $('#userPwd1').val();
	var checkpassword = document.getElementById("checkpassword");
	if (userPwd1 != val) {
		checkpassword.innerHTML = '<p class="release_error_word" id="checkpassword">两次输入密码不一致!</p>';
	} else {
		checkpassword.innerHTML = '<p class="release_error_word" id="checkpassword"></p>';
	}
}


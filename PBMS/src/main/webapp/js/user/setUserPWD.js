$(function() {
			$("#setpwd_form").validate({
				onkeyup : false,
				// 错误信息提示位置
				errorPlacement : function(error, element) {
					error.appendTo(element.parent("div")
							.next("p.release_error_word"));
				},

				// 条件
				rules : {
					userLogin : {
						required : true,
						english_figure : true,
						minlength : 2,
						maxlength : 16
					},
					userCard : {
						required : true,
						IDcard : true
					},
					userPhone : {
						required : true,
						cellphone : true
					}
				},
				// 错误信息
				messages : {
					userLogin : {
						required : "请输入登录账号",
						minlength : "请输入至少2个字符",
						maxlength : "请不要超过16个字符"
					},
					userCard : {
						required : "请输入身份证"
					},
					userPhone : {
						required : "请输入联系电话"
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

/**
 * @author Hehaipeng
 * @date 2017年5月4日
 * @todo TODO修改自己账号的密码等信息
 */
function validateUserInfo_submit() {

	var userLogin = $('#userLogin').val();
	var userCard = $('#userCard').val();
	var userPhone = $('#userPhone').val();

	// 执行用户名检测
	$.ajax({
		url : "checkLoginName.do",
		data : {
			userLogin : $("#userLogin").val()
		},
		dataType : "JSON",
		type : "POST",
		success : function(json) {
			var checkLogin = document.getElementById("checkLogin");
			if (json.reflag == false) {
				checkLogin.innerHTML = '<p class="release_error_word" id="checkLogin">'
						+ json.infoMsg + '</p>';
			} else {
				checkLogin.innerHTML = '<p class="release_error_word" id="checkLogin"></p>';

				if ($("#setpwd_form").valid()) { // 通过验证
					$.ajax({
						url : 'validateUserInfo.do',
						secureuri : false, // 是否需要安全协议，一般设置为false
						data : {
							userLogin : userLogin,
							userCard : userCard,
							userPhone : userPhone
						},
						type : "POST",
						dataType : "json",
						success : function(json) {
							// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
							var showInfo = document.getElementById("showInfo");
							if (json.reflag == true) {
								window.location.href = "tosetSetUserPwd.do?userId=" + json.returnObj.userId
										+ "&userLogin=" + json.returnObj.userLogin;
							} else {
//								top.window.alertMsg("错误信息", json.infoMsg, "warning");
								showInfo.innerHTML = '<font id="showInfo" color="red">'+json.infoMsg+'</font>';
							}
						}
					});
				} else { // 拒绝提交
					// 收到返回消息之后先隐藏滚动条
					top.window.closeProgressBar();
				}
			}
		}
	});
}

// 只能输入英文字母或汉字
jQuery.validator.addMethod("English_Chinese", function(value, element) {
			// return this.optional(element) ||
			// /^([a-zA-Z]|[\u4e00-\u9fa5])+$/.test(value);
			return this.optional(element)
					|| /^[A-Z|a-z|\u4e00-\u9fa5]+$/.test(value);
		}, "只能输入英文字母或汉字");

// 只能输入数字
jQuery.validator.addMethod("isNumber", function(value, element) {
			return this.optional(element) || /^[0-9]+$/.test(value);
		}, "只能输入数字");

$(function() {
			$("#loginForm").validate({
						onkeyup : false,
						// 错误信息提示位置
						errorPlacement : function(error, element) {
							error.appendTo(element.parent("div")
									.next("p.error_word"));
						},

						// 条件
						rules : {
							userLogin : {
								required : true,
								English_Chinese : true,
								minlength : 2,
								maxlength : 8
							},
							userPwd : {
								required : true,
								minlength : 4,
								maxlength : 16
							},
							securityCode : {
								required : true
							}
						},
						// 错误信息
						messages : {
							userLogin : {
								required : "请输入用户名",
								minlength : "请输入至少2个字符",
								maxlength : "请不要超过8个字符"
							},
							userPwd : {
								required : "请输入密码",
								minlength : "请输入至少4个字符",
								maxlength : "请不要超过16个字符"
							},
							securityCode : {
								required : "请输入验证码"
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

$(document).ready(function() { // 刷新页面及加载
			$('#securityCode').blur(function() { // 失去焦点触发
						var securityCode = $('#securityCode').val();
						if (securityCode == "") {

						} else {
							$.ajax({
								url : "chechKaptchaImage.do",
								data : {
									securityCode : securityCode
								},
								type : "post",
								dataType : "json",
								success : function(json) {
									// json = JSON.parse(json); //转为json格式
									// json = eval('(' + json + ')'); //
									// 解析json为字符串
									// json = (new Function("return " +
									// json))();
									// //解析json为字符串
									var errWord = document
											.getElementById("error_word_securityCode");
									if (json.reflag == false) {
										errWord.innerHTML = '<p class="error_word" id="error_word_securityCode">'
												+ json.infoMsg + '</p>';
									} else {
										errWord.innerHTML = '<p class="error_word" id="error_word_securityCode"></p>';
									}
								},
								error : function(msg) {
									top.window.alertMsg("错误信息", msg, "warning");
								}
							});
						}
					})
		});

$(function() {
	// 生成验证码
	$('#kaptchaImage').click(function() {
		$(this).attr(
				'src',
				'ClinicCountManager/captcha-image.do?'
						+ Math.floor(Math.random() * 100)).fadeIn();
	});
	// 按enter键登录
	document.onkeydown = function(event) {
		if (event.keyCode == 13) // 回车键的键值为13
			loginSubmit();
	};
		// enter键登录（无效方法）
		// $('input').bind('keypress', function(event) {
		// if (event.keyCode == "13") { // 调用登录按钮的登录事件(回车键的键值为13)
		// loginSubmit();
		// }
		// });

	});

/**
 * 登录提交
 */
function loginSubmit() {
	var userLogin = $('#userLogin').val();
	var userPwd = $('#userPwd').val();
	var securityCode = $('#securityCode').val();

	if ($("#loginForm").valid()) { // 通过验证
		$.ajax({
			url : 'checkUserInfo.do',
			secureuri : false, // 是否需要安全协议，一般设置为false
			data : {
				userLogin : userLogin,
				securityCode : securityCode,
				userPwd : userPwd
			},
			type : "POST",
			dataType : "json",
			success : function(json) {
				// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
				if (json.reflag == true) {
					window.location.href = "intoWelcome.do";
				} else {
					if (json.dlgType = '1') { // 验证码
						var errWord = document
								.getElementById("error_word_securityCode"); // 验证码
						errWord.innerHTML = '<p class="error_word" id="error_word_securityCode">'
								+ json.infoMsg + '</p>';
					} else { // 用户名、密码
					// var errUser =
					// document.getElementById("error_word_userInfo"); //用户名密码
					// errUser.innerHTML = '<p class="error_word"
					// id="error_word_securityCode"></p>';
						top.window.alertMsg("温馨提示", json.infoMsg, "warning");
					}
				}
			},
			error : function(msg) {
				top.window.alertMsg("错误信息", msg, "warning");
			}
		});
	} else {

	}
}

/**
 * 忘记密码
 */
function forgetPWD() {
	window.location.href = "toUpdatePwd.do";
}
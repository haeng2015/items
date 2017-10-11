/**
 * @author Hehaipeng
 * @date 2017年3月23日
 * @todo TODO 注销退出
 */
$(document).ready(function() { // 刷新页面及加载

			// 注销退出
			$("#cancelQuite").click(function() {
				var confirm = function() {
					$.ajax({
								url : "/PBMS/cancelQuite.do",
								data : {},
								type : "post",
								dataType : "JSON",
								success : function(json) {
									if (json.reflag == true) {
										setTimeout(
												"window.location.href='/PBMS/login.do'",
												1000); // 1秒后跳转
									}
								},
								error : function(msg) {
									$.messager.alert({
												title : "错误信息",
												msg : msg,
												icon : "warning"
											});
								}
							});
				}
				top.window.confirmMsg('信息确认', "确定退出？", confirm);
			});

			// session消失时，自动退出
			$.ajax({
						url : "/PBMS/checkSession.do",
						data : {},
						type : "POST",
						dataType : "JSON",
						success : function(json) {
							if (json.reflag == true) {
								alertMsg('温馨提示', json.infoMsg, 'warning');
								setTimeout(
										"window.location.href='/PBMS/login.do'",
										1000); // 1秒后跳转到登录页面
							}
						}
					});
		});

/**
 * 修改自己账号的密码
 * 说明：如果直接updateUserInfo.do，而从欢迎页进入则不会被拦截；
 * 		如果从其他页面，如用户列表页进入，则会被拦截，默认请求路径这时变为/user/updateUserInfo.do,因此被拦截
 */
function updateUserInfo() {
	window.location.href="/PBMS/updateUserInfo.do";
}

/**
 * 为管理员添加权限(超级管理员、超级角色标志都为1,且都只有一个)
 */
function addAuthForAdmin() {
	$.ajax({
				url : "addRoleAndAuthForAdmin.do",
				data : {
					superType : '1'
				},
				type : "POST",
				dataType : "JSON",
				success : function(json) {
					if (json.reflag == true) {
						top.window.showMsg("成功信息", json.infoMsg);
					}else{
						alertMsg('温馨提示', json.infoMsg, 'warning');
					}
				}
			});
}

/**
 * 提供一个启动滚动条的方法。
 */
function openProgressBar() {
	$('#progressBarWindow').panel('open', true);
}

/**
 * 提供一个关闭滚动条的方法。
 */
function closeProgressBar() {
	$('#progressBarWindow').panel('close');
}
/**
 * 提供一个提示条（alert）的方法。
 */
function alertMsg(title, msg, icon) {
	$.messager.alert({
				title : title,
				msg : msg,
				icon : icon
			});
}
/**
 * 提供一个提示条（show）的方法。
 */
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
 * 提供一个框架对话框弹出互动操作。
 */
function confirmMsg(title, msg, fn) {
	$.messager.confirm(title, msg, function(r) {
				if (r) {
					fn();
				}
			});
}

/**
 * 定义一个对话框输入
 */
function openDialog(id, width, height, titile, draggable, href, closed) {
	openOneDialog(id, width, height, titile, draggable, href, closed);
}

$(document).ready(function() {
	var erji_name = "";
	// 二级导航
	$(".nav li").hover(function() {
				$(this).find(".block_a p.bor").stop().animate({
							'bottom' : '1px'
						}, 250);
				$(this).find(".block_div").stop(true, true).slideDown(300);
				erji_name = $(this).find(".block_a").find("#menu_header")
						.text();
			}, function() {
				$(this).find(".block_div").stop(true, true).slideUp(1);
				if ($(this).find(".block_a").hasClass("current")) {
					return;
				}
				$(this).find(".block_a p.bor").stop().animate({
							'bottom' : '-10px'
						}, 250);
			});

	// 二级导航鼠标点击
	$(".nav li .block_div a").click(function() {
				$(".nav li .block_a").removeClass("current");
				$(".block_a p.bor").stop().animate({
							'bottom' : '-10px'
						}, 250);
				$('#location_erji').html(erji_name);
				$('#location_sanji').html("&nbsp;>&nbsp;" + $(this).text());
				$(this).parents("li").find(".block_a").addClass("current");
				$(this).parents("li").find(".block_a p.bor").stop().animate({
							'bottom' : '0'
						}, 250);
			});
	$(".nav li .block_a").click(function() {
				$(".nav li .block_a").removeClass("current");
				$(".block_a p.bor").stop().animate({
							'bottom' : '-10px'
						}, 250);
				$(this).parents("li").find(".block_a").addClass("current");
				$(this).parents("li").find(".block_a p.bor").stop().animate({
							'bottom' : '0'
						}, 250);
			});

		/*-----------------滚动进度条滚动效果--------------------*/
		// window.setInterval("progressBarRun('progressBar')", 100);
		/*--------------初始化完毕后关闭进度条----------------*/
		// $('#progressBarWindow').panel('close');
});

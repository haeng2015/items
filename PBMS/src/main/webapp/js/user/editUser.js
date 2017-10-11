$(function() {
			$("#addUser_form").validate({
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
					userPwd : {
						required : true,
						minlength : 4,
						maxlength : 16
					},
					userName : {
						required : true,
						english_chinese : true,
						minlength : 2,
						maxlength : 20
					},
					userSex : {
						required : true
					},
					userCard : {
						required : true,
						IDcard : true
					},
					QQ : {
						figure : true,
						minlength : 6,
						maxlength : 12
					},
					MSN : {
						Email : true
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
					userPwd : {
						required : "请输入登录密码",
						minlength : "请输入至少4个字符",
						maxlength : "请不要超过16个字符"
					},
					userName : {
						required : "请输入用户名",
						minlength : "请输入至少2个字符",
						maxlength : "请不要超过20个字符"
					},
					userSex : {
						required : "请输入选择性别"
					},
					userCard : {
						required : "请输入身份证"
					},
					QQ : {
						minlength : "请输入至少6个字符",
						maxlength : "请不要超过12个字符"
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

			// w文件上传：：初始化，获得所有file类型的id(name)值,放入数组中this.id this.name
			window.fileArr = []; // 定义为全局变量
			$("input[type='file']").each(function() {
						if (this.id != '' && this.id != undefined) {
							if (this.value != null && this.value != "") {
								fileArr.push(this.id);
							}
						}
					});

			// 图片默认同比例缩放
			!(function() {
				var w = $(".imgBox").width();// 容器宽度
				var h = $(".imgBox").height();// 容器宽度
				$(".imgBox img").each(function() {// 如果有很多图片，我们可以使用each()遍历
							var img_w = $(this).width();// 图片宽度
							var img_h = $(this).height();// 图片高度
							if (img_w > 0 && img_h > 0) {
								if (img_w > w) {
									$(this).width(w);
									$(this).height((img_h * w) / img_w);
									$(this).css({
										"position" : "absolute",
										"left" : "0",
										"top" : "50%",
										"marginTop" : -((img_h * w) / img_w)
												/ 2 + "px"
									});
								} else if (img_h > h) {
									$(this).height(h);
									$(this).width((img_w * h) / img_h);
								} else {
									$(this).width(img_w);
									$(this).height(img_h);
									$(this).css({
												"top" : "50%",
												"left" : "50%",
												"marginLeft" : -(img_w / 2)
														+ 'px',
												"marginTop" : -(img_h / 2)
														+ 'px'
											});
								}
							}
						});
			})();
		});

/**
 * 检查登录名是否已经存在
 */
function checkIsLoginName() {
	$.ajax({
		url : "checkUserLoginName.do",
		data : {
			userLogin : $("#userLogin").val(),
			userId : $('#userId').val()
		},
		dataType : "JSON",
		type : "POST",
		success : function(json) {
			var checkLogin = document.getElementById("checkLogin");
			// top.window.alertMsg('温馨提示', json.infoMsg, 'warning');
			if (json.reflag == true) {
				checkLogin.innerHTML = '<p class="release_error_word" id="checkLogin">'
						+ json.infoMsg + '</p>';
			} else {
				checkLogin.innerHTML = '<p class="release_error_word" id="checkLogin"></p>';

			}
		}
	});
}

$(document).ready(function() {

	$("#addUser_submit").click(function() {
		// 初始化，获得所有file类型的id(name)值,放入数组中this.id this.name
		window.fileArr = []; // 定义为全局变量
		$("input[type='file']").each(function() {
					if (this.id != '' && this.id != undefined) {
						if (this.value != null && this.value != "") {
							fileArr.push(this.id);
						}
					}
				});

		var userId = $('#userId').val();
		var userLogin;
		if(userId != null && userId != ""){
			userLogin = "";
		}else{
			userLogin = $('#userLogin').val();
		}
		var userPwd = $('#userPwd').val();
		var userName = $('#userName').val();
		var userSex = $("input[name='userSex']:checked").val();
		var userPhone = $('#userPhone').val();
		var userCard = $('#userCard').val();
		var userQq = $('#userQq').val();
		var userMsn = $('#userMsn').val();

		// 执行用户名检测
		$.ajax({
			url : "checkUserLoginName.do",
			data : {
				userLogin : $("#userLogin").val(),
				userId : userId
			},
			dataType : "JSON",
			type : "POST",
			success : function(json) {
				var checkLogin = document.getElementById("checkLogin");
				// top.window.alertMsg('温馨提示', json.infoMsg, 'warning');
				if (json.reflag == true) {
					checkLogin.innerHTML = '<p class="release_error_word" id="checkLogin">'
							+ json.infoMsg + '</p>';
				} else {
					checkLogin.innerHTML = '<p class="release_error_word" id="checkLogin"></p>';

					// 执行保存方法
					var checkLogin = document.getElementById("checkLogin");
					var checkLogin1 = checkLogin.innerText;
					var checkLogin2 = checkLogin.firstChild.nodeValue;
					
					if (checkLogin1 != null && checkLogin1 != ""
							|| checkLogin2 != null && checkLogin2 != "") {
						return false;
					}
					if (userSex != null && userSex != "") {
						if ($("#addUser_form").valid()) { // 通过验证
							$.ajaxFileUpload({
								url : 'saveOrUpdateUser.do',
								fileElementId : fileArr, // 文件上传（多个）
								secureuri : false, // 是否需要安全协议，一般设置为false
								data : {
									userId : userId,
									userLogin : userLogin,
									userPwd : userPwd,
									userName : userName,
									userCard : userCard,
									userSex : userSex,
									userQq : userQq,
									userMsn : userMsn,
									userPhone : userPhone
								},
								type : "POST",
								dataType : "json",
								success : function(json) {
									// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
									if (json.reflag == true) {
										top.window.showMsg("成功信息", json.infoMsg);
										$('#userList_table').datagrid('reload');
										if (userId != '') {
											setTimeout(
													"window.location.href='toUserList.do';",
													1500);
										} else {
											setTimeout(
													"window.location.href='editUser.do';",
													1500);
										}
									} else {
										top.window.alertMsg("错误信息",
												json.infoMsg, "warning");
									}
								}
							});
						} else { // 拒绝提交
							// 收到返回消息之后先隐藏滚动条
							top.window.closeProgressBar();
						}
					} else {
						top.window.alertMsg('温馨提示', "请选择性别!", 'warning');
					}
				}
			}
		});
	});
});

/**
 * 图片上传预览 IE是用了滤镜。
 * 
 * @param {}
 *            file
 */
function previewImage(file) {
	var MAXWIDTH = 250;
	var MAXHEIGHT = 200;
	var div = document.getElementById('preview');
	if (file.files && file.files[0]) {
		div.innerHTML = '<img id="imghead">';
		var img = document.getElementById('imghead');
		img.onload = function() {
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			// img.style.marginLeft = rect.left+'px';
			img.style.marginTop = rect.top + 'px';
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	} else // 兼容IE
	{
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id="imghead">';
		var img = document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
				img.offsetHeight);
		status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
		div.innerHTML = "<div id=divhead style='width:" + rect.width
				+ "px;height:" + rect.height + "px;margin-top:" + rect.top
				+ "px;" + sFilter + src + "\"'></div>";
		// div.innerHTML = "<div id='divhead'
		// style='width:120px;height:100px;margin-top:5px;"
		// + sFilter + src + "\"'></div>";
	}
}

/**
 * 设置图片默认大小（同比列缩放）
 * 
 * @param {}
 *            maxWidth
 * @param {}
 *            maxHeight
 * @param {}
 *            width
 * @param {}
 *            height
 * @return {}
 */
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		top : 0,
		left : 0,
		width : width,
		height : height
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		} else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}

	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 修改竞价产品时，删除上传的文件
 */
function deleteMaterials(val) {
	var confirm = function() {
		$.ajax({
					url : "deleteAttachmentForBidding.do",
					data : {
						attId : val
					},
					dataType : "JSON",
					type : "POST",
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 刷新页面
							setTimeout(
									"window.location.href = 'findBiddingByID.do?mark=1&productID="
											+ $("#productID").val() + "';",
									1500);
						} else {
							top.window.showMsg("错误信息", result.infoMsg);
						}
					}
				});
	}
	top.window.confirmMsg('信息确认', "确定删除此附件?", confirm);
}

/**
 * @author Hehaipeng
 * @date 2017年3月14日
 * @todo TODO 下载竞价文件
 */
function uploadMaterials(val) {
	var confirm = function() {
		window.location.href = "uploadAttachmentForBidding.do?attId=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此文件?", confirm);
}

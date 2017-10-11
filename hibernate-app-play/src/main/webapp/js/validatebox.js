$(document).ready(function() {
	$.extend($.fn.validatebox.defaults.rules, {
				figure : {
					validator : function(value) {
						return /^[0-9]+$/.test($.trim(value));
					},
					message : '请输入数字.'
				}
			});
	// 价格（金额数）
	$.extend($.fn.validatebox.defaults.rules, {
				money : {
					validator : function(value) {
						return /^[0-9.]+$/.test($.trim(value));
					},
					message : '请输入正确的金额数'
				}
			});
	// 规格
	$.extend($.fn.validatebox.defaults.rules, {
				productNorms : {
					validator : function(value) {
						return /^([0-9]{1,9})+\*(([0-9]{1,9})+\*)+[0-9]{1,9}$/i
								.test($.trim(value));
					},
					message : '请输入格式(123*456*789).'
				}
			});
	// 版本号
	$.extend($.fn.validatebox.defaults.rules, {
				version_number : {
					validator : function(value) {
						return /^([0-9]{1,3})+\.(([0-9]{1,3})+\.)+[0-9]{1,3}$/i
								.test($.trim(value));
					},
					message : '请输入版本号格式(1.3.24).'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				minLength : {
					validator : function(value, param) { // value 为需要校验的输入框的值
						// ,
						// param为使用此规则时存入的参数
						return value.length >= param[0];
					},
					message : '请输入最小{0}位字符.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				maxLength : {
					validator : function(value, param) {
						return param[0] >= value.length;
					},
					message : '请输入最大{0}位字符.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				length : {
					validator : function(value, param) {
						return value.length >= param[0]
								&& param[1] >= value.length;
					},
					message : '请输入{0}-{1}位字符.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				chinese : {
					validator : function(value) {
						return /^[\u0391-\uFFE5]+$/i.test(value);
					},
					message : '请输入姓名.'
				}
			});

	// extend the 'equals' rule
	$.extend($.fn.validatebox.defaults.rules, {
				equals : {
					validator : function(value, param) {
						return value == $(param[0]).val();
					},
					message : '字段不相同.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				web : {
					validator : function(value) {
						return /^(http[s]{0,1}|ftp):\/\//i.test($.trim(value));
					},
					message : '网址格式错误.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				mobile : {
					validator : function(value) {
						return /^1[3|5|7|8|][0-9]{9}$/i.test($.trim(value));
					},
					message : '手机号码格式错误.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				isPhone : {
					validator : function(value) {
						// 联系电话(手机/电话皆可)验证
						// var mobile =
						// /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
						// var tel = /^\d{3,4}-?\d{7,9}$/;
						return /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/i.test($
								.trim(value));
					},
					message : '联系方式格式错误.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
				date : {
					validator : function(value) {
						return /^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$/i.test($
								.trim(value));
					},
					message : '曰期格式错误,如2012-09-11.'
				}
			});

	$.extend($.fn.validatebox.defaults.rules, {
		email : {
			validator : function(value) {
				return /^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/i
						.test($.trim(value));
			},
			message : '电子邮箱格式错误.'
		}
	});
	// 验证码错误
	$.extend($.fn.validatebox.defaults.rules, {
				captcha : {
					validator : function(value) {
						var data0 = false;
						$.ajax({
									type : "POST",
									async : false,
									url : "",
									dataType : "json",
									data : {
										"simulation" : value
									},
									success : function(data) {
										data0 = data;
									}
								});

						return data0;
						// return /^[a-zA-Z0-9]{4}$/i.test($.trim(value));
					},
					message : '验证码错误.'
				}
			});

	// 用户名称已存在
	$.extend($.fn.validatebox.defaults.rules, {
				checkUserName : {
					validator : function(value, param) {
						var data0 = false;
						if (value.length >= param[0]
								&& param[1] >= value.length) {
							$.ajax({
										type : "POST",
										async : false,
										url : ".do",
										dataType : "json",
										data : {
											"userName" : value
										},
										async : false,
										success : function(data) {
											data0 = !data;
										}
									});
						} else {
							param[2] = "请输入" + param[0] + "-" + param[1]
									+ "位字符.";
							return false;
						}

						param[2] = "用户名称已存在.";
						return data0;
					},
					message : "{2}"
				}
			});

	// 检测版本是否已经存在 versionCode
	$.extend($.fn.validatebox.defaults.rules, {
				checkVersionNum : {
					validator : function(value, param) {
						var reflag = false;
						$.ajax({
									url : '.do',
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
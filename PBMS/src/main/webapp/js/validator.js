/**
 * @author Hehaipeng
 * @date 2017年4月11日
 * @todo TODO 校验器（正则表达式）
 */
// 只能输入汉字
jQuery.validator.addMethod("isChinese", function(value, element) {
			return this.optional(element) || /^[\u4e00-\u9fa5]+$/.test(value);
		}, "请输入汉字");

// 只能输入英文字母或汉字
jQuery.validator.addMethod("english_chinese", function(value, element) {
			// return this.optional(element) ||
			// /^([a-zA-Z]|[\u4e00-\u9fa5])+$/.test(value);
			return this.optional(element)
					|| /^[A-Z|a-z|\u4e00-\u9fa5]+$/.test(value);
		}, "请输入英文字母或汉字");

// 只能输入英文字母、数字和下划线
jQuery.validator.addMethod("english_figure", function(value, element) {
			return this.optional(element)
					|| /^[a-zA-Z_][a-zA-Z0-9_]+$/.test(value);
		}, "输入格式：字母或下划线开头，内容包含字母、数字、下划线");

// 只能输入英文字母
jQuery.validator.addMethod("english", function(value, element) {
			return this.optional(element) || /^[a-zA-Z][a-zA-Z]+$/.test(value);
		}, "请输入英文字母");
		
// 只能输入数字
jQuery.validator.addMethod("figure", function(value, element) {
			return this.optional(element) || /^[0-9.]+$/.test(value);
		}, "请输入数字");

// 身份证验证
jQuery.validator.addMethod("IDcard", function(value, element) {
			return this.optional(element) || /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
		}, "请输入正确的身份证号");

// 邮箱验证
jQuery.validator.addMethod("Email", function(value, element) {
			return this.optional(element) || /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value);
		}, "请输入正确的邮箱格式");

// 手机验证
jQuery.validator.addMethod("cellphone", function(value, element) {
			return this.optional(element) || /^1[3|4|5|8][0-9]\d{4,8}$/.test(value);
		}, "请输入正确的手机号");

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("linkPhone", function(value, element) {
			var length = value.length;
			var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
			var tel = /^\d{3,4}-?\d{7,9}$/;
			var tels = /^\d{3,4}-?\d{7,9}-?\d{2,8}$/;
			return this.optional(element)
					|| (tels.test(value) || tel.test(value) || mobile
							.test(value));
		}, "请输入一个有效的联系电话(如：0571-8888888-001)，或手机号。");
		
/**
 * 直接在输入框中的验证方式
 */		


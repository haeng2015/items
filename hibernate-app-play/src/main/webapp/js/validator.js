$(document).ready(function() {
	// 只能输入汉字
	jQuery.validator.addMethod("isChinese", function(value, element) {
				return this.optional(element)
						|| /^[\u4e00-\u9fa5]+$/.test(value);
			}, "请输入汉字");

	// 只能输入英文字母或汉字
	jQuery.validator.addMethod("english_chinese", function(value, element) {
				// return this.optional(element) ||
				// /^([a-zA-Z]|[\u4e00-\u9fa5])+$/.test(value);
				return this.optional(element)
						|| /^[A-Z|a-z|\u4e00-\u9fa5]+$/.test(value);
			}, "请输入英文字母或汉字");

	// 只能输入数字
	jQuery.validator.addMethod("figure", function(value, element) {
				return this.optional(element) || /^[0-9]+$/.test(value);
			}, "请输入数字");
			
	// 只能输入数字和.（可用于小数点的验证）
	jQuery.validator.addMethod("decimal_point", function(value, element) {
				return this.optional(element) || /^[0-9]+\.+[0-9]+$/.test(value);
			}, "请输入数字和小数点");
			
	// 只能输入数字和.（可用于版本号的验证）
	jQuery.validator.addMethod("version_number", function(value, element) {
				return this.optional(element) || /^([0-9]{1,3})+\.(([0-9]{1,3})+\.)+[0-9]{1,3}+$/.test(value);
			}, "请输入正确的版本号格式(1.3.24)");

	// 联系电话(手机/电话皆可)验证
	jQuery.validator.addMethod("linkPhone", function(value, element) {
				var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
				var tel = /^\d{3,4}-?\d{7,9}$/;
				var tels = /^\d{3,4}-?\d{7,9}-?\d{2,8}$/;
				var shotNum = /^\d{4,6}$/;
				return this.optional(element)
 						|| (tels.test(value) || tel.test(value) || mobile.test(value) || shotNum.test(value));
			}, "请输入一个有效的联系电话(如：0571-8888888-001)，或手机号。");
});
// 只能输入英文字母或汉字
jQuery.validator.addMethod("English_Chinese", function(value, element) {
			// return this.optional(element) ||
			// /^([a-zA-Z]|[\u4e00-\u9fa5])+$/.test(value);
			return this.optional(element)
					|| /^[A-Z|a-z|\u4e00-\u9fa5]+$/.test(value);
		}, "只能输入英文字母或汉字");

/**
 * @author Hehaipeng
 * @date 2017年3月28日
 * @todo TODO 保存、修改role
 */
$(document).ready(function() {
			$("#roleEditForm").validate({
						onkeyup : false,
						// 错误信息提示位置
						errorPlacement : function(error, element) {
							error.appendTo(element.parent("div")
									.next("p.error_word"));
						},

						// 条件
						rules : {
							roleName : {
								required : true,
								English_Chinese : true,
								minlength : 2,
								maxlength : 20
							}
						},
						// 错误信息
						messages : {
							roleName : {
								required : "请输入角色名",
								minlength : "请输入至少2个字符",
								maxlength : "请不要超过20个字符"
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

			$("#addRoleButton").click(function() {
						if ($("#roleEditForm").valid()) { // 通过验证
							addUpdateRole();
						}
					});

		});

/**
 * 添加、更新角色
 */
function addUpdateRole() {
	var roleName = $('#roleName').val();
	var roleId = $('#roleId').val();

	$.ajax({
				url : "addUpdateRole.do",
				data : {
					roleId : roleId,
					roleName : roleName
				},
				type : "POST",
				dataType : "JSON",
				success : function(json) {
					if (json.reflag == true) {
						top.window.showMsg("成功信息", json.infoMsg);
						setTimeout("window.location.href='toEditRole.do';",
								1500);
					} else {
						top.window.alertMsg("错误信息", json.infoMsg, "warning");
					}
				},
				error : function(info) {
					top.window.alertMsg("错误信息", "执行错误：" + info, "warning");
				}
			});
}

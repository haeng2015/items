$(function() {

	$("#resourceEdit_form").validate({
		onkeyup : false,
		// 错误信息提示位置
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("p.release_error_word"));
		},

		// 条件
		rules : {
			versionCode : {
				required : true
			}
		},
		// 错误信息
		messages : {
			type : {
				required : "请选择资源文件类型"
			}
		},
		errorPlacement : function(error, element) { // 指定错误信息位置
			if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
				var name = element.attr('name'); // 获取元素的name属性
				var val = $("input[name='" + name + "']:checked").val();
				if (val == null || val == "") {
					error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
				}
			} else {
				error.insertAfter(element);
			}
		}
	});
});	
	
function saveOrUpdateResource(){
	var resourceId = $("#resourceId").val();
	var validate = $('#resource_table').form('validate');
	
//	if(resourceId == ""){
//		if($("#resourceFileUpload").val() == ""){
//			validate = false;
//			top.window.alertMsg("信息提示", "请选择资源文件！", "warning");
//			return;
//		}
//	}
	
	if(validate){
		//判断是否有照片
		$.ajaxFileUpload({
			url : 'saveOrUpdateResource.do',
			secureuri : false,
			fileElementId : ['resourceFileUpload'],
			dataType : 'json',
			data: { 
				resourceId : resourceId,
				type : $("#type").val(),
				remarks : $("#remarks").val()
			},
			success : function(msg, status) {
				if (msg.reflag==true) {
					top.window.showMsg("成功信息", msg.infoMsg);
					if($("#resourceId").val()){
						setTimeout("window.location.reload(false);", 1000);
					}else{
						setTimeout("window.location.href='toResourceEdit.do?id="+resourceId+"';", 1000);
					}
					
				} else {
					top.window.alertMsg("信息提示", msg, "warning");
					setTimeout("window.location.reload(false);", 2000);
				}
			},error : function(data, status, e) {
				top.window.alertMsg("提示信息", "上传文件失败，请刷新页面后再试！", "error");
				setTimeout("window.location.reload(false);", 2000);
			}
		});
	}
}



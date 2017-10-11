/**
 * @author Hehaipeng
 * @date 2017年4月17日
 * @todo TODO
 */
$(function() {
			$("#addOwner_form").validate({
				onkeyup : false,
				// 错误信息提示位置
				errorPlacement : function(error, element) {
					error.appendTo(element.parent("div")
							.next("p.release_error_word"));
				},

				// 条件
				rules : {
					ownerName : {
						required : true,
						english_chinese : true,
						minlength : 2,
						maxlength : 20
					},
					ownerPhone : {
						required : true,
						cellphone : true
					},
					ownerCard : {
						required : true,
						IDcard : true
					},
					ownerWork : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					pId : {
						required : true
					},
					cId : {
						required : true
					},
					rId : {
						required : true
					}
				},
				// 错误信息
				messages : {
					ownerName : {
						required : "请输入业主名称",
						minlength : "请输入至少2个字符",
						maxlength : "请不要超过20个字符"
					},
					ownerCard : {
						required : "请输入身份证"
					},
					ownerWork : {
						required : "请输入工作单位",
						minlength : "请输入至少2个字符",
						maxlength : "请不要超过50个字符"
					},
					ownerPhone : {
						required : "请输入联系电话"
					},
					pId : {
						required : "请选择省份"
					},
					cId : {
						required : "请选择城市"
					},
					rId : {
						required : "请选择县区"
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

			// 如果是修改业主信息，则调用该方法，通过楼栋自动加载业主所在的房间信息
			var build = $("#build").val();
			var room = $("#room").val();
			if (build != null && build != "" && room != null && room != "") {
				// 调用获得房间的js方法
				getRoomsByBuildId(build, room);
			}
		});

function addOwner_submit() {
	// 初始化，获得所有file类型的id(name)值,放入数组中this.id this.name
	window.fileArr = []; // 定义为全局变量
	$("input[type='file']").each(function() {
				if (this.id != '' && this.id != undefined) {
					if (this.value != null && this.value != "") {
						fileArr.push(this.id);
					}
				}
			});

	var provinceId = $('#pId').val();
	var cityId = $('#cId').val();
	var regionId = $('#rId').val();

	var buildId = $('#buildId').val();
	var roomId = "";
	if(buildId != null && buildId != ""){
		roomId = $('#roomId').val()
	}

	var ownerId = $('#ownerId').val();
	var ownerName = $('#ownerName').val();
	var ownerSex = $("input[name='ownerSex']:checked").val();
	var ownerPhone = $('#ownerPhone').val();
	var ownerCard = $('#ownerCard').val();
	var ownerWork = $('#ownerWork').val();

	if (ownerSex != null && ownerSex != "") {
		if ((buildId != null && buildId != "") && (roomId == null || roomId == "")) {
			top.window.alertMsg("温馨提示", "请选择房间!", "warning");
			return null;
		}
		if ($("#addOwner_form").valid()) { // 通过验证
			$.ajaxFileUpload({
				url : 'saveOrUpdateOwner.do',
				fileElementId : fileArr, // 文件上传（多个）
				secureuri : false, // 是否需要安全协议，一般设置为false
				data : {
					ownerId : ownerId,
					ownerName : ownerName,
					ownerSex : ownerSex,
					ownerPhone : ownerPhone,
					ownerCard : ownerCard,
					ownerWork : ownerWork,

					buildId : buildId,
					roomId : roomId,
					provinceId : provinceId,
					cityId : cityId,
					regionId : regionId
				},
				type : "POST",
				dataType : "json",
				success : function(json) {
					// 判断是否成功，成功则关闭窗口，刷新界面并提示消息，失败则跳警告信息
					if (json.reflag == true) {
						top.window.showMsg("成功信息", json.infoMsg);
						if(ownerId != null && ownerId != ""){
							setTimeout("window.location.href='toOwnerList.do';", 1000);
						}else{
							setTimeout("window.location.href='editOwner.do';", 1000);
						}
					} else {
						top.window.alertMsg("温馨提示", json.infoMsg, "warning");
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

/**
 * 根据楼栋id获得房间
 */
function getRoomsByBuildId(buildId, roomId) {
	if(buildId == null || buildId == ""){
		$("#selcctRoom").empty();
		return null;
	}
	
	//如果再次选择原来的楼栋时，而重新将原来的房间也放入里面
	if(buildId == $("#build").val()){
		roomId = $("#room").val(); //再次选择时进入（后想选择原来的楼栋房间）
	}
	
	$.ajax({
				url : "../room/getRoomsByBuild.do",
				data : {
					buildId : buildId,
					roomId : roomId
				},
				dataType : "JSON",
				type : "POST",
				success : function(json) {
					if (json.reflag == true) {
						var boRooms = json.returnObj;
						$("#selcctRoom").empty();
						var element = '<select id="roomId" name="roomId">';
						element += '<option value="">请选择房间</option>';
						for (var index = 0; index < boRooms.length; index++) {
							var boRoom = boRooms[index];
							if (roomId != null && roomId != "") {
								element += '<option value="' + boRoom.roomId + '" selected = "selected">' + boRoom.roomName + '</option>';
							} else {
								element += '<option value="' + boRoom.roomId + '">' + boRoom.roomName + '</option>';
							}

						}
						element += '</select>';
						$("#selcctRoom").append(element);

					} else {
						$("#selcctRoom").empty();
						top.window.alertMsg("温馨提示", json.infoMsg, "warning");
					}
				}
			});
}


/**
 * @author Hehaipeng
 * @date 2017年3月15日
 * @todo TODO 文件下载
 * @param val
 */
function uploadMaterials(val) {
	var confirm = function() {
		window.location.href = "uploadAttachmentForOwner.do?id=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此附件?", confirm);
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 删除文件
 */
function deleteMaterials(val) {
	var confirm = function() {
		$.ajax({
					url : "deleteAttachmentForOwner.do",
					data : {
						id : val
					},
					dataType : "JSON",
					type : "POST",
					success : function(json) {
						if (json.reflag == true) {
							top.window.showMsg("成功信息", json.infoMsg);
							// 刷新页面
							setTimeout("window.location.href='editOwner.do?ownerId="+ $("#ownerId").val() + "';", 1000);
						} else {
							top.window.showMsg("错误信息", result.infoMsg);
						}
					}
				});
	}
	top.window.confirmMsg('信息确认', "确定删除此附件?", confirm);
}



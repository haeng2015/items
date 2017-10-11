/**@author Hehaipeng
 * @date 2017年4月15日
 * @todo TODO 附件动态添加
 */
 
 
/**
 * @author Hehaipeng
 * @date 2017年3月14日
 * @todo TODO 动态添加附件用全局变量
 */
var Attach;
var spnList;
var fileIndex = 0;
var spnLen = 0;

/**
 * @author Hehaipeng
 * @date 2017年3月14日
 * @todo TODO 限制文件上传数量
 */
function addFile() {
	Attach = document.getElementById("dvReadAttach");
	spnList = Attach.getElementsByTagName("SPAN");
	spnLen = spnList.length;
	if (spnLen > 3) {
		top.window.alertMsg("温馨提示", "一次只能上传4个文件!", "warning");
		document.getElementById("aAddAttachLimit").innerHTML = "<font color='red'>停止添加附件</font>";
	} else {
		fAddAttach(fileIndex++);
	}
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 添加竞价产品时，动态添加文件输入框input
 */
function fAddAttach(val) {
	var gAttchHTML = '<div>附件'
			+ (val + 1)
			+ ' ：</div><div><input type="file" id="attachfile_" >'
			+ '<a href="javascript:void(0);" onclick="fDeleteAttach(this);" id="btnDeleteReadAttach"><font color="red">删除</font></a></div><span></span>';
	Attach = document.getElementById("dvReadAttach");
	spnList = Attach.getElementsByTagName("SPAN");
	spnLen = spnList.length;
	var spn = document.createElement("DIV");
	spn.className = "qrc4";
	spn.innerHTML = gAttchHTML;
	spn.childNodes[1].childNodes[0].name = "attachfile_" + val;
	spn.childNodes[1].childNodes[0].id = "attachfile_" + val;
	Attach.appendChild(spn);
	fGetObjInputById(spn, "btnDeleteReadAttach");
	document.getElementById("aAddAttach").innerHTML = "<font color='blue'>继续添加</font>";
	Attach.style.display = "";
}

/**
 * @author Hehaipeng
 * @date 2017年3月14日
 * @todo TODO 获得输入框id
 * @param obj
 * @param id
 * @returns
 */
function fGetObjInputById(obj, id) {
	var inputList = obj.getElementsByTagName("INPUT");
	for (var i = 0; i < inputList.length; i++) {
		if (inputList[i].id == id) {
			return inputList[i];
		}
	}
	return null;
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 添加竞价产品时，取消上传输入框
 */
function fDeleteAttach(obj) {
	try {
		obj.parentNode.parentNode.parentNode
				.removeChild(obj.parentNode.parentNode);
		Attach = document.getElementById("dvReadAttach");
		spnList = Attach.getElementsByTagName("SPAN");
		spnLen = spnList.length;
		if (spnList.length == 0) {
			document.getElementById("aAddAttach").innerHTML = "<font color='green'>添加附件</font>";
			Attach.style.display = "none";
		} else {
			document.getElementById("aAddAttachLimit").innerHTML = "<a href=\"javascript:addFile();\" id=\"aAddAttach\"><font color=\"blue\">继续添加</font></a>";
		}
	} catch (exp) {
		// fDebug("fDeleteAttach",exp.description);
	}
}

/**
 * @author Hehaipeng
 * @date 2017年3月9日
 * @todo TODO 修改竞价产品时，删除上传的文件
 */
function deleteMaterials(val) {
	var confirm = function() {
		$.ajax({
					url : ".do",
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
		window.location.href = ".do?attId=" + val;
		window.open(link);
	};
	top.window.confirmMsg('信息确认', "确认下载此文件?", confirm);
}

 
 
 
 
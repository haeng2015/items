/*
 * 表格排序方法： 使用办法，调用sortTable方法，三个参数分别代表，table的id名，该列位于哪一行，排序种类（int float string）
 * 
 */
function show(id) {

	for (var i = 1; i < 10; i++) {
		document.getElementById("banner" + i).className = "bannerchild";
	}
	var oul = document.getElementById("banner" + id);
	oul.className = "showTable";
}

function away(id) {
	var oul = document.getElementById("banner" + id);
	oul.className = "bannerchild";
}

var maxToMin = true;
function convert(sValue, sDataType) {
	switch (sDataType) {
		case "int" :
			return parseInt(sValue);
		case "float" :
			return parseFloat(sValue);
		case "date" :
			return new Date(Date.parse(sValue));
		default :
			return sValue.toString();

	}
}

function generateCompareTRs(iCol, sDataType) {
	if (maxToMin) {
		return function compareTRs(oTR1, oTR2) {
			maxToMin = false;
			var vValue1 = convert(oTR1.cells[iCol].firstChild.nodeValue,
					sDataType);
			var vValue2 = convert(oTR2.cells[iCol].firstChild.nodeValue,
					sDataType);
			if (vValue1 < vValue2) {
				return -1;
			} else if (vValue1 > vValue2) {
				return 1;
			} else {
				return 0;
			}
		}
	} else {
		return function compareTRs(oTR1, oTR2) {
			maxToMin = true;
			var vValue1 = convert(oTR1.cells[iCol].firstChild.nodeValue,
					sDataType);
			var vValue2 = convert(oTR2.cells[iCol].firstChild.nodeValue,
					sDataType);
			if (vValue1 < vValue2) {
				return 1;
			} else if (vValue1 > vValue2) {
				return -1;
			} else {
				return 0;
			}
		}

	}
}

/**
 * 2: 使用办法，调用sortTable方法，三个参数分别代表，table的id名，该列位于哪一行，排序种类（int float string）
 * 
 * @param {}
 *            sTableId
 * @param {}
 *            iCol
 * @param {}
 *            sDataType
 */
function sortTable(sTableId, iCol, sDataType) {
	var oTable = document.getElementById(sTableId);
	var oTBody = oTable.tBodies[0];
	var colDataRows = oTBody.rows;
	var aTRs = new Array;
	for (var i = 0; i < colDataRows.length; i++) {
		aTRs[i] = colDataRows[i];
	}

	aTRs.sort(generateCompareTRs(iCol, sDataType));

	var oFragment = document.createDocumentFragment();

	for (var j = 0; j < aTRs.length; j++) {
		oFragment.appendChild(aTRs[j]);
	}

	oTBody.appendChild(oFragment);
	oTable.sortCol = iCol;
}

function befSubmit() {
	if (confirm("确定删除？")) {
		return true;
	} else {
		return false;
	}
}
function befRecover() {
	if (confirm("确定恢复数据？")) {
		return true;
	} else {
		return false;
	}
}

/**
 * 全部选中
 */
function checkAllCheckbox() {
	var flag = false;
	var element = document.form2.checkone;
	for (j = 0; j < element.length; j++) {
		if (element[j].checked == false) {
			for (i = 0; i < element.length; i++) {
				element[i].checked = true;
				flag = true;
			}
		}
	}
	if (flag == false) {
		for (i = 0; i < element.length; i++) {
			element[i].checked = false;
		}
	}

}

function role() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "RoleList.do?curpage=" + init;
}
function rolesearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "RoleSearchList.do?curpage=" + init;
}
function pay() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "PayList.do?curpage=" + init;
}
function paysearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "PaySearchList.do?curpage=" + init;
}
function owner() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "OwnerList.do?curpage=" + init;
}
function ownersearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "OwnerSearchList.do?curpage=" + init;
}
function member() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "MemberList.do?curpage=" + init;
}
function membersearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "MemberSearchList.do?curpage=" + init;
}
function type() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "TypeList.do?curpage=" + init;
}
function typesearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "TypeListSearch.do?curpage=" + init;
}
function charge() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "ChargeList.do?curpage=" + init;
}
function chargesearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "ChargeSearchList.do?curpage=" + init;
}
function power() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "PowerList.do?curpage=" + init;
}
function powersearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "PowerSearchList.do?curpage=" + init;
}
function build() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "BuildList.do?curpage=" + init;
}
function buildsearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "BuildListSearch.do?curpage=" + init;
}
function room() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "RoomList.do?curpage=" + init;
}
function roomsearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "RoomListSearch.do?curpage=" + init;
}
function material() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "MeterialList.do?curpage=" + init;
}
function materialsearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "MeterialListSearch.do?curpage=" + init;
}
function user() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "UserList.do?curpage=" + init;
}
function usersearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "SearchUser.do?curpage=" + init;
}
function rolepower() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "RolePowerList.do?curpage=" + init;
}
function rolepowersearch() {
	var oChange = document.getElementById("pagenumber");
	var init = oChange.value;
	window.location = "SearchRolePower.do?curpage=" + init;
}
function selectText() {
	var oTextbox1 = document.getElementById("txt1");
	oTextbox1.focus();
	oTextbox1.select();
}
var isn1 = null;
var isn2 = false;
today = new Date();
function stopit() {
	if (isn2) {
		clearTimeout(isn1);
	}
	isn2 = false;
}
function startit() {
	stopit();
	isnclock();
}
function isnclock() {
	var now = new Date();
	var hrs = now.getHours();
	var min = now.getMinutes();
	var sec = now.getSeconds();
	document.getElementById("hours").innerHTML = ""
			+ ((hrs > 12) ? hrs - 12 : hrs);
	document.getElementById("minutes").innerHTML = ((min < 10) ? "0" : "")
			+ min;
	document.getElementById("seconds").innerHTML = ((sec < 10) ? "0" : "")
			+ sec;
	isn1 = setTimeout("isnclock()", 1000);
	isn2 = true;
}
function isnArray() {
	argnr = isnArray.arguments.length
	for (var i = 0; i < argnr; i++) {
		this[i + 1] = isnArray.arguments[i];
	}
}
var isnMonths = new isnArray("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月",
		"9月", "10月", "11月", "12月");
var isnDays = new isnArray("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日");
isnDays[0] = "星期日";
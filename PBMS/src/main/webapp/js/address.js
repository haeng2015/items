/**
 * 若要在修改信息时自动加载地址下拉信息，需要在每个网页获得如下三个值
 * <input id="provinceId" name="provinceId" type="hidden" value="${requestScope.ownerVO.provinceId }" />
	<input id="cityId" name="cityId" type="hidden" value="${requestScope.ownerVO.cityId }" />
	<input id="regionId" name="regionId" type="hidden" value="${requestScope.ownerVO.regionId }" />
 */

// 若修改信息时，根据省自动加载选中市区信息地址信息（自动调用该方法）
$(function() {
			var provinceId = $("#provinceId").val();
			var cityId = $("#cityId").val();
			var regionId = $("#regionId").val();

			if (provinceId != null && provinceId != "") {
				if (cityId != null && cityId != "") {
					getCityByProvince(provinceId, cityId);
					if (regionId != null && regionId != "") {
						getRegionByCity(cityId, regionId);
					}
				}
			}

		});

/**
 * @author Hehaipeng
 * @date 2017年4月17日
 * @todo TODO 通过省id获得市级信息
 */
function getCityByProvince(pId, cityId) {
	$.ajax({
		url : "../address/getCityByProvince.do",
		data : {
			pId : pId,
			cId : cityId
		},
		dataType : "JSON",
		type : "POST",
		success : function(json) {
			if (json.reflag == true) {
				var citys = json.returnObj;
				$("#selcctCity").empty();
				$("#selcctRegion").empty();
				var element = '<select id="cId" name="cId" onchange="getRegionByCity(this.value)">';
				element += '<option value="">请选择市级</option>';
				for (var index = 0; index < citys.length; index++) {
					var city = citys[index];
					if (cityId != null && cityId != "" && city.cId == cityId) {
						element += '<option value="' + city.cId + '" selected = "selected">' + city.cName + '</option>';
					} else {
						element += '<option value="' + city.cId + '">'
								+ city.cName + '</option>';
					}
				}
				element += '</select>';
				$("#selcctCity").append(element);

			} else {
				top.window.alertMsg("温馨提示", json.infoMsg, "warning");
			}
		},
		error : function(val){
			top.window.alertMsg("错误 信息", val, "warning");
		}
	});
}

/**
 * @author Hehaipeng
 * @date 2017年4月17日
 * @todo TODO 通过市id获得区级信息
 */
function getRegionByCity(cId, regionId) {
	$.ajax({
				url : "../address/getRegionByCity.do",
				data : {
					cId : cId,
					pId : regionId
				},
				dataType : "JSON",
				type : "POST",
				success : function(json) {
					if (json.reflag == true) {
						var regions = json.returnObj;
						$("#selcctRegion").empty();
						var element = '<select id="rId" name="rId">';
						element += '<option value="">请选择区级</option>';
						for (var index = 0; index < regions.length; index++) {
							var region = regions[index];
							if (regionId != null && regionId != "" && region.rId == regionId) {
								element += '<option value="' + region.rId + '" selected = "selected">' + region.rName + '</option>';
							} else {
								element += '<option value="' + region.rId + '">' + region.rName + '</option>';
							}
						}
						element += '</select>';
						$("#selcctRegion").append(element);

					} else {
						// top.window.alertMsg("温馨提示", json.infoMsg, "warning");
					}
				}
			});
}

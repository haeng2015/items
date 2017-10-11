package com.pbms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pbms.service.AddressService;
import com.pbms.vo.AddressVO;
import com.pbms.vo.JsonVO;

@Controller
@RequestMapping("/address")
public class AddressController {
    
    private static final Logger LOGGER = Logger.getLogger(AddressController.class);
    
    @Autowired
    private AddressService addressService;
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月17日
     * @function：TODO 根据省份id查市级信息
     * @param addressVO
     * @return
     */
    @RequestMapping("/getCityByProvince")
    @ResponseBody
    public JsonVO getCityByProvince(AddressVO addressVO) {
	JsonVO json = new JsonVO();
	List<AddressVO> cityList = null;
	try {
	    cityList = this.addressService.findCitysByProvince(addressVO);
	    if (cityList != null && cityList.size() > 0) {
		json.setReflag(true);
		json.setReturnObj(cityList);
	    } else {
		json.setReflag(false);
	    }
	} catch (Exception e) {
	    LOGGER.error("AddressController -> getCityByProvince : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月17日
     * @function：TODO 根据市级id查区级信息
     * @param addressVO
     * @return
     */
    @RequestMapping("/getRegionByCity")
    @ResponseBody
    public JsonVO getRegionByCity(AddressVO addressVO) {
	JsonVO json = new JsonVO();
	List<AddressVO> regionList = null;
	try {
	    regionList = this.addressService.findRegionsByCity(addressVO);
	    if (regionList != null && regionList.size() > 0) {
		json.setReflag(true);
		json.setReturnObj(regionList);
	    } else {
		json.setReflag(false);
	    }
	} catch (Exception e) {
	    LOGGER.error("AddressController -> getRegionByCity : " + e.getMessage());
	}
	return json;
    }
    
}

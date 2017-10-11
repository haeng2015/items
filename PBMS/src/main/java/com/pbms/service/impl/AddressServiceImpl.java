package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoAddressCity;
import com.pbms.pojo.BoAddressProvince;
import com.pbms.pojo.BoAddressRegion;
import com.pbms.service.AddressService;
import com.pbms.vo.AddressVO;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    /**
     * namespace常量：省
     */
    public static final String PROVINCE_NAMESPACE = "boAddressProvince";
    /**
     * namespace常量：市
     */
    public static final String CITY_NAMESPACE = "boAddressCity";
    /**
     * namespace常量：区
     */
    public static final String REGION_NAMESPACE = "boAddressRegino";
    
    @Autowired
    private BaseDao<BoAddressProvince> provinceDao;
    @Autowired
    private BaseDao<BoAddressCity> cityDao;
    @Resource
    private BaseDao<BoAddressRegion> regionDao;
    
    public BaseDao<BoAddressRegion> getRegionDao() {
	return regionDao;
    }
    
    public void setRegionDao(BaseDao<BoAddressRegion> regionDao) {
	this.regionDao = regionDao;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO vo转bo(Province)
     * @return
     */
    @Override
    public BoAddressProvince voToProvince(AddressVO addressVO) {
	BoAddressProvince province = new BoAddressProvince();
	if (addressVO != null && !"".equals(addressVO)) {
	    BeanUtils.copyProperties(addressVO, province);
	    return province;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO vo转bo(city)
     * @return
     */
    @Override
    public BoAddressCity voToCity(AddressVO addressVO) {
	BoAddressCity city = new BoAddressCity();
	if (addressVO != null && !"".equals(addressVO)) {
	    BeanUtils.copyProperties(addressVO, city);
	    return city;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO vo转bo(region)
     * @return
     */
    @Override
    public BoAddressRegion voToRegion(AddressVO addressVO) {
	BoAddressRegion region = new BoAddressRegion();
	if (addressVO != null && !"".equals(addressVO)) {
	    BeanUtils.copyProperties(addressVO, region);
	    return region;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO bo转vo
     * @param province
     * @param city
     * @param region
     * @return
     */
    @Override
    public AddressVO boToVo(BoAddressProvince province, BoAddressCity city, BoAddressRegion region) {
	AddressVO address = new AddressVO();
	if (province != null && !"".equals(province)) {
	    BeanUtils.copyProperties(province, address);
	}
	if (city != null && !"".equals(city)) {
	    BeanUtils.copyProperties(city, address);
	}
	if (region != null && !"".equals(region)) {
	    BeanUtils.copyProperties(region, address);
	}
	return address;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO 查所有province对象
     * @return
     */
    @Override
    public List<AddressVO> findAllProvinces() {
	this.provinceDao.setNamespace(PROVINCE_NAMESPACE); // 设置namespace值
	List<BoAddressProvince> provinces = this.provinceDao.selects();
	List<AddressVO> addresses = new ArrayList<AddressVO>();
	for (BoAddressProvince province : provinces) {
	    addresses.add(this.boToVo(province, null, null));
	}
	return addresses;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO 根据省份id查市
     * @return
     */
    @Override
    public List<AddressVO> findCitysByProvince(AddressVO addressVO) {
	List<BoAddressCity> citys = null;
	BoAddressProvince province = null;
	List<AddressVO> addresses = new ArrayList<AddressVO>();
	if (addressVO != null && !"".equals(addressVO)) {
	    this.provinceDao.setNamespace(PROVINCE_NAMESPACE); // 设置namespace值
	    province = this.provinceDao.get(addressVO.getpId());
	    
	    this.cityDao.setNamespace(CITY_NAMESPACE); // 设置namespace值
	    citys = this.cityDao.selectFks(province);
	    for (BoAddressCity city : citys) {
		addresses.add(this.boToVo(province, city, null));
	    }
	}
	return addresses;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO 根据市id查区
     * @return
     */
    @Override
    public List<AddressVO> findRegionsByCity(AddressVO addressVO) {
	List<BoAddressRegion> regions = null;
	BoAddressProvince province = null;
	BoAddressCity city = null;
	List<AddressVO> addresses = new ArrayList<AddressVO>();
	if (addressVO != null && !"".equals(addressVO)) {
	    // 查找省信息
	    if (addressVO.getpId() != null && !"".equals(addressVO.getpId())) {
		this.provinceDao.setNamespace(PROVINCE_NAMESPACE); // 设置namespace值
		province = this.provinceDao.get(addressVO.getpId());
	    }
	    
	    // 查找市信息
	    if (addressVO.getcId() != null && !"".equals(addressVO.getcId())) {
		this.cityDao.setNamespace(CITY_NAMESPACE); // 设置namespace值
		city = this.cityDao.get(addressVO.getcId());
		
		// 查找县区信息
		this.regionDao.setNamespace(REGION_NAMESPACE); // 设置namespace值
		regions = this.regionDao.selectFks(city);
		if (regions != null) {
		    for (BoAddressRegion region : regions) {
			addresses.add(this.boToVo(province, city, region));
		    }
		} else {
		    addresses.add(this.boToVo(province, city, null));
		}
	    }
	}
	return addresses;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月15日
     * @function：TODO 查找地址名称
     * @return
     */
    @Override
    public AddressVO findAddressName(AddressVO addressVO) {
	BoAddressProvince province = null;
	BoAddressCity city = null;
	BoAddressRegion region = null;
	
	if (addressVO != null && !"".equals(addressVO)) {
	    // 查找省信息
	    if (addressVO.getpId() != null && !"".equals(addressVO.getpId())) {
		this.provinceDao.setNamespace(PROVINCE_NAMESPACE); // 设置namespace值
		province = this.provinceDao.get(addressVO.getpId());
	    }
	    
	    // 查找市信息
	    if (addressVO.getcId() != null && !"".equals(addressVO.getcId())) {
		this.cityDao.setNamespace(CITY_NAMESPACE); // 设置namespace值
		city = this.cityDao.get(addressVO.getcId());
	    }
	    
	    // 查找区信息
	    if (addressVO.getrId() != null && !"".equals(addressVO.getrId())) {
		this.regionDao.setNamespace(REGION_NAMESPACE); // 设置namespace值
		region = this.regionDao.get(addressVO.getrId());
	    }
	    
	    // 转换为vo对象
	    return this.boToVo(province, city, region);
	}
	return null;
    }

    /**
     * @author：Hehaipeng
     * @date：2017年5月16日
     * @function：TODO 根据id查找（区级）
     * @param rId
     * @return
     */
    @Override
    public BoAddressRegion findBoAddressRegionById(String rId) {
	if (rId != null && !"".equals(rId)) {
	    this.regionDao.setNamespace(REGION_NAMESPACE);
	    return this.regionDao.get(rId);
	}
	return null;
    }

    /**
     * @author：Hehaipeng
     * @date：2017年5月16日
     * @function：TODO 根据id查找（市级）
     * @param rId
     * @return
     */
    @Override
    public BoAddressCity findBoAddressCityById(String cId) {
	if (cId != null && !"".equals(cId)) {
	    this.cityDao.setNamespace(CITY_NAMESPACE);
	    return this.cityDao.get(cId);
	}
	return null;
    }

    /**
     * @author：Hehaipeng
     * @date：2017年5月16日
     * @function：TODO 根据id查找（省级）
     * @param rId
     * @return
     */
    @Override
    public BoAddressProvince findBoAddressProvinceById(String pId) {
	if (pId != null && !"".equals(pId)) {
	    this.provinceDao.setNamespace(PROVINCE_NAMESPACE);
	    return this.provinceDao.get(pId);
	}
	return null;
    }
    
}

package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoAddressCity;
import com.pbms.pojo.BoAddressProvince;
import com.pbms.pojo.BoAddressRegion;
import com.pbms.vo.AddressVO;

public interface AddressService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO vo转bo(Province)
     * @return
     */
    public BoAddressProvince voToProvince(AddressVO addressVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO vo转bo(city)
     * @return
     */
    public BoAddressCity voToCity(AddressVO addressVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO vo转bo(region)
     * @return
     */
    public BoAddressRegion voToRegion(AddressVO addressVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO bo转vo
     * @param province
     * @param city
     * @param region
     * @return
     */
    public AddressVO boToVo(BoAddressProvince province, BoAddressCity city, BoAddressRegion region);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO 查所有province对象
     * @return
     */
    public List<AddressVO> findAllProvinces();
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO 根据省份查市
     * @return
     */
    public List<AddressVO> findCitysByProvince(AddressVO addressVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月16日
     * @function：TODO 根据市查区
     * @return
     */
    public List<AddressVO> findRegionsByCity(AddressVO addressVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月15日
     * @function：TODO 查找地址名称
     * @return
     */
    public AddressVO findAddressName(AddressVO addressVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月16日
     * @function：TODO 根据id查找（区级）
     * @param rId
     * @return
     */
    public BoAddressRegion findBoAddressRegionById(String rId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月16日
     * @function：TODO 根据id查找（市级）
     * @param rId
     * @return
     */
    public BoAddressCity findBoAddressCityById(String cId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月16日
     * @function：TODO 根据id查找（省级）
     * @param rId
     * @return
     */
    public BoAddressProvince findBoAddressProvinceById(String pId);
}

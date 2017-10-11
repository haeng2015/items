package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoCharge;
import com.pbms.service.BoChargeService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.ChargeVO;

@Service("boChargeService")
public class BoChargeServiceImpl implements BoChargeService {
    
    /**
     * charge的mapper文件的namespace常量值：boChargeMapper
     */
    public static final String NAMESPACE = "boChargeMapper"; // xml文件中的namespac值
    @Resource
    private BaseDao<BoCharge> baseDao;
    
    public BaseDao<BoCharge> getBaseDao() {
	return baseDao;
    }
    
    public void setBaseDao(BaseDao<BoCharge> baseDao) {
	this.baseDao = baseDao;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    @Override
    public ChargeVO boToVo(BoCharge boCharge) {
	if (boCharge != null && !"".equals(boCharge)) {
	    ChargeVO chargeVO = new ChargeVO();
	    BeanUtils.copyProperties(boCharge, chargeVO);
	    return chargeVO;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    @Override
    public BoCharge voToBo(ChargeVO chargeVO) {
	if (chargeVO != null && !"".equals(chargeVO)) {
	    BoCharge boCharge = new BoCharge();
	    BeanUtils.copyProperties(chargeVO, boCharge);
	    return boCharge;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    @Override
    public BoCharge findBoChargeById(Integer chargeId) {
	if (chargeId != null && !"".equals(chargeId)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.get(chargeId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载列表
     * @param pageContext
     * @return
     */
    @Override
    public DataGrid loadChargeList(DataGridPage pageContext) {
	if (pageContext != null && !"".equals(pageContext)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    DataGrid dataGrid = this.baseDao.pageDataGrid(pageContext);
	    
	    if (dataGrid != null && !"".equals(dataGrid)) {
		@SuppressWarnings("unchecked")
		List<BoCharge> boChargeList = (List<BoCharge>) dataGrid.getRows();
		List<ChargeVO> chargeVOs = new ArrayList<ChargeVO>();
		// bo转vo
		for (BoCharge boCharge : boChargeList) {
		    chargeVOs.add(this.boToVo(boCharge));
		}
		dataGrid.setRows(chargeVOs);
		return dataGrid;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新对象
     * @return
     */
    @Override
    public Integer saveOrUpdateCharge(ChargeVO chargeVO) {
	Integer result = 0;
	BoCharge boCharge = new BoCharge();
	if (chargeVO != null && !"".equals(chargeVO)) {
	    boCharge = this.voToBo(chargeVO);
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值(必须在voToBo函数下面，因为该方法中调用了其他接口函数)
	    
	    // 更新
	    if (boCharge.getChargeId() != null && !"".equals(boCharge.getChargeId())) {
		result = this.baseDao.updateSelective(boCharge);
	    } else { // 添加
		result = this.baseDao.insertSelective(boCharge);
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id删除对象
     * @return
     */
    @Override
    public Integer deleteCharge(List<Integer> chargeIds) {
	Integer result = 0;
	if (chargeIds != null && chargeIds.size() > 0) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    for (Integer chargeId : chargeIds) {
		result = this.baseDao.delete(chargeId);
	    }
	}
	return result;
    }
    
}

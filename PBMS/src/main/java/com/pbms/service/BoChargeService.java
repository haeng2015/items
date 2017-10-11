package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoCharge;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.ChargeVO;

public interface BoChargeService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    public ChargeVO boToVo(BoCharge boCharge);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    public BoCharge voToBo(ChargeVO chargeVO);
    

    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    public BoCharge findBoChargeById(Integer chargeId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载列表
     * @param pageContext
     * @return
     */
    public DataGrid loadChargeList(DataGridPage pageContext);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新对象
     * @return
     */
    public Integer saveOrUpdateCharge(ChargeVO chargeVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id删除对象
     * @return
     */
    public Integer deleteCharge(List<Integer> chargeIds);
    
    
}

package com.pbms.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.pbms.pojo.BoUser;
import com.pbms.pojo.UserRole;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.UserVO;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:He.hp
 * @创建日期:2017年3月22日
 * @说明：用户service接口
 */
public interface BoUserService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO bo转vo
     * @param userRole
     * @return
     */
    public UserVO boToVo(BoUser boUser, UserRole userRole);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO vo转bo
     * @param userVO
     * @return
     */
    public BoUser voToBo(UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 检查用户名是否已经存在
     * @param userVO
     * @return
     */
    public BoUser findBoUserByLoginName(UserVO userVO);
    
    /**
     * @author：He.hp
     * @date：2017年3月21日
     * @function：TODO 根据id查找user信息
     * @param userId
     * @return
     * @throws Exception
     */
    public BoUser findBoUserById(Integer userId) throws Exception;
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月23日
     * @function：TODO 根据用户名和密码查找用户信息
     * @param userVO
     * @return
     */
    public UserVO findBoUserByNameAndPwd(UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 查找所有用户（带分页）
     * @return
     */
    public PagingResult<UserVO> findAllBoUser(PageEntity param);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 加载用户列表信息（datagrid数据格式）
     * @param dataGridPage
     * @return
     */
    public DataGrid loadUserListInfo(DataGridPage dataGridPage);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月3日
     * @function：TODO 添加或更新用户
     * @param userVo
     * @param fileMap 用户照片
     * @return
     */
    public BoUser addOrUpdateUser(UserVO userVo,Map<String, MultipartFile> fileMap, HttpServletRequest request);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月20日
     * @function：TODO 根据id删除用户
     * @param userId
     * @return
     */
    public Integer deleteBoUserById(List<String> userIds);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月27日
     * @function：TODO 根据用户id查找其权限url请求路径(set去除重复路径)
     * @param userId
     * @return
     */
    public Set<String> findBoAuthUrlByBoUserId(Integer userId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 根据类型查找超级管理员
     * @param type
     * @return
     */
    public BoUser findAdminByType(String type);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月14日
     * @function：TODO 验证用户信息（修改密码用）
     * @param userVO
     * @return
     */
    public BoUser validateUserInfo(UserVO userVO);
}

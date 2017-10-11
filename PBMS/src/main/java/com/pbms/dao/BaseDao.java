package com.pbms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;

@MapperScan
public interface BaseDao<T> {
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 用于传入namespace值
     * @return
     */
    public abstract void setNamespace(String namespace);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 新增实体
     * @param entity
     * @return
     */
    public abstract Integer insert(T entity);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 选择对象属性插入
     * @param roleId
     * @return
     */
    public abstract Integer insertSelective(T entity);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月12日
     * @function：TODO 保存对象后返回对象的id（主键自增时） <!-- 配置插入对象后返回该对象的id：（mysql）
     *                “keyProperty”表示返回的id要保存到对象的那个属性中
     *                “useGeneratedKeys”表示主键id为自增长模式 -->
     * 
     *                <!-- oracle保存对象后获得主键方式： <insert id="insert"
     *                parameterType="com.test.User"> <selectKey
     *                resultType="INTEGER" order="BEFORE" keyProperty="userId">
     *                SELECT SEQ_USER.NEXTVAL as userId from DUAL </selectKey>
     *                insert into user (user_id, user_name, modified, state)
     *                values (#{userId,jdbcType=INTEGER},
     *                #{userName,jdbcType=VARCHAR},
     *                #{modified,jdbcType=TIMESTAMP}, #{state,jdbcType=INTEGER})
     *                </insert> -->
     * @param entity
     * @return
     */
    public abstract Object insertSelectiveReturnId(T entity);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 批量插入
     * @param list
     * @return
     */
    public abstract Integer insertBatch(final List<T> lists);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO 删除对象（通过致一字段为1，以此表示该对象已被删除）
     * @return
     */
    public abstract Integer isDeleted(Object primaryKey);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO 批量删除对象（通过致一字段为1，以此表示该对象已被删除）
     * @return
     */
    public Integer isDeletedBatch(List<Object> lists);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 根据主键删除
     * @param roleId
     * @return 删除的对象个数，正常情况=1
     */
    public abstract Integer delete(Object primaryKey);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 根据外键删除对象
     * @param roleId
     * @return 删除的对象个数，正常情况=1
     */
    public abstract Integer deleteFK(Object object);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 删除符合条件的记录
     * @param param
     *            用于产生SQL的参数值，包括WHERE条件（其他参数内容不起作用）
     * @return
     */
    public abstract Integer deleteParam(Map<String, Object> params);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 批量删除
     * @param list
     * @return
     */
    public abstract Integer deleteBatch(final List<Object> lists);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 修改一个实体对象，修改全部字段(传入对象id和需要修改的字段值)
     * @param entity
     * @return 修改的对象个数，正常情况=1
     */
    public abstract Integer update(T entity);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO 修改一个实体对象(选择更新字段）
     * @param entity
     * @return
     */
    public abstract Integer updateSelective(T entity);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 修改符合条件的记录(适合于一次性把多条记录的某些字段值设置为新值（定值）的情况，比如修改符合条件的记录的状态字段
     *                或把一条记录的个别字段的值修改为新值（定值），此时要把条件设置为该记录的主键)
     * @param param
     *            用于产生SQL的参数值，包括WHERE条件、目标字段和新值等
     * @return 修改的记录个数，用于判断修改是否成功
     */
    public abstract Integer updateParam(Map<String, Object> params);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 批量修改
     * @param list
     * @return
     */
    public abstract Integer updateBatch(final List<T> list);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 按主键取记录
     * @param primaryKey
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null
     */
    public abstract T get(Object primaryKey);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 取全部记录
     * @return 全部记录实体对象的List
     */
    public abstract List<T> selects();
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 按条件查询记录
     * @param param
     *            查询条件参数，包括WHERE条件、分页条件、排序条件
     * @return 符合条件记录的实体对象的List
     */
    public abstract List<T> selectParam(Map<String, Object> params);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 根据对象查询实体
     * @param object
     * @return
     */
    public abstract List<T> selectObiects(T object);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 根据外键查询实体(批量)
     * @param object
     * @return
     */
    public abstract List<T> selectFks(Object object);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 根据外键查询实体
     * @param object
     * @return
     */
    public abstract T selectFk(Object object);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 通过实体对象参数查询符合条件的记录数
     * @return 整表总记录数
     */
    public abstract Integer count(T object);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 通过map参数查询符合条件的记录数
     * @param param
     *            单个查询条件参数，包括WHERE条件（其他参数内容不起作用）。此参数设置为null，则相当于count()
     * @return
     */
    public abstract Integer countParam(Map<String, Object> params);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 获得总记录数（分页）
     * @param pageNo
     * @param pageSize
     * @return
     */
    public abstract Integer getCountPaged(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 按条件查询记录，并处理成分页结果
     * @param param
     *            查询条件参数，包括WHERE条件、分页条件、排序条件
     * @return PaginationResult对象，包括（符合条件的）总记录数、页实体对象List等
     */
    public abstract PagingResult<T> selectPagination(PageEntity param);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 获得数据表格
     * @param object
     * @return
     */
    public abstract DataGrid pageDataGrid(DataGridPage pageContext);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 获得分页后的实体对象列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    public abstract List<T> getEntityByPager(Class<T> tClass, @Param("pageNo") Integer pageNo,
	    @Param("pageSize") Integer pageSize);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 清空表，比delete具有更高的效率，而且是从数据库中物理删除（delete是逻辑删除，被删除的记录依然占有空间）
     * @return <strong>此方法一定要慎用！</strong>
     */
    public abstract Integer truncate();
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 日志打印
     * @param sqlId
     * @param param
     */
    public abstract void printLog(Object primaryKey, Object params);
    
}

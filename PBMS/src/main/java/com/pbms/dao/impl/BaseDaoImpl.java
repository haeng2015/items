package com.pbms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.pbms.dao.BaseDao;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月23日
 * @说明：baseDAO的实现基类(继承SqlSessionDaoSupport获得SqlSession)
 */
@Repository("baseDao")
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {
    // mapper.xml中的namespace
    private String namespace = "";
    
    // sqlmap.xml定义文件中对应的sql id
    /**
     * 插入对象：全部字段（实体对象参数）
     */
    public static final String SQLID_INSERT = "insert";
    /**
     * 插入对象：部分字段（实体对象参数）
     */
    public static final String SQLID_INSERT_SELECTIVE = "insertSelective";
    /**
     * 插入对象：部分字段（实体对象参数,在主键自增情况下，返回插入对象的id）
     */
    public static final String SQLID_INSERT_SELECTIVE_RETURNID = "insertSelectiveReturnId";
    /**
     * 插入对象：批量插入（list实体对象参数）
     */
    public static final String SQLID_INSERT_BATCH = "insertBatch";
    
    /**
     * 删除对象:通过致isDeleted字段为1，以此表示该对象已被删除(0表示未删除、1表示已删除)
     */
    public static final String SQLID_IS_DELETED = "isDeleted";
    /**
     * 删除对象：根据主键对象删除（任意类型参数）
     */
    public static final String SQLID_DELETE = "delete";
    /**
     * 删除对象：根据外键对象删除（任意类型参数）
     */
    public static final String SQLID_DELETE_FK = "deleteFK";
    /**
     * 删除对象：语句参删除(map参数)
     */
    public static final String SQLID_DELETE_PARAM = "deleteParam";
    /**
     * 删除对象：批量删除（list任意类型参数）
     */
    public static final String SQLID_DELETE_BATCH = "deleteBatch";
    
    /**
     * 更新对象：全部字段（实体对象参数）
     */
    public static final String SQLID_UPDATE = "update";
    /**
     * 更新对象：部分字段（实体对象参数）
     */
    public static final String SQLID_UPDATE_SELECTIVE = "updateSelective";
    /**
     * 更新对象：语句更新（map参数）
     */
    public static final String SQLID_UPDATE_PARAM = "updateParam";
    /**
     * 更新对象：批量更新（list实体对象参数）
     */
    public static final String SQLID_UPDATE_BATCH = "updateBatch";
    
    /**
     * 获得对象：主键查询（主键字段任意类型参数）
     */
    public static final String SQLID_GET = "get";
    /**
     * 获得对象：获得全部对象（无参）
     */
    public static final String SQLID_SELECTS = "selects";
    /**
     * 获得对象：获得部分对象（map参数）
     */
    public static final String SQLID_SELECT_PARAM = "selectParam";
    /**
     * 获得对象：获得部分对象（实体对象参数）
     */
    public static final String SQLID_SELECT_OBJECTS = "selectObiects";
    /**
     * 获得对象：获得部分对象（任意类型参数）
     */
    public static final String SQLID_SELECT_FK = "selectFk";
    /**
     * 获得对象：获得部分对象/批量（任意类型参数）
     */
    public static final String SQLID_SELECT_FKS = "selectFks";
    
    /**
     * 获得总记录数：为RowBounds对象的分页提供（实体对象参数）
     */
    public static final String SQLID_COUNT = "count";
    /**
     * 获得总记录数：为RowBounds对象的分页提供（map参数）
     */
    public static final String SQLID_COUNT_PARAM = "countParam";
    /**
     * 获得记录数：获得区间数记录（pageNo第几页数和pageSize每页多少数）
     */
    public static final String SQLID_COUNT_PAGED = "getCountPaged";
    
    /**
     * 获得分页对象：通过传入第几页和每页多少条后，通过RowBounds对象分页（PageEntity对象参数，其中包含了分页各参数）
     */
    public static final String SQLID_SELECT_PAGINATION = "selectPagination";
    /**
     * 获得分页对象：获得分页后的DataGrid对象，供easyUI框架使用(PageContext对象参数，其中包含了分页各参数)
     */
    public static final String SQLID_PAGE_DATAGRID = "pageDataGrid";
    /**
     * 获得分页对象：通过传入实体对象和分页参数，以获得分页对象
     */
    public static final String SQLID_GET_ENTITY_BYPAGER = "getEntityByPager";
    /**
     * 完全清空表中对象：从数据库中物理删除
     */
    public static final String SQLID_TRUNCATE = "truncate";
    
    // @Resource(name = "sqlSessionFactory")
    @Resource(name = "sqlSessionTemplate")
    public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    
    // 获得本类class名， 即 ActivityDaoImpl
    public String getSimpleNameSpace() {
	return this.getClass().getSimpleName();
    }
    
    // 获得本类class全文件名， 包名.class名 即com.xxx.cms.activity.dao.impl.ActivityDaoImpl
    // public String getNameSpace() {
    // return this.getClass().getName();
    // }
    
    public String getNamespace() {
	return namespace;
    }
    
    public BaseDaoImpl() {
	super();
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 用于传入namespace值
     * @return
     */
    @Override
    public void setNamespace(String namespase) {
	this.namespace = namespase;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 新增实体
     * @param entity
     * @return
     */
    @Override
    public Integer insert(T entity) {
	Integer rows = 0;
	try {
	    rows = this.getSqlSession().insert(namespace + "." + SQLID_INSERT, entity);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 选择对象属性插入
     * @param roleId
     * @return
     */
    @Override
    public Integer insertSelective(T entity) {
	Integer rows = 0;
	try {
	    if (entity != null && !"".equals(entity)) {
		rows = this.getSqlSession().insert(namespace + "." + SQLID_INSERT_SELECTIVE, entity);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月12日
     * @function：TODO 保存对象后返回对象的id（主键自增时）
     * @param entity
     * @return
     */
    @Override
    public Object insertSelectiveReturnId(T entity) {
	Object rows = 0;
	try {
	    if (entity != null && !"".equals(entity)) {
		rows = this.getSqlSession().insert(namespace + "." + SQLID_INSERT_SELECTIVE_RETURNID, entity);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 批量插入
     * @param list
     * @return
     */
    @Override
    public Integer insertBatch(List<T> lists) {
	Integer rows = 0;
	try {
	    if (lists.size() > 0 && !lists.isEmpty()) {
		rows = this.getSqlSession().insert(namespace + "." + SQLID_INSERT_BATCH, lists);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO 删除对象（通过致一字段为1，以此表示该对象已被删除）
     * @return
     */
    @Override
    public Integer isDeleted(Object primaryKey) {
	Integer rows = 0;
	try {
	    rows = this.getSqlSession().update(namespace + "." + SQLID_IS_DELETED, primaryKey);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO 删除对象（通过致一字段为1，以此表示该对象已被删除）
     * @return
     */
    @Override
    public Integer isDeletedBatch(List<Object> lists) {
	Integer rows = 0;
	try {
	    rows = this.getSqlSession().update(namespace + "." + SQLID_IS_DELETED, lists);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 根据主键删除
     * @param roleId
     * @return 删除的对象个数，正常情况=1
     */
    @Override
    public Integer delete(Object primaryKey) {
	Integer rows = 0;
	try {
	    if (primaryKey != null && !"".equals(primaryKey)) {
		rows = this.getSqlSession().delete(namespace + "." + SQLID_DELETE, primaryKey);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 根据外键删除对象
     * @param roleId
     * @return 删除的对象个数，正常情况=1
     */
    @Override
    public Integer deleteFK(Object object) {
	Integer rows = 0;
	try {
	    if (object != null && !"".equals(object)) {
		rows = this.getSqlSession().delete(namespace + "." + SQLID_DELETE_FK, object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 删除符合条件的记录
     * @param param
     *            用于产生SQL的参数值，包括WHERE条件（其他参数内容不起作用）
     * @return
     */
    @Override
    public Integer deleteParam(Map<String, Object> params) {
	Integer rows = 0;
	try {
	    if (params.size() > 0 && !params.isEmpty()) {
		rows = this.getSqlSession().delete(namespace + "." + SQLID_DELETE_PARAM, params);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 批量删除
     * @param list
     * @return
     */
    @Override
    public Integer deleteBatch(List<Object> lists) {
	Integer rows = 0;
	try {
	    if (lists.size() > 0 && !lists.isEmpty()) {
		rows = this.getSqlSession().delete(namespace + "." + SQLID_DELETE_BATCH, lists);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 修改一个实体对象
     * @param entity
     * @return 修改的对象个数，正常情况=1
     */
    @Override
    public Integer update(T entity) {
	Integer rows = 0;
	try {
	    if (entity != null && !"".equals(entity)) {
		rows = this.getSqlSession().update(namespace + "." + SQLID_UPDATE, entity);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO 修改一个实体对象(选择更新字段）
     * @param entity
     * @return
     */
    @Override
    public Integer updateSelective(T entity) {
	Integer rows = 0;
	try {
	    if (entity != null && !"".equals(entity)) {
		rows = this.getSqlSession().update(namespace + "." + SQLID_UPDATE_SELECTIVE, entity);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 修改符合条件的记录(适合于一次性把多条记录的某些字段值设置为新值（定值）的情况，比如修改符合条件的记录的状态字段
     *                或把一条记录的个别字段的值修改为新值（定值），此时要把条件设置为该记录的主键)
     * @param param
     *            用于产生SQL的参数值，包括WHERE条件、目标字段和新值等
     * @return 修改的记录个数，用于判断修改是否成功
     */
    @Override
    public Integer updateParam(Map<String, Object> params) {
	Integer rows = 0;
	try {
	    if (params.size() > 0 && !params.isEmpty()) {
		rows = this.getSqlSession().update(namespace + "." + SQLID_UPDATE_PARAM, params);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 批量修改
     * @param list
     * @return
     */
    @Override
    public Integer updateBatch(List<T> lists) {
	Integer rows = 0;
	try {
	    if (lists.size() > 0 && !lists.isEmpty()) {
		for (T t : lists) {
		    rows = rows + this.getSqlSession().update(namespace + "." + SQLID_UPDATE_BATCH, t);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 按主键取记录
     * @param primaryKey
     * @return 记录实体对象，如果没有符合主键条件的记录，则返回null
     */
    @Override
    public T get(Object primaryKey) {
	try {
	    if (primaryKey != null && !"".equals(primaryKey)) {
		return this.getSqlSession().selectOne(namespace + "." + SQLID_GET, primaryKey);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 取全部记录
     * @return 全部记录实体对象的List
     */
    @Override
    public List<T> selects() {
	try {
	    return this.getSqlSession().selectList(namespace + "." + SQLID_SELECTS);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 按条件查询记录
     * @param param
     *            查询条件参数，包括WHERE条件、分页条件、排序条件
     * @return 符合条件记录的实体对象的List
     */
    @Override
    public List<T> selectParam(Map<String, Object> params) {
	try {
	    if (params.size() > 0 && !params.isEmpty()) {
		return this.getSqlSession().selectList(namespace + "." + SQLID_SELECT_PARAM, params);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 根据对象查询实体
     * @param object
     * @return
     */
    @Override
    public List<T> selectObiects(T object) {
	try {
	    if (object != null && !"".equals(object)) {
		return this.getSqlSession().selectList(namespace + "." + SQLID_SELECT_OBJECTS, object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 根据外键查询实体
     * @param object
     * @return
     */
    @Override
    public T selectFk(Object object) {
	try {
	    if (object != null && !"".equals(object)) {
		return this.getSqlSession().selectOne(namespace + "." + SQLID_SELECT_FK, object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 根据外键查询实体(批量)
     * @param object
     * @return
     */
    @Override
    public List<T> selectFks(Object object) {
	try {
	    if (object != null && !"".equals(object)) {
		return this.getSqlSession().selectList(namespace + "." + SQLID_SELECT_FKS, object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 查询整表总记录数
     * @return 整表总记录数
     */
    @Override
    public Integer count(T object) {
	Integer result = 0;
	try {
	    result = this.getSqlSession().selectOne(namespace + "." + SQLID_COUNT, object);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 查询符合条件的记录数(分页总数)
     * @param param
     *            查询条件参数，包括WHERE条件（其他参数内容不起作用）。此参数设置为null，则相当于count()
     * @return
     */
    @Override
    public Integer countParam(Map<String, Object> params) {
	Integer result = 0;
	try {
	    result = this.getSqlSession().selectOne(namespace + "." + SQLID_COUNT_PARAM, params);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 获得总记录数（分页）
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Integer getCountPaged(Integer pageNo, Integer pageSize) {
	Integer result = 0;
	try {
	    result = this.getSqlSession().selectOne(namespace + "." + SQLID_COUNT_PAGED);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 按条件查询记录，并处理成分页结果
     * @param param
     *            查询条件参数，包括WHERE条件、分页条件、排序条件
     * @return PaginationResult对象，包括（符合条件的）总记录数、页实体对象List等
     */
    @Override
    public PagingResult<T> selectPagination(PageEntity pageEntity) {
	try {
	    if (pageEntity != null && !"".equals(pageEntity)) {
		Integer pageNo = pageEntity.getPageNo() == null || "".equals(pageEntity.getPageNo()) ? 1 : pageEntity
			.getPageNo(); // 默认为第一页
		Integer pageSize = pageEntity.getPageSize() == null || "".equals(pageEntity.getPageSize()) ? 5
			: pageEntity.getPageSize(); // 默认每页5条
		
		RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
		String orderColumn = pageEntity.getOrderColumn(); // 要排序的列
		
		Map<String, Object> param = pageEntity.getParams();
		if (param != null) {
		    if (orderColumn != null && !"".equals(orderColumn)) {
			param.put("orderColumn", pageEntity.getOrderColumn());
		    } else {
			// param.put("orderColumn", param.get(orderColumn));
			// //设置默认排序列
		    }
		    param.put("orderTurn", pageEntity.getOrderTurn());
		} else {
		    param = new HashMap<String, Object>();
		    param.put("orderColumn", pageEntity.getOrderColumn());
		    param.put("orderTurn", pageEntity.getOrderTurn());
		}
		
		List<T> resultList = this.getSqlSession().selectList(
			this.getNamespace() + "." + SQLID_SELECT_PAGINATION, param, rowBounds);
		// 调用count方法,计算总记录数
		Integer totalSize = this.countParam(pageEntity.getParams());
		
		PagingResult<T> pagingResult = new PagingResult<T>();
		pagingResult.setCurrentPage(pageNo);
		if (totalSize > pageSize) {
		    pagingResult.setTotalPage(totalSize / pageSize + 1);
		} else {
		    pagingResult.setTotalPage(1);
		}
		pagingResult.setTotalSize(totalSize);
		pagingResult.setResultList(resultList);
		return pagingResult;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 获得数据表格
     * @param object
     * @return
     */
    @Override
    public DataGrid pageDataGrid(DataGridPage dataGridPage) {
	DataGrid dataGrid = new DataGrid();
	try {
	    if (dataGridPage != null && !"".equals(dataGridPage)) {
		
		RowBounds rowBounds = new RowBounds((dataGridPage.getPage() - 1) * dataGridPage.getRows(),
			dataGridPage.getRows());
		Map<String, Object> param = dataGridPage.getParams();
		if (param != null) {
		    param.put("orderColumn", dataGridPage.getOrder());
		    param.put("orderTurn", dataGridPage.getSort());
		} else {
		    param = new HashMap<String, Object>();
		    param.put("orderColumn", dataGridPage.getOrder());
		    param.put("orderTurn", dataGridPage.getSort());
		}
		
		List<T> resultList = this.getSqlSession().selectList(this.getNamespace() + "." + SQLID_PAGE_DATAGRID,
			param, rowBounds);
		
		// 调用count方法,计算总记录数
		dataGrid.setTotal(this.countParam(dataGridPage.getParams()));
		
		// 将经分页查到的对象放入list中
		dataGrid.setRows(resultList);
		
		return dataGrid;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 获得分页后的实体对象列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<T> getEntityByPager(Class<T> tClass, Integer pageNo, Integer pageSize) {
	try {
	    return this.getSqlSession().selectList(namespace + "." + SQLID_GET_ENTITY_BYPAGER, tClass,
		    new RowBounds(0, 15));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 清空表，比delete具有更高的效率，而且是从数据库中物理删除（delete是逻辑删除，被删除的记录依然占有空间）
     * @return <strong>此方法一定要慎用！</strong>
     */
    @Override
    public Integer truncate() {
	Integer rows = 0;
	try {
	    rows = this.getSqlSession().delete(namespace + "." + SQLID_TRUNCATE);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return rows;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月29日
     * @function：TODO 日志打印
     * @param sqlId
     * @param param
     */
    @Override
    public void printLog(Object primaryKey, Object params) {
	Configuration configuration = this.getSqlSession().getConfiguration();
	// sqlId为配置文件中的sqlid
	MappedStatement mappedStatement = configuration.getMappedStatement((String) primaryKey);
	// param为传入到sql语句中的参数
	BoundSql boundSql = mappedStatement.getBoundSql(params);
	// 得到sql语句
	String sql = boundSql.getSql().trim();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	System.out.println("info-sql: " + sdf.format(new Date()) + "  " + sql);
    }
    
}

package com.panasign.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作接口
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-3
 * @param <T>
 */
public interface BaseDaoInterface<T> {
	/**
	 * 强刷缓存，使对象立刻持久化
	 * 
	 * @author: Wu.Liang
	 */

	public void flush();

	/**
	 * 清空session缓存
	 * 
	 * @author: Wu.Liang
	 */
	public void clear();

	/**
	 * 根据id直接删除一个对象
	 * @param t
	 * @param id
	 * @author: Wu.Liang
	 */
	public void removeById(Class<T> c, Serializable id);
	
	/**
	 * 保存一个对象
	 * @param t
	 * @return
	 * @author: Wu.Liang
	 */
	public Serializable save(T o);
	
	/**
	 * 修改一个对象
	 * @param t
	 * @return
	 * @author: Wu.Liang
	 */
	public T merge(T t);

	/**
	 * 根据id跟对象c.class直接get对象
	 * @param c
	 * @param id
	 * @return
	 * @author: Wu.Liang
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 根据hql语句获得一个对象
	 * 
	 * @param hql
	 * @return
	 * @author: Wu.Liang
	 */
	public T get(String hql);

	/**
	 * 根据hql语句与参数 map获取唯一对象 例：select u.id,u.name from User u where u.id=:i and
	 * u.name=:n;
	 * 
	 * @param hql
	 * @param params
	 *            使用标准化hql参数（既‘:’后面部分），如上面例子，map参数应为: map.put("i",
	 *            1);map.put("n", "zhangsan");
	 * @return
	 * @author: Wu.Liang
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * 删除o对象
	 * 
	 * @param o
	 * @author: Wu.Liang
	 */
	public void delete(T o);

	/**
	 * 更新o对象
	 * 
	 * @param o
	 * @author: Wu.Liang
	 */
	public void update(T o);

	/**
	 * 新增或更新o对象
	 * 
	 * @param o
	 * @author: Wu.Liang
	 */
	public void saveOrUpdate(T o);

	/**
	 * 根据hql语句查找对象T的集合
	 * 
	 * @param hql
	 * @param useSecondCache 是否使用二级缓存，如无特殊需求，请用false
	 * @return
	 * @author: Wu.Liang
	 */
	public List<T> find(String hql, boolean useSecondCache);

	/**
	 * 根据hql语句与参数 map获取对象集合 例：
	 * select u.id,u.name from User u where u.id=:i and u.name=:n;
	 * @param hql
	 * @param params 
	 * 使用标准化hql参数（既‘:’后面占位符部分），如上面例子，map参数应为: 
	 * map.put("i", 1);map.put("n", "zhangsan");
	 * @return
	 * @author: Wu.Liang
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 不带预编译参数的hql语句分页
	 * @param hql
	 * @param page 当前页数
	 * @param rows 每页行数
	 * @return
	 * @author: Wu.Liang
	 */
	public List<T> find(String hql, int page, int rows);

	/**
	 * 分页查找对象集合，参数形式参照其他find方法。
	 * @param hql
	 * @param params
	 * @param page 当前页数
	 * @param rows 每页行数
	 * @return
	 * @author: Wu.Liang
	 */
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows);

	/**
	 * 不带预编译参数的hql，计算总记录条数
	 * @param hql
	 * @return
	 * @author: Wu.Liang
	 */
	public Long count(String hql);

	/**
	 * 计算总记录条数
	 * @param hql
	 * @param params
	 * 例子：select u.id,u.name from User u where u.id=:i and u.name=:n;
	 * 使用标准化hql参数（既‘:’后面占位符部分），如上面例子，map参数应为: 
	 * map.put("i", 1);map.put("n", "zhangsan");
	 * @return
	 * @author: Wu.Liang
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 根据不带预编译参数的hql，批量更新/或删除
	 * @param hql
	 * @return 成功条数
	 * @author: Wu.Liang
	 */
	public int updateOrDeleteBatchByHql(String hql);

	/**
	 * 批量更新/或删除
	 * @param hql
	 * @param params
	 * 例子：select u.id,u.name from User u where u.id=:i and u.name=:n;
	 * 使用标准化hql参数（既‘:’后面占位符部分），如上面例子，map参数应为: 
	 * map.put("i", 1);map.put("n", "zhangsan");
	 * @return 成功条数
	 * @author: Wu.Liang
	 */
	public int updateOrDeleteBatchByHql(String hql, Map<String, Object> params);
	
	/**
	 * 查找某些字段、类型的集合
	 * @param hql
	 * @param useSecondCache
	 * @return
	 * @author: Wu.Liang
	 */
	public List<Object> findSomeFields(String hql, boolean useSecondCache);
	
	
	/**
	 * 查找某些字段、类型的集合
	 * @param hql
	 * @param params
	 * @return
	 * @author: Wu.Liang
	 */
	public List<Object> findSomeFields(String hql, Map<String, Object> params);
	
	
	/**
	 * 用于多表查询，传入结果集的模型类，返回结果集合
	 * @param hql
	 * @param page
	 * @param rows
	 * @return
	 * @author: Wu.Liang
	 */
	public <C> List<C> findByManyTables(String hql, int page, int rows);
	
	/**
	 * 用于多表查询，传入结果集的模型类，返回结果集合，带分页
	 * @param hql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 * @author: Wu.Liang
	 * @param <C>
	 */
	public <C> List<C> findByManyTables(String hql, Map<String, Object> params, int page, int rows);

    /**
     * 批量删除
     * @param string
     * @param id
     */
	public void deleteBySql(String hql, Integer id);
	
	/**
	 * 原生的SQL统计数量
	 * @param sql
	 * @param params
	 * @return
	 * @author Wu.Liang
	 */
	public Long countSQL(String sql, Map<String, Object> params); 
	
	/**
	 * 使用原生的SQL语句
	 * @param sql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 * @author Wu.Liang
	 */
	public <C> List<C> findByManyTablesSQL(String sql, Map<String, Object> params, int page, int rows);
	
}

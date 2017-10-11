package com.panasign.dao.Impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panasign.dao.BaseDaoInterface;

/**
 * 数据库操作实现类，基于hibernate4框架，版本向下兼容
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-3
 * @param <T>
 */
@Repository("dao")
public class HibernateDao<T> implements BaseDaoInterface<T> {
	/**
	 * sessionFactory引用
	 */
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获得currentSession对象
	 * @return Session
	 * @author: Wu.Liang
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
		
	}
	
	/**
	 * 强刷缓存，使对象立刻持久化
	 * 
	 * @author: Wu.Liang
	 */
	@Override
	public void flush() {
		getCurrentSession().flush();
	}

	/**
	 * 清空session缓存
	 * 
	 * @author: Wu.Liang
	 */
	@Override
	public void clear() {
		getCurrentSession().clear();
	}

	/**
	 * 保存一个对象
	 * 
	 * @param t
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	public Serializable save(T t) {
		return this.getCurrentSession().save(t);
	}

	/**
	 * 根据id跟对象类直接get对象
	 * @param c
	 * @param id
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	/**
	 * 根据hql语句获得一个对象
	 * 
	 * @param hql
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

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
	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		@SuppressWarnings("unchecked")
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 删除o对象
	 * 
	 * @param o
	 * @author: Wu.Liang
	 */
	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	/**
	 * 更新o对象
	 * 
	 * @param o
	 * @author: Wu.Liang
	 */
	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	/**
	 * 新增或更新o对象
	 * 
	 * @param o
	 * @author: Wu.Liang
	 */
	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	/**
	 * 根据hql语句查找对象T的集合
	 * 
	 * @param hql
	 * @param useSecondCache 是否使用二级缓存，默认为false
	 * @return
	 * @author: Wu.Liang
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, boolean useSecondCache) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setCacheable(useSecondCache);
		return q.list();
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		return q.list();
	}

	/**
	 * 分页查找对象集合，参数形式参照其他find方法。
	 * @param hql
	 * @param params
	 * @param page 当前页数
	 * @param rows 每页行数
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 不带预编译参数的hql语句分页
	 * @param hql
	 * @param page 当前页数
	 * @param rows 每页行数
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 不带预编译参数的hql，计算总记录条数
	 * @param hql
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	public Long count(String hql) {
		return (Long)this.getCurrentSession().createQuery(hql).uniqueResult();
	}
	
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
	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		return (Long) q.uniqueResult();
	}

	/**
	 * 根据不带预编译参数的hql，批量更新
	 * @param hql
	 * @return 成功条数
	 * @author: Wu.Liang
	 */
	@Override
	public int updateOrDeleteBatchByHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	/**
	 * 批量更新
	 * @param hql
	 * @param params
	 * 例子：select u.id,u.name from User u where u.id=:i and u.name=:n;
	 * 使用标准化hql参数（既‘:’后面占位符部分），如上面例子，map参数应为: 
	 * map.put("i", 1);map.put("n", "zhangsan");
	 * @return 成功条数
	 * @author: Wu.Liang
	 */
	@Override
	public int updateOrDeleteBatchByHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		return q.executeUpdate();
	}

	/**
	 * 根据id直接删除一个对象
	 * @param t
	 * @param id
	 * @author: Wu.Liang
	 */
	@Override
	public void removeById(Class<T> tClass, Serializable id) {
		T t = this.get(tClass, id);
		this.delete(t);
	}

	
	/**
	 * 查找某些字段、类型的集合，或者用于复合查询
	 * @param hql
	 * @param useSecondCache
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findSomeFields(String hql, boolean useSecondCache) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setCacheable(useSecondCache);
		return q.list();
	}

	/**
	 * 查找某些字段、类型的集合，或者用于复合查询
	 * @param hql
	 * @param params
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findSomeFields(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		return q.list();
	}

	/**
	 * 用于多表查询，传入结果集的模型类，返回结果集合
	 * @param hql
	 * @param params
	 * @param cls
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <C> List<C> findByManyTables(String hql, int page, int rows) {
		List<C> list = new ArrayList<C>();
		Query q = this.getCurrentSession().createQuery(hql);
		q.setFirstResult((page - 1) * rows);
		q.setMaxResults(rows);
		list = q.list();
		return list;
	}

	/**
	 * 用于多表查询，传入结果集的模型类，返回结果集合，带分页
	 * @param hql
	 * @param params
	 * @param page
	 * @param rows
	 * @param cls
	 * @return
	 * @author: Wu.Liang
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> List<C> findByManyTables(String hql, Map<String, Object> params,
			int page, int rows) {
		List<C> list = new ArrayList<C>();
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					q.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					q.setParameterList(key, (Object[])obj);
				}else{
					q.setParameter(key, obj);
				}
			}
		}
		q.setFirstResult((page - 1) * rows);
		q.setMaxResults(rows);
		list=q.list();
		return list;
	}
	
	/**
	 * 使用原生的SQL语句
	 * @param sql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 * @author Wu.Liang
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> List<C> findByManyTablesSQL(String sql, Map<String, Object> params, int page, int rows) {
		List<C> list = new ArrayList<C>();
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					sqlQuery.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					sqlQuery.setParameterList(key, (Object[])obj);
				}else{
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		sqlQuery.setFirstResult((page - 1) * rows);
		sqlQuery.setMaxResults(rows);
		list = sqlQuery.list();
		return list;
	}

	/**
	 * 修改一个对象，返回修改后的persistent状态的对象
	 * @param t
	 * @return
	 * @author: Wu.Liang
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T merge(T t) {
		return (T) this.getCurrentSession().merge(t);
	}

	@Override
	public void deleteBySql(String sql, Integer id) {
		  this.getCurrentSession().createSQLQuery(sql).setParameter(0, id).executeUpdate();  
	   this.getCurrentSession().flush(); //清理缓存，执行批量插入  
	        this.getCurrentSession().clear(); //清空缓存中的 对象  
	}

	/**
	 * 原生的SQL统计数量
	 * @param hql
	 * @param params
	 * @return
	 * @author Wu.Liang
	 */
	@Override
	public Long countSQL(String sql, Map<String, Object> params) {
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if(obj instanceof Collection<?>){
					sqlQuery.setParameterList(key, (Collection<?>)obj);
				}else if(obj instanceof Object[]){
					sqlQuery.setParameterList(key, (Object[])obj);
				}else{
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		Object count = sqlQuery.uniqueResult();
		if(count instanceof BigInteger){
			return ((BigInteger)count).longValue();
		}else if(count instanceof Integer){
			return new Long((Integer)count);
		}else{
			return (Long)count;
		}
	}
}

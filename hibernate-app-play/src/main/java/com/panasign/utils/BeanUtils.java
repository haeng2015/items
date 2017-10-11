/**
 * 
 */
package com.panasign.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: BeanUtils
 * @Description: 封装java bean的基本转换操作
 * @Author TangZheng
 * @Date 2013-11-21 上午10:25:33
 * @Copyright: 版权归 HundSun 所有
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	
	private static Logger log = LoggerFactory.getLogger(BeanUtils.class);
	
	private final static Map<Class<?>, Method> METHOD_MAP = new HashMap<Class<?>, Method>();

	static {// 注入所有需要转换的类型
		try {
			METHOD_MAP.put(Integer.class, Integer.class.getMethod("valueOf", String.class));
			METHOD_MAP.put(Long.class, Long.class.getMethod("valueOf", String.class));
			METHOD_MAP.put(Boolean.class, Boolean.class.getMethod("valueOf", String.class));
//			METHOD_MAP.put(BigDecimal.class, BigDecimal.class.getMethod("valueOf", Long.class));
			
			// ...
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Object convertMap(Class<?> type, Map<?, ?> map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		// 获取类属性
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		
		// 创建 JavaBean 对象
		Object obj = type.newInstance();
		
//		Constructor c = type.getDeclaredConstructors()[0];   
//	    c.setAccessible(true);//将c设置成可访问
//	    Object obj = c.newInstance();

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Method target = descriptor.getWriteMethod();
				
				Object value = map.get(propertyName);
				
				try {
					target.invoke(obj, new Object[]{convertType(value, target.getParameterTypes()[0])});
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}				
			}
		}
		return obj;
	}

	/**
	 * 将value对象类型转换为targetType类型
	 * @param value 
	 * @param targetType
	 * @return
	 * @throws NumberFormatException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Object convertType(Object value, Class<?> targetType) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if(value != null) {
			if(value.getClass() != targetType) {
				if(targetType == java.util.Date.class) {
					if(value.getClass() == java.sql.Timestamp.class)
					    return new java.util.Date(((java.sql.Timestamp)value).getTime());
					else if(value.getClass() == java.sql.Date.class)
						return new java.util.Date(((java.sql.Date)value).getTime());
					else 
					{
						log.info("{}类型无法匹配{}目标类型...", value.getClass(), targetType);
						return null;
					}
				}
				else if(METHOD_MAP.containsKey(targetType)) {
					Method method = METHOD_MAP.get(targetType);
					
					return method.invoke(targetType, value.toString());
				} 
				else {
					log.info("缺少{}类型...", targetType);
					return null;
				}
		    }
		}
		return value;
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Map<String, Object> convertBean(Object bean)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
}

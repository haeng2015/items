/**
 * Copyright 2015-2025. All rights reserved. Powered by panasign.
 */
package com.panasign.core;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * 自定义函数使用(hibernate 本地方言扩展)
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Liu.ruxing
 * @createDate: 2015-8-10
 */
public class MysqlDailetExtend extends org.hibernate.dialect.MySQLDialect{

	public MysqlDailetExtend() {
		super();
		// Hibernate.DATE
		registerFunction("date_add", new SQLFunctionTemplate(StandardBasicTypes.DATE, "date_add(?1, INTERVAL ?2 ?3)"));
		registerFunction("date_sub", new SQLFunctionTemplate(StandardBasicTypes.DATE, "date_sub(?1, INTERVAL ?2 ?3)"));
	}
}

/**
 * Copyright 2015-2025. All rights reserved. Powered by panasign.
 */
package com.panasign.core;

import java.util.Comparator;

/**
 * 比较器，重写compare方法，根据ListSort的flag字段来排序ListSortExample
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Liu.ruxing
 * @createDate: 2015-8-4
 */
public class ComparatorListSort implements Comparator<Object>{
	public int compare(Object arg0, Object arg1) {
		TreeNode example0 = (TreeNode) arg0;
		TreeNode example1 = (TreeNode) arg1;
		// 首先比较年龄，如果年龄相同，则比较名字
		int flag = example0.getId().compareTo(example1.getId());
		return flag;
	}

}

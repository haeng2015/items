package com.pbms.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月23日
 * @说明：分页结果
 */
public class PagingResult<T> {
    // 当前页
    private int currentPage;
    // 总共记录条数
    private int totalSize;
    //总页数
    private int totalPage;
    // 结果集对象
    private List<T> resultList = new ArrayList<T>();
    
    public int getCurrentPage() {
	return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
    }
    
    public int getTotalSize() {
	return totalSize;
    }
    
    public void setTotalSize(int totalSize) {
	this.totalSize = totalSize;
    }
    
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResultList() {
	return resultList;
    }
    
    public void setResultList(List<T> resultList) {
	this.resultList = resultList;
    }
}

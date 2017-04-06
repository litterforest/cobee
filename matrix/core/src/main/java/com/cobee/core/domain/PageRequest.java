/**
 * 
 */
package com.cobee.core.domain;

import java.io.Serializable;

/**
 * <pre>
 * 前端分页请求对象
 * </pre>
 * 
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月5日
 *
 */
public class PageRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1762072130882101327L;

	private Integer currentPage; // 当前页码
	private Integer pageSize; // 一页显示记录的条数

	public PageRequest() {
		super();
	}

	public PageRequest(Integer currentPage, Integer pageSize) {
		super();
		if (currentPage < 1) {
			throw new IllegalArgumentException("当前页码从1开始");
		}
		if (pageSize < 1) {
			throw new IllegalArgumentException("pageSize的值不能少于1");
		}
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * <pre>获取mysql分页子句</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日 下午10:40:57
	 * 
	 * @return
	 */
	public String getLimitClause()
	{
		if(currentPage == null || pageSize == null) return "";
		return " LIMIT " + ((this.currentPage - 1) * this.pageSize) + "," + this.pageSize;
	}
	
}

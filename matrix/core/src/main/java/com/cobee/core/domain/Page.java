/**
 * 
 */
package com.cobee.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 返回的分页查询结果对象
 * </pre>
 * 
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月6日
 *
 */
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5341911130141526595L;

	/**
	 * 默认页码
	 */
	public static final Integer DEFAULT_PAGE_NO = 1;
	/**
	 * 默认页面大小
	 */
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	/**
	 * 默认的快速导航页码显示个数
	 */
	public static final Integer DEFAULT_PAGE_NAV_SIZE = 5;

	private Integer pageNo = DEFAULT_PAGE_NO; // 当前页码
	private Integer pageSize = DEFAULT_PAGE_SIZE; // 页面大小
	private Integer pageNaviSize = DEFAULT_PAGE_NAV_SIZE; // 页码快速导航显示的个数
	private Integer totalCount; // 总的记录数
	private List<T> content; // 返回的查询结果集
	
	public Page() {
		super();
	}
	
	public Page(Integer pageNo, Integer pageSize, Integer totalCount) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNaviSize() {
		return pageNaviSize;
	}

	public void setPageNaviSize(Integer pageNaviSize) {
		this.pageNaviSize = pageNaviSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getFirstPage() {
		return new Integer(1);
	}

	public Integer getPrePage() {
		return (getPageNo() - 1) >= 1 ? (getPageNo() - 1) : 1;
	}

	public Integer getNextPage() {
		Integer totalPage = getTotalPage();
		return (getPageNo() + 1) <= totalPage ? (getPageNo() + 1) : totalPage;
	}

	public Integer getLastPage() {
		return getTotalPage();
	}

	public Integer getTotalPage() {
		return (totalCount % getPageSize() == 0) ? (totalCount / getPageSize()) : ((totalCount / getPageSize()) + 1);
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	/**
	 * <pre>
	 * 返回快速导航页码
	 * </pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月6日
	 *
	 * @return
	 */
	public int[] getPageNavis() {
		// 先运算出左，右边界
		int start = 0, end = 0;
		int a = pageNaviSize / 2;
		start = pageNo - a;
		if (pageNaviSize % 2 == 0) {
			end = pageNo + a - 1;
		} else {
			end = pageNo + a;
		}
		// 分三种情况处理
		int totalPages = getTotalPage();
		int[] b = new int[pageNaviSize];
		// 左边界
		if (start < 1) {
			for (int i = 0, step = 1; i < pageNaviSize; i++, step++) {
				if (step <= totalPages) {
					b[i] = step;
				} else {
					break;
				}
			}
		} else if (end > totalPages) { // 右边界
			for (int i = pageNaviSize - 1, step = totalPages; i >= 0; i--, step--) {
				if (step > 0) {
					b[i] = step;
				} else {
					break;
				}
			}
		} else { // 中间
			for (int i = 0; i < pageNaviSize; i++) {
				b[i] = start++;
			}
		}
		return b;
	}

}

/**
 * 
 */
package com.cobee.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.cobee.core.po.PageRequest;

/**
 * <pre></pre>
 * 
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月5日
 *
 */
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6385104791618600696L;

	private ID id;
	private String createBy; // 创建者
	private Date createDate; // 创建日期
	private String updateBy; // 更新者
	private Date updateDate; // 更新日期
	private String delFlag; // 删除标记（0：正常；1：删除；2：审核）
	private String remarks; // 备注

	private PageRequest pageRequest; // 分页请求对象，处理当前页和页记录大小参数

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}

	public abstract void preInsert();

	public abstract void preUpdate();

}

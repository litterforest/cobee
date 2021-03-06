/**
 * 
 */
package com.cobee.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.cobee.core.common.persistence.annotation.CreatedBy;
import com.cobee.core.common.persistence.annotation.CreatedDate;
import com.cobee.core.common.persistence.annotation.LastModifiedBy;
import com.cobee.core.common.persistence.annotation.LastModifiedDate;
import com.cobee.core.domain.PageRequest;

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
	@CreatedBy
	private String createBy; // 创建者
	@CreatedDate
	private Date createDate; // 创建日期
	@LastModifiedBy
	private String updateBy; // 更新者
	@LastModifiedDate
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

	/**
	 * <pre>在具体的应用项目中实现，可以使用AOP的方式来做</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月7日
	 *
	 */
	@Deprecated
	public void preInsert()
	{
		//TODO
	}

	/**
	 * <pre>在具体的应用项目中实现，可以使用AOP的方式来做</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月7日
	 *
	 */
	@Deprecated
	public void preUpdate()
	{
		//TODO
	}

}

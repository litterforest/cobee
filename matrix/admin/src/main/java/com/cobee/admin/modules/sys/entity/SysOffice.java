package com.cobee.admin.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.cobee.core.entity.BaseEntity;

public class SysOffice extends BaseEntity<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -518523336211153102L;

	private String id; // '编号'
	private String parentId; // '父级编号'
	private String parentIds;// '所有父级编号'
	private String name;// '名称',
	private Double sort;// '排序',
	private String areaId;// '归属区域',
	private String code;// '区域编码',
	private String type;// '机构类型',
	private String grade;// '机构等级',
	private String address;// '联系地址',
	private String zipCode;// '邮政编码',
	private String master;// '负责人',
	private String phone;// '电话',
	private String fax;// '传真',
	private String email;// '邮箱',
	private String useable;// '是否启用',
	private String primaryPerson;// '主负责人',
	private String deputyPerson;// '副负责人',
	private String createBy;// '创建者',
	private Date createDate;// '创建时间',
	private String updateBy;// '更新者',
	private Date updateDate;// '更新时间',
	private String remarks;// '备注信息',
	private String delFlag;// '删除标记',

	public SysOffice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysOffice(String id, String parentId, String parentIds, String name, Double sort, String areaId, String code,
			String type, String grade, String address, String zipCode, String master, String phone, String fax,
			String email, String useable, String primaryPerson, String deputyPerson, String createBy, Date createDate,
			String updateBy, Date updateDate, String remarks, String delFlag) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.name = name;
		this.sort = sort;
		this.areaId = areaId;
		this.code = code;
		this.type = type;
		this.grade = grade;
		this.address = address;
		this.zipCode = zipCode;
		this.master = master;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.useable = useable;
		this.primaryPerson = primaryPerson;
		this.deputyPerson = deputyPerson;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public String getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(String deputyPerson) {
		this.deputyPerson = deputyPerson;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "SysOffice [id=" + id + ", parentId=" + parentId + ", parentIds=" + parentIds + ", name=" + name
				+ ", sort=" + sort + ", areaId=" + areaId + ", code=" + code + ", type=" + type + ", grade=" + grade
				+ ", address=" + address + ", zipCode=" + zipCode + ", master=" + master + ", phone=" + phone + ", fax="
				+ fax + ", email=" + email + ", useable=" + useable + ", primaryPerson=" + primaryPerson
				+ ", deputyPerson=" + deputyPerson + ", createBy=" + createBy + ", createDate=" + createDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate + ", remarks=" + remarks + ", delFlag="
				+ delFlag + "]";
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}
	
}

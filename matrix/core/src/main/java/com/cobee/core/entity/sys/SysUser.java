/**
 * 
 */
package com.cobee.core.entity.sys;

import com.cobee.core.entity.BaseEntity;

/**
 * <pre>
 * 框架系统用户
 * </pre>
 * 
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月13日
 *
 */
public class SysUser extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6582234588391700425L;

	private Integer deptId; // '关联的部门ID'
	private String userName; // '用户帐号名称'
	private String password; // '加密后的用户密码',
	private String jobNumber; // '工号',
	private String realName; // '姓名',
	private String email; // '电子邮箱',
	private String phone; // '固话',
	private String mobile; // '手机号码',
	private String userType; // '用户类型',
	private String photo; // '头像',
	private String qrcode; // '二维码',
	private String loginIp; // '最后登陆IP',
	private String loginDate; // '最后登陆日期时间',
	private String loginFlag; // '是否可以登录',

	public SysUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

}

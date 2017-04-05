package com.cobee.admin.modules.sys.service;

import java.util.List;

import com.cobee.admin.modules.sys.entity.SysOffice;

public interface SysOfficeService {
	
	List<SysOffice> findAll();
	
	List<SysOffice> findList(SysOffice sysOffice);
	
	List<SysOffice> findByPage(SysOffice sysOffice);
	
	void update(SysOffice sysOffice);
}

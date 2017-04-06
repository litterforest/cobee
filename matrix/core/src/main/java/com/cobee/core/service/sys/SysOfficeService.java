package com.cobee.core.service.sys;

import java.util.List;

import com.cobee.core.entity.sys.SysOffice;

public interface SysOfficeService {
	
	List<SysOffice> findAll();
	
	List<SysOffice> findList(SysOffice sysOffice);
	
	List<SysOffice> findByPage(SysOffice sysOffice);
	
	void update(SysOffice sysOffice);
}

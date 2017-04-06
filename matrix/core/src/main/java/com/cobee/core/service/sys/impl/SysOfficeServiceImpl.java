/**
 * 
 */
package com.cobee.core.service.sys.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cobee.core.dao.sys.SysOfficeMapper;
import com.cobee.core.domain.Page;
import com.cobee.core.entity.sys.SysOffice;
import com.cobee.core.service.PagingAndSortingService;
import com.cobee.core.service.sys.SysOfficeService;

/** 
 * <pre></pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月5日
 *
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class SysOfficeServiceImpl extends PagingAndSortingService<SysOfficeMapper> implements SysOfficeService {
	
	@Override
	public List<SysOffice> findAll() {
		Page<SysOffice> list = this.findByPage(new SysOffice());
		return dao.findAll();
	}

	@Override
	public List<SysOffice> findList(SysOffice sysOffice) {
		return dao.findList(sysOffice);
	}
	
	@Transactional(value = "transactionManager", readOnly = false)
	public void update(SysOffice sysOffice){
		dao.update(sysOffice);
		Page<SysOffice> list = this.findByPage(sysOffice);
		System.out.println(list);
	}

	@Override
	public Page<SysOffice> findByPage(SysOffice sysOffice) {
		return super.findByPage(sysOffice);
	}
	
}

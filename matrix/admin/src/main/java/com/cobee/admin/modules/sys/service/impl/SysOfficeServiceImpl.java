/**
 * 
 */
package com.cobee.admin.modules.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cobee.admin.modules.sys.dao.SysOfficeMapper;
import com.cobee.admin.modules.sys.entity.SysOffice;
import com.cobee.admin.modules.sys.service.SysOfficeService;
import com.cobee.core.service.BaseService;
import com.cobee.core.service.PagingAndSortingService;

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
		List<SysOffice> list = this.findByPage(new SysOffice());
		return dao.findAll();
	}

	@Override
	public List<SysOffice> findList(SysOffice sysOffice) {
		return dao.findList(sysOffice);
	}
	
	@Transactional(value = "transactionManager", readOnly = false)
	public void update(SysOffice sysOffice){
		dao.update(sysOffice);
		List<SysOffice> list = this.findByPage(sysOffice);
		System.out.println(list);
	}

	@Override
	public List<SysOffice> findByPage(SysOffice sysOffice) {
		return super.findByPage(sysOffice);
	}
	
}

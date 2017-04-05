/**
 * 
 */
package com.cobee.core.service;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cobee.core.dao.CrudDao;
import com.cobee.core.entity.BaseEntity;

/** 
 * <pre>业务层的父类</pre>
 * 
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月5日
 *
 */
public abstract class BaseService<T extends CrudDao<? extends BaseEntity<? extends Serializable>, ? extends Serializable>> {

	@Autowired
	protected T dao;
	
}

/**
 * 
 */
package com.cobee.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cobee.core.dao.CrudDao;
import com.cobee.core.entity.BaseEntity;

/**
 * <pre>
 * 增删改查业务基类
 * </pre>
 *
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月9日 下午12:31:17
 *
 */
@Transactional(readOnly = true)
public abstract class CrudService<T extends CrudDao<D, ID>, D extends BaseEntity<ID>, ID extends Serializable>
		extends BaseService {

	@Autowired
	protected T dao;
	
	public Integer save(D entity)
	{
		entity.preInsert();
		return dao.save(entity);
	}
	
	public Integer saveBySelective(D entity)
	{
		entity.preInsert();
		return dao.saveBySelective(entity);
	}
	
//	@Transactional(readOnly = false)
	public Integer update(D entity)
	{
		entity.preUpdate();
		return dao.update(entity);
	}
	
	public Integer updateBySelective(D entity)
	{
		entity.preUpdate();
		return dao.updateBySelective(entity);
	}
	
	public D get(ID id)
	{
		return dao.get(id);
	}
	
	public List<D> findAll()
	{
		return dao.findAll();
	}
	
	public List<D> findList(D entity)
	{
		return dao.findList(entity);
	}
	
	public Integer count(D entity)
	{
		return dao.count(entity);
	}
	
	public void deleteByID(ID id)
	{
		dao.deleteByID(id);
	}
	
	public void delete(D entity)
	{
		dao.delete(entity);
	}
	
	public void deleteByLogic(D entity)
	{
		dao.deleteByLogic(entity);
	}
	
}

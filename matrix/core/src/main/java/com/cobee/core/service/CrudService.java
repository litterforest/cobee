/**
 * 
 */
package com.cobee.core.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	
	/**
	 * <pre>持久化一个对象，门面方法，自动判断是新增还是更新</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月10日
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer save(D entity)
	{
		ID id = entity.getId();
		if (id != null)
		{
			if (id instanceof String)
			{
				String idStr = (String) id;
				if (StringUtils.isNotBlank(idStr))
				{
					return insertBySelective(entity);
				}
			}
			else
			{
				return insertBySelective(entity);
			}
		}
		return updateBySelective(entity);
	}
	
	/**
	 * <pre>保存一个实体对象</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return 返回新增记录的条数
	 */
	@Transactional(readOnly = false)
	public Integer insert(D entity)
	{
		entity.preInsert();
		return dao.insert(entity);
	}
	
	/**
	 * <pre>保存一个实体对象, 有选择地创建字段数据</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return 返回新增记录的条数
	 */
	@Transactional(readOnly = false)
	public Integer insertBySelective(D entity)
	{
		entity.preInsert();
		return dao.insertBySelective(entity);
	}
	
	/**
	 * <pre>更新一个实体对象</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer update(D entity)
	{
		entity.preUpdate();
		return dao.update(entity);
	}
	
	/**
	 * <pre>有条件地更新一个实体对象中的字段</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer updateBySelective(D entity)
	{
		entity.preUpdate();
		return dao.updateBySelective(entity);
	}
	
	/**
	 * <pre>根据实体ID来获取数据库中的记录</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param id
	 * @return 查询实体对象
	 */
	public D get(ID id)
	{
		return dao.get(id);
	}
	
	/**
	 * <pre>返回表中的所有数据</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @return 所有数据的列表
	 */
	public List<D> findAll()
	{
		return dao.findAll();
	}
	
	/**
	 * <pre>根据实例条件查询列表</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return
	 */
	public List<D> findList(D entity)
	{
		return dao.findList(entity);
	}
	
	/**
	 * <pre>根据条件查询数据库中的记录数</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return 数据的记录数
	 */
	public Integer count(D entity)
	{
		return dao.count(entity);
	}
	
	/**
	 * <pre>根据ID删除数据记录</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteByID(ID id)
	{
		dao.deleteByID(id);
	}
	
	/**
	 * <pre>根据给定的例子删除数据库记录</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(D entity)
	{
		dao.delete(entity);
	}
	
	/**
	 * <pre>逻辑删除</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void deleteByLogic(D entity)
	{
		dao.deleteByLogic(entity);
	}
	
}

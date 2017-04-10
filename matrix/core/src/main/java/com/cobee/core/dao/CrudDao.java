package com.cobee.core.dao;

import java.io.Serializable;
import java.util.List;

import com.cobee.core.entity.BaseEntity;

/**
 * <pre>数据库通用的增删改查操作，使用泛型定义实体</pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月5日
 *
 */
public interface CrudDao<T extends BaseEntity<? extends Serializable>, ID extends Serializable> {

	/**
	 * <pre>持久化一个对象，门面方法，自动判断是新增还是更新</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月10日
	 *
	 * @param entity
	 * @return
	 */
	int save(T entity);
	
	/**
	 * <pre>保存一个实体对象</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return 返回新增记录的条数
	 */
	int insert(T entity);
	
	/**
	 * <pre>保存一个实体对象, 有选择地创建字段数据</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return 返回新增记录的条数
	 */
	int insertBySelective(T entity);
	
	/**
	 * <pre>更新一个实体对象</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return
	 */
	int update(T entity);
	
	/**
	 * <pre>有条件地更新一个实体对象中的字段</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return
	 */
	int updateBySelective(T entity);
	
	/**
	 * <pre>根据实体ID来获取数据库中的记录</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param id
	 * @return 查询实体对象
	 */
	T get(ID id);
	
	/**
	 * <pre>返回表中的所有数据</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @return 所有数据的列表
	 */
	List<T> findAll();
	
	/**
	 * <pre>根据实例条件查询列表</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return
	 */
	List<T> findList(T entity);
	
	/**
	 * <pre>根据条件查询数据库中的记录数</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 * @return 数据的记录数
	 */
	Integer count(T entity);
	
	//Integer customizedCount(String countSQL);
	
	/**
	 * <pre>根据ID删除数据记录</pre>
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param id
	 */
	void deleteByID(ID id);
	
	/**
	 * <pre>根据给定的例子删除数据库记录</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 */
	void delete(T entity); 
	
	/**
	 * <pre>逻辑删除</pre>
	 * 
	 * @author 陈淦森
	 * @version 1.0.1
	 * @date 2017年4月5日
	 *
	 * @param entity
	 */
	void deleteByLogic(T entity);
	
}

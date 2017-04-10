package com.cobee.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cobee.core.common.util.PropertyHelper;
import com.cobee.core.dao.CrudDao;
import com.cobee.core.domain.Page;
import com.cobee.core.domain.PageRequest;
import com.cobee.core.entity.BaseEntity;

public abstract class PagingAndSortingService<T extends CrudDao<D, ID>, D extends BaseEntity<ID>, ID extends Serializable>
		extends CrudService<T, D, ID> {

	private static final Logger logger = LoggerFactory.getLogger(PagingAndSortingService.class);
	
	private static final String DEFAULT_PAGING_METHOD = "findList";
	
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	private String getMapperNamespace() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		return parameterizedType.getActualTypeArguments()[0].getTypeName();
	}

	public Page<D> findByPage(D paramObj) {
		return this.findByPage(paramObj, DEFAULT_PAGING_METHOD);
	}
	
	public Page<D> findByPage(D paramObj, String selectSqlID) {
		String selectID = getMapperNamespace() + "." + selectSqlID;
		SqlSession session = SqlSessionUtils.getSqlSession(sqlSessionFactory);
		Configuration conf = session.getConfiguration();
		MappedStatement ms = conf.getMappedStatement(selectID);
		// 1,查询总记录数
		PageRequest pageRequest = paramObj.getPageRequest();
		// 1.1,记数的时候排除掉order by, limit子句的影响
		paramObj.setPageRequest(null);
		BoundSql bs = ms.getBoundSql(paramObj);
		String sql = bs.getSql();
		sql = sql.toLowerCase();
		logger.debug("lowercase native sql no order by no limit>>>>>>>>>:" + sql);
//		Integer orderByIdx = sql.lastIndexOf("order by");
//		if (orderByIdx >= 0) {
//			sql = sql.substring(0, orderByIdx);
//		}
//		Integer limitIdx = sql.lastIndexOf("limit");
//		if (limitIdx >= 0) {
//			sql = sql.substring(0, limitIdx);
//		}
		Integer fromIdx = sql.indexOf("from");
		String countSql = "select count(1) " + sql.substring(fromIdx);
		logger.debug("count sql no order by no limit>>>>>>>>>:" + countSql);
		// 1.2,计算首页、上一页、下一页、尾页、总页
		Page<D> page = new Page<D>();
		Integer totalCount = doTotalCount(session, countSql, paramObj, bs.getParameterMappings());
		if (totalCount == 0) return page;
		page.setPageNo(pageRequest.getCurrentPage()); page.setPageSize(pageRequest.getPageSize()); page.setTotalCount(totalCount);
		logger.debug("首页：" + page.getFirstPage() + " 上一页：" + page.getPrePage() + " 下一页：" +
		page.getNextPage() + " 尾页：" + page.getLastPage() + " 总页数："+ page.getTotalPage() +" 总记录数：" + page.getTotalCount());
		// 2,获取分页的数据
		String databaseId = conf.getDatabaseId();
		List<D> list = null;
		paramObj.setPageRequest(pageRequest);
		if ("mysql".equalsIgnoreCase(databaseId))
		{
			list = session.selectList(selectID, paramObj);
		}
		else if ("oracle".equalsIgnoreCase(databaseId))
		{
			list = getOraclePagingContent(session, ms, paramObj);
		}
		page.setContent(list);
		return page;
	}

	private List<D> getOraclePagingContent(SqlSession session, MappedStatement ms, D paramObj)
	{
		String pagingSql = paramObj.getPageRequest().getPagingFramework();
		BoundSql bs = ms.getBoundSql(paramObj);
		String nativeSql = bs.getSql();
		pagingSql = String.format(pagingSql, nativeSql);
		logger.debug("oracle pagingSql============>>>>:" + pagingSql);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		List<D> list = new ArrayList<D>();
		// 从mybatis框架中获取查询的结果类型
		Class<D> entityClazz = (Class<D>) ms.getResultMaps().get(0).getType();
		try {
			conn = session.getConnection();
			ps = conn.prepareStatement(pagingSql);
			List<ParameterMapping> parameterMappingList = bs.getParameterMappings();
			for (int i = 0; i < parameterMappingList.size(); i++) {
				ParameterMapping pm = parameterMappingList.get(0);
				ps.setObject(i + 1, PropertyUtils.getProperty(paramObj, pm.getProperty()));
			}
			resultSet = ps.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData(); 
			while(resultSet.next())
			{
				D entity = entityClazz.newInstance();
				for (int i = 0; i < metaData.getColumnCount(); i++)
				{
					String label = metaData.getColumnLabel(i + 1);
					PropertyHelper.setProperty(entity, label, resultSet.getObject(label));
				}
				list.add(entity);
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (resultSet != null)
			{
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	
	private Integer doTotalCount(SqlSession session, String countSql, Object paramObj, List<ParameterMapping> parameterMappingList)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			conn = session.getConnection();
			ps = conn.prepareStatement(countSql);
			for (int i = 0; i < parameterMappingList.size(); i++) {
				ParameterMapping pm = parameterMappingList.get(0);
				ps.setObject(i + 1, PropertyUtils.getProperty(paramObj, pm.getProperty()));
			}
			resultSet = ps.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (resultSet != null)
			{
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}
			if (ps != null)
			{
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return 0;
	}
	
}

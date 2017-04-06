package com.cobee.core.service;

import java.io.Serializable;
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
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cobee.core.dao.CrudDao;
import com.cobee.core.domain.Page;
import com.cobee.core.domain.PageRequest;
import com.cobee.core.entity.BaseEntity;

public abstract class PagingAndSortingService<T extends CrudDao<? extends BaseEntity<? extends Serializable>, ? extends Serializable>>
		extends BaseService<T> {

	private static final Logger logger = LoggerFactory.getLogger(PagingAndSortingService.class);
	
	private static final String DEFAULT_PAGING_METHOD = "findList";
	
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	private String getMapperNamespace() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		return parameterizedType.getActualTypeArguments()[0].getTypeName();
	}

	public <E extends BaseEntity<? extends Serializable>> Page<E> findByPage(E paramObj) {
		String selectID = getMapperNamespace() + "." + DEFAULT_PAGING_METHOD;
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
		Page<E> page = new Page<E>();
		Integer totalCount = doTotalCount(session, countSql, paramObj, bs.getParameterMappings());
		if (totalCount == 0) return page;
		Integer totalPage = (totalCount % pageRequest.getPageSize() == 0) ? (totalCount / pageRequest.getPageSize()) : ((totalCount / pageRequest.getPageSize()) + 1);
		Integer firstPage = 1;
		Integer prePage = (pageRequest.getCurrentPage() - 1) >= 1 ? (pageRequest.getCurrentPage() - 1) : 1;
		Integer nextPage = (pageRequest.getCurrentPage() + 1) <= totalPage ? (pageRequest.getCurrentPage() + 1) : totalPage;
		Integer lastPage = totalPage;
		page.setFirstPage(firstPage);
		page.setLastPage(lastPage);
		page.setNextPage(nextPage);
		page.setPageNo(pageRequest.getCurrentPage());
		page.setPageSize(pageRequest.getPageSize());
		page.setPrePage(prePage);
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		logger.debug("首页：" + firstPage + " 上一页：" + prePage + " 下一页：" + nextPage + " 尾页：" + lastPage + " 总记录数：" + totalCount);
		// 2,获取分页的数据
		paramObj.setPageRequest(pageRequest);
		List<E> list = session.selectList(selectID, paramObj);
		page.setContent(list);
		return page;
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

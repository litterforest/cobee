package com.cobee.core.service;

import java.io.Serializable;
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
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cobee.core.dao.CrudDao;
import com.cobee.core.entity.BaseEntity;

public abstract class PagingAndSortingService<T extends CrudDao<? extends BaseEntity<? extends Serializable>, ? extends Serializable>>
		extends BaseService<T> {

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	private String getMapperNamespace() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		return parameterizedType.getActualTypeArguments()[0].getTypeName();
	}

	public <E extends BaseEntity<? extends Serializable>> List<E> findByPage(E paramObj) {
		String selectID = getMapperNamespace() + ".findList";
		SqlSession session = SqlSessionUtils.getSqlSession(sqlSessionFactory);
		Configuration conf = session.getConfiguration();
		MappedStatement ms = conf.getMappedStatement(selectID);
		BoundSql bs = ms.getBoundSql(paramObj);
		String sql = bs.getSql();
		sql = sql.toLowerCase();
		System.out.println(sql);
		Integer orderByIdx = sql.lastIndexOf("order by");
		if (orderByIdx >= 0) {
			sql = sql.substring(0, orderByIdx);
		}
		Integer limitIdx = sql.lastIndexOf("limit");
		if (limitIdx >= 0) {
			sql = sql.substring(0, limitIdx);
		}
		System.out.println(sql);
		Integer fromIdx = sql.indexOf("from");
		String countSql = "select count(1) " + sql.substring(fromIdx);
		System.out.println(countSql);
		Integer totalCount;
		try {
			Connection conn = session.getConnection();
			PreparedStatement ps = conn.prepareStatement(countSql);
			List<ParameterMapping> parameterMappingList = bs.getParameterMappings();
			for (int i = 0; i < parameterMappingList.size(); i++) {
				ParameterMapping pm = parameterMappingList.get(0);
				ps.setObject(i + 1, PropertyUtils.getProperty(paramObj, pm.getProperty()));
			}

			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			totalCount = resultSet.getInt(1);
			System.out.println(">>>>>>>>>>" + totalCount);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<E> list = session.selectList(selectID, paramObj);
		return list;
	}

}

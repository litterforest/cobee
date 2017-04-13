package com.cobee.core.dao.sys;

import com.cobee.core.common.persistence.annotation.DefaultDataSource;
import com.cobee.core.dao.CrudDao;
import com.cobee.core.entity.sys.SysUser;

@DefaultDataSource
public interface SysUserMapper extends CrudDao<SysUser, Integer> {
	
}

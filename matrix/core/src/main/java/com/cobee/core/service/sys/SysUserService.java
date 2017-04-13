/**
 * 
 */
package com.cobee.core.service.sys;

import org.springframework.stereotype.Service;

import com.cobee.core.dao.sys.SysUserMapper;
import com.cobee.core.entity.sys.SysUser;
import com.cobee.core.service.PagingAndSortingService;

/** 
 * <pre></pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年4月5日
 *
 */
@Service
public class SysUserService extends PagingAndSortingService<SysUserMapper, SysUser, Integer> {
	
}

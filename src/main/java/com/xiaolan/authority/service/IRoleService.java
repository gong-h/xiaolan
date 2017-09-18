package com.xiaolan.authority.service;


import com.xiaolan.authority.domain.Role;

import java.util.List;


public interface IRoleService {
	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Role addRole(Role role);

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	void delRole(Role role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Role upRole(Role role);

	/**
	 * 查询角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	Role findRole(Role role);

	/**
	 * 查询角色
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role findById(Integer id);

	/**
	 * 查询所有角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Role> listOfRole(Role role);


}

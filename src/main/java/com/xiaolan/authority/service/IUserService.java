package com.xiaolan.authority.service;

import com.xiaolan.authority.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User addUser(User user);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	void delUser(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User upUser(User user);

	/**
	 * 查询一个具体用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User findUser(User user);

	User findByUsername(String username);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 * @throws Exception
	 */
	List<User> listOfUser(User user);

}

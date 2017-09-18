package com.xiaolan.authority.repository;


import com.xiaolan.authority.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User> {
	User findByUsername(String username);
	Page<User> findByNicknameLike(String nickname, Pageable pageRequest);

	/**
	 * 不是超级管理员（isAdmin<>0），没有删除的元素（status<>2）
	 * @return
	 */
	@Query("select u from User u where u.isAdmin <> 0 and  u.status <> 2")
    List<User> queryUsersNotAdmin();
}

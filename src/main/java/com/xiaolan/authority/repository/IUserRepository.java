package com.xiaolan.authority.repository;


import com.xiaolan.authority.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}

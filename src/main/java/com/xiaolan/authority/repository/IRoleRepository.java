package com.xiaolan.authority.repository;


import com.xiaolan.authority.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}

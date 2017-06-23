package com.xiaolan.authority.repository;

import com.xiaolan.authority.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuRepository extends JpaRepository<Menu, Integer> {

}

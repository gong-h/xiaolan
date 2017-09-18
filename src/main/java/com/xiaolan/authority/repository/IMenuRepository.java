package com.xiaolan.authority.repository;

import com.xiaolan.authority.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMenuRepository extends JpaRepository<Menu, Integer>,PagingAndSortingRepository<Menu, Integer> {

}

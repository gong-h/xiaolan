package com.xiaolan.authority.service.impl;

import com.xiaolan.authority.domain.Role;
import com.xiaolan.authority.repository.IRoleRepository;
import com.xiaolan.authority.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role upRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRole(Role role) {
        return roleRepository.findOne(role.getId());
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> listOfRole(Role role) {
        if (role == null)
            role = new Role();
        ExampleMatcher matcher = ExampleMatcher.matching()//
                .withMatcher("name", GenericPropertyMatchers.contains())// like包含name属性
                .withMatcher("description", GenericPropertyMatchers.contains())// like包含description属性
                ;
        return roleRepository.findAll(Example.of(role, matcher));
    }
}
